#include <cstdlib>
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <unistd.h>
#include <vector>
#include <fcntl.h>
#include <sys/wait.h>

using namespace std;

/**
 *
 * C1<delimate char><command and param to be executed>
 * C1;ls;-l
 * C2;state of windows vm
 * C2;running
 * C2;paused
 * C2;
 *
 *
 *
 *
 */

const string PANEL_WM_NAME = "bspwm_panel";
const string PANEL_HEIGHT = "24";
/* const string PANEL_FIFO = "/tmp/panel-fifo"; */
const string LEMONBAR = "/home/navn/aur/bspwm-tools/bin/lemonbar";

const string COLOR_DEFAULT_FG="#a7a5a5";
const string COLOR_DEFAULT_BG="#333232";
const string COLOR_MONITOR_FG="#8dbcdf";
const string COLOR_MONITOR_BG="#333232";
const string COLOR_FOCUSED_MONITOR_FG="#b1d0e8";
const string COLOR_FOCUSED_MONITOR_BG="#144b6c";
const string COLOR_FREE_FG="#737171";
const string COLOR_FREE_BG="#333232";
const string COLOR_FOCUSED_FREE_FG="#000000";
const string COLOR_FOCUSED_FREE_BG="#504e4e";
const string COLOR_OCCUPIED_FG="#a7a5a5";
const string COLOR_OCCUPIED_BG="#333232";
const string COLOR_FOCUSED_OCCUPIED_FG="#d6d3d2";
const string COLOR_FOCUSED_OCCUPIED_BG="#504e4e";
const string COLOR_URGENT_FG="#f15d66";
const string COLOR_URGENT_BG="#333232";
const string COLOR_FOCUSED_URGENT_FG="#501d1f";
const string COLOR_FOCUSED_URGENT_BG="#d5443e";
const string COLOR_STATE_FG="#89b09c";
const string COLOR_STATE_BG="#333232";
const string COLOR_TITLE_FG="#a8a2c0";
const string COLOR_TITLE_BG="#333232";
const string COLOR_SYS_FG="#b1a57d";
const string COLOR_SYS_BG="#333232";
bool startsWith(const string &thiis, const string &that);
vector<string> split(const string &thiss, const string &delim);
void runCommand(const char **argv, char *buffer=nullptr, int bsize=0);

inline string pad(const string &data, const string &padStr=" ", int amount=1) {
	string output = data;
	for(int i=0; i<amount; i++) {
		output = padStr + output + padStr;
	}
	return output;
}
inline bool startsWith(const string &thiis, const string &that) {
	return thiis.rfind(that,0);
}
inline string setColor(const string data, const char type, const string &col) {
	string color = col;
	if(col[0] != '#') {
		color = "#"+color;
	}
	stringstream ss;
	ss << "%{" << type << color << "}" << data << "%{" << type <<"-}";
	return ss.str();
}
string setOnClickCommand(const string &data, const string &command) {
	return "%{A:"+command+":}"+data+"%{A}";
}
enum class VmWinStatus {
	RUNNING, PAUSED, OFF
};
int main2() {
	string panel_fifo = getenv("PANEL_FIFO");
	if(panel_fifo.empty()) {
		panel_fifo = "/tmp/panel-fifo";
	}
	int pfd[2];
	if(pipe(pfd) == -1) {
		cout << "Can't create pipe.";
		exit(1);	
	}
	int panelPid = fork();
	/* int panelPid = 1; */
	if(panelPid == -1) {
		cout << "Can't fork.";
		exit(2);	
	}
	else if(panelPid == 0) {
		//child process
		close(pfd[1]);//close the write end
		dup2(pfd[0], STDIN_FILENO);//stdin becomes pfd0
		close(pfd[0]);//stdin is already same
		int ffd = open(panel_fifo.c_str(), O_WRONLY);
		if(ffd == -1) {
			cout << "failed to open fifo.\n";
			exit(3);
		}
		dup2(ffd, STDOUT_FILENO);//stdout becomes ffd(fifo)
		close(ffd);
		execl(LEMONBAR.c_str(), "lemonbar", "-a", "32", "-u", "2", "-n", PANEL_WM_NAME.c_str(), "-g", ("x"+PANEL_HEIGHT).c_str(), "-f", "fixed", "-B", COLOR_DEFAULT_BG.c_str(), "-f", "-something-myfont-medium-r-normal--13-100-96-96-c-80-something-cp1252", "-f", "-wuncon-siji-medium-r-normal--10-100-75-75-c-80-iso10646-1" ,NULL );
		/* execl(LEMONBAR.c_str(), "lemonbar",NULL ); */
		/* execl("/bin/wc","wc",NULL); */
		cout << "Failed to start lemonbar.";
		exit(4);
	}
	//parent process
	close(pfd[0]);//won't be reading.


	string closeButton="XY";
	closeButton = pad(closeButton);
	closeButton = setColor(closeButton, 'B', "a14740");
	closeButton = setColor(closeButton, 'F', "000000");
	closeButton = setOnClickCommand(closeButton, "C1;bspc;node;-c");
	closeButton = "%{T2}" + closeButton + "%{T1}";
	// close_button='%{A:bspc node -c:}%{T2}%{B#a14740}%{F#000000} XY %{F-}%{B-}%{T1}%{A}'

	// Create a text string, which is used to output the text file
	// Read from the text file
	ifstream MyReadFile(panel_fifo);

	// Use a while loop together with the getline() function to read the file line
	// by line
	string input;
	string sys = "";
	string title = "";
	string wm="";
	bool const DEBUG = false;
	int focusedScreen = 0;
	bool noTitle = true;
	DEBUG?cout<<"starting loop\n":cout;
	string vmStatusDisplay = "";
	VmWinStatus vmWinStatus = VmWinStatus::OFF;
	while (getline(MyReadFile, input)) {
		/* { */
		string direct = "";
		DEBUG?cout<<"input: "<<input<<"\n":cout;

		/* { */
		/* 	const char *argv[] = {"virsh", "-c", "qemu:///system", "list", NULL}; */
		/* 	char buffer[200]; */
		/* 	runCommand(argv, buffer, 200); */
		/* 	string out = buffer; */
		/* 	if(out.rfind("running") != string::npos) { */
		/* 		vmStatus = "%{T2}A%{T1}"; */
		/* 	} */
		/* 	else if(out.rfind("paused") != string::npos) { */
		/* 		vmStatus = "%{T2}A%{T3}%{T1}"; */
		/* 	} */
		/* 	/1* vmStatus = setColor(vmStatus, 'F', "#a3a098"); *1/ */
		/* 	/1* vmStatus = setColor(vmStatus, 'B', "#0341fc"); *1/ */
		/* 	vmStatus = setColor(vmStatus, 'F', "#0341fc"); */
		/* 	vmStatus = setColor(vmStatus, 'B', "#b09999"); */
		/* } */

		if(input[0] == '%' && input[1] == '{') {
			direct = input;
		}
		else if(input[0] == 'C') {
			if(input[1] == '1') {
				string delim(1,input[2]);
				int pid = fork();
				if(pid == -1) {
					cout << "Fork failed\n";
				}
				else if(pid == 0) {
					//inside child
					input = input.substr(3);
					auto inputSplit = split(input, delim);
					char **argv = new char*[inputSplit.size()+1];
					for(int i=0; i<inputSplit.size(); i++) {
						argv[i] = const_cast<char*>(inputSplit[i].c_str());
					}
					argv[inputSplit.size()] = NULL;
					execvp(argv[0], argv);
				}
			}
			else if(input[1] == '2' && input[2] == ';') {
				input = input.substr(3);
				if(input == "started") {
					vmWinStatus = VmWinStatus::RUNNING;
					vmStatusDisplay = "%{T2}A%{T1}";
					vmStatusDisplay = setOnClickCommand(vmStatusDisplay, "C2;toggle");
				}
				else if(input == "paused") {
					vmWinStatus = VmWinStatus::PAUSED;
					vmStatusDisplay = "%{T2}A%{T3}%{T1}";
					vmStatusDisplay = setOnClickCommand(vmStatusDisplay, "C2;toggle");
				}
				else if(input == "stopped") {
					vmWinStatus = VmWinStatus::OFF;
					vmStatusDisplay = "";
				}
				else if(input == "toggle") {
					if(vmWinStatus == VmWinStatus::PAUSED) {
						const char *argv[] = {"windows","r",NULL};
						runCommand(argv);
					}
					else if(vmWinStatus == VmWinStatus::RUNNING) {
						const char *argv[] = {"windows","p",NULL};
						runCommand(argv);
					}
				}
				vmStatusDisplay = setColor(vmStatusDisplay, 'F', "#0341fc");
				vmStatusDisplay = setColor(vmStatusDisplay, 'B', "#b09999");
			}
		}
		else if(input[0] == 'S') {
			string line = input.substr(1);
			line = pad(line);
			line = setColor(line,'F',COLOR_SYS_FG);
			line = setColor(line,'B',COLOR_SYS_BG);
			sys=line;
		}
		else if(input[0] == 'T') {
			if(input.length() == 1) noTitle = true;
			else noTitle = false;
			string line = input.substr(1);
			line = pad(line);
			line = setColor(line,'F',COLOR_TITLE_FG);
			line = setColor(line,'B',COLOR_TITLE_BG);
			title=line;
		}
		else if(input[0] == 'W') {
			wm = "";
			int screenCount = -1;
			string line = input.substr(1);
			//WMDP-1:OI:oII:fIII:fIV:fV:fVI:fVII:fVIII:LT:TT:G:mHDMI-2:OIX:fX:LT:TT:G
			auto strSplit = split(line,":");
			bool onFocusedMonitor = false;
			string fg = "";
			string bg = "";
			string ul = "";
			for(string item: strSplit) {
				string name = item.substr(1);
				if((item[0] == 'm') || (item[0] == 'M')) {
					screenCount++;
					if(item[0] == 'm') { 
						fg=COLOR_MONITOR_FG;
						bg=COLOR_MONITOR_BG;
						onFocusedMonitor = false;
					}
					else if(item[0] == 'M') { //on onfocused monitor
						fg = COLOR_FOCUSED_MONITOR_FG;
						bg = COLOR_FOCUSED_MONITOR_BG;
						onFocusedMonitor = true;
						focusedScreen = screenCount;
					}

					string monitorInfo = name;
					monitorInfo = pad(monitorInfo);
					monitorInfo = setColor(monitorInfo, 'F', fg);
					monitorInfo = setColor(monitorInfo, 'B', bg);
					monitorInfo = setOnClickCommand(monitorInfo, "C1;bspc;monitor;-f;"+name);
					wm += monitorInfo;
				}
				if(item[0] == 'F' || item[0] == 'f' || item[0] == 'O' || item[0] == 'o' || item[0] == 'U' || item[0] == 'u') {
					if(item[0] == 'f') {
						//free desktop
						fg = COLOR_FREE_FG;
						bg = COLOR_FREE_BG;
						ul = bg;
					}
					else if(item[0] == 'F') {
						if(onFocusedMonitor) {
							fg=COLOR_FOCUSED_FREE_FG;
							bg=COLOR_FOCUSED_FREE_BG;
							ul=bg;
						}
						else {
							fg=COLOR_FREE_FG;
							bg=COLOR_FREE_BG;
							ul=COLOR_FOCUSED_FREE_BG;
						}
					}
					else if(item[0] == 'o') {
						fg=COLOR_OCCUPIED_FG;
						bg=COLOR_OCCUPIED_BG;
						ul=bg;
					}
					else if(item[0] == 'O') {
						if(onFocusedMonitor) {
							fg=COLOR_FOCUSED_OCCUPIED_FG;
							bg=COLOR_FOCUSED_OCCUPIED_BG;
							ul=bg;
						}
						else {
							fg=COLOR_OCCUPIED_FG;
							bg=COLOR_OCCUPIED_BG;
							ul=COLOR_FOCUSED_OCCUPIED_BG;
						}
					}
					else if(item[0] == 'u') {
						fg=COLOR_URGENT_FG;
						bg=COLOR_URGENT_BG;
						ul=bg;
					}
					else if(item[0] == 'U') {
						if(onFocusedMonitor) {
							fg=COLOR_FOCUSED_URGENT_FG;
							bg=COLOR_FOCUSED_URGENT_BG;
							ul=bg;
						}
						else {
							fg=COLOR_URGENT_FG;
							bg=COLOR_URGENT_BG;
							ul=COLOR_FOCUSED_URGENT_BG;
						}
					}
					string desktopInfo = name;
					desktopInfo = pad(desktopInfo);
					desktopInfo = setOnClickCommand(desktopInfo, "C1;bspc;desktop;-f;"+name);
					desktopInfo = "%{U"+ul+"}%{+u}"+desktopInfo;
					desktopInfo = setColor(desktopInfo, 'F', fg);
					desktopInfo = setColor(desktopInfo, 'B', bg);
					desktopInfo += "%{-u}";
					wm += desktopInfo;

				}
				else if(item[0] == 'L' || item[0] == 'T' || item[0] == 'G'){
					//WMDP-1:OI:oII:fIII:fIV:fV:fVI:fVII:fVIII:LT:TT:G:mHDMI-2:OIX:fX:LT:TT:G
					string info = name;
					if(info == "T") {
						info = "%{T3}%{T1}";
					}
					info = pad(info);
					info = setColor(info, 'F', "#a3a098");
					info = setColor(info, 'B', COLOR_STATE_BG);
					wm += info;
				}

			}

		}
		DEBUG?cout<<"after last if\n":cout;
		/* string barout = direct+"%{l}"+wm+"%{c}"+title+"%{r}"+sys+closeButton; */
		string barout = direct+"%{l}"+wm+"%{r}"+vmStatusDisplay+sys;
		string scr0,scr1;
		scr0 = "%{S0}" + barout;
		scr1 = "%{S1}" + barout;
		string cb = "";
		if(!noTitle)
			cb = closeButton;
		/* char buffer[2]; */
		/* char *argv[] = {"bspc","query","-N","-n","focused"}; */
		/* runCommand(argv,buffer,2); */
		/* if(buffer[0] != 0) { */
		/* 	cb = closeButton; */
		/* } */
		/* DEBUG?cout<<buffer<<'\n':cout; */
		if(focusedScreen == 0) {
			scr0 +=  cb+"%{c}"+title;
		}
		else {
			scr1 +=  cb+"%{c}"+title;
		}
		string finalOutput = scr0 + scr1 + "\n";
		/* cout << finalOutput; */
		int ret = write(pfd[1], finalOutput.c_str(),finalOutput.length()+0);

		/* int ret = write(pfd[1], "hello world",finalOutput.length()+1); */
		DEBUG?cout<<"write returned: "<<ret<<"\n":cout;
		DEBUG?cout<<"final out:"<<finalOutput<<"\n\n\n\n":cout;

		/* cout << "%{S0}" << barout << "%{S1}" << barout << "\n"; */
		// Output the text from the file
	}

	DEBUG?cout<<"closing file.":cout;
	// Close the file
	close(pfd[1]);
	MyReadFile.close();
	return 0;
}

void runCommand(const char **argv, char *buffer, int bsize) {
	int pfd[2];
	if(buffer != nullptr) {
		if(pipe(pfd) == -1) {
			cout<<"pipe failed.\n";
			return;
		}
	}
	int pid = fork();
	if(pid == -1) {
		cout<<"fork failed.";
	}
	else if(pid == 0) {
		if(buffer != nullptr) {
			close(pfd[0]); //child won't read
			dup2(pfd[1],STDOUT_FILENO); //write to pipe instead of stdout
			dup2(pfd[1],STDERR_FILENO); //write to pipe instead of stderr
			close(pfd[1]); //stdout and err is set
		}
		execvp(argv[0], const_cast<char **>(argv));
		/* execvp(argv[0], const_cast<char **>(argv)); */
		perror("execvp failed");
	}
	else {
		//parent
		close(pfd[1]);//no write needed
		if(buffer != nullptr) {
			int r = read(pfd[0],buffer,bsize-1);
			buffer[r] = 0;
		}
		close(pfd[0]);//read done
		int status;
		waitpid(pid,&status,0);
	}
}

vector<string> split(const string &thiss, const string &delim) {
	int start = 0;
	int end = thiss.find(delim);
	vector<string> output;
	while (end != string::npos)
	{
		string part = thiss.substr(start, end - start);
		output.push_back(part);
		start = end + delim.length();
		end = thiss.find(delim, start);
	}
	output.push_back(thiss.substr(start, end));
	return output;
}

//never called
void toggle_winvm(){
	const char *argv[] = {"virsh", "-c", "qemu:///system", "list", NULL};
	char buffer[200];
	runCommand(argv, buffer, 200);
	string out = buffer;
	if(out.rfind("running") != string::npos) {
		const char *argv[] = {"windows", "p", NULL};
		runCommand(argv);
	}
	else if(out.rfind("paused") != string::npos) {
		const char *argv[] = {"windows", "r", NULL};
		runCommand(argv);
	}
}
int main() {
	return main2();
}
