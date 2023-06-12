#include <cstdlib>
#include <iostream>
#include <fcntl.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fstream>
#include <vector>
#include <sstream>
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
using namespace std;
void runCommand(const char **argv, char *buffer=nullptr, int bsize=0);
vector<string> split(const string &thiss, const string &delim);
string setOnClickCommand(const string &data, const string &command) {
	return "%{A1:"+command+":}"+data+"%{A}";
}
char * getMonitorName();
inline string setColor(const string data, const char type, const string &col);

const char nl = '\n';
enum class VmWinStatus {
	RUNNING, PAUSED, OFF
};
int main() {
	/* char *t = getenv("XDG_RUNTIME_DIR"); */
	char *t = getenv("POLYBAR_CONTROLLER");
	string controllerFifo;
	if(t == NULL) {
		cout<<"POLYBAR_CONTROLLER env missing.";
		exit(-1);
	}
	else
		controllerFifo = t;
	t = getenv("XDG_RUNTIME_DIR");
	string filesDir;
	if(t == NULL) {
		cout<<"XDG_RUNTIME_DIR env missing.";
		exit(-1);
	}
	else
		filesDir = t;
	if(filesDir[filesDir.size() - 1] != '/')
		filesDir += '/';
	filesDir += "panel/";

	const string titleLeftFile = filesDir + "title-left";
	const string titleRightFile =  filesDir + "title-right";
	VmWinStatus vmWinStatus;
	string vmStatusDisplay;
	cout<<"got here1"<<endl;
	ifstream controllerIFS(controllerFifo);
	cout<<"got here2"<<endl;
	ofstream titleLeftFS(titleLeftFile);
	cout<<"got here3"<<endl;
	ofstream titleRightFS(titleRightFile);
	cout<<"got here4"<<endl;
	string input;
	cout<<"got here"<<endl;
	while (getline(controllerIFS, input)) {
		cout<<"input is:"<<input<<endl;
		if(input[0] == 'C') {
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
				const char *winOff[] = { "polybar-msg", "hook", "winstatus", "1", NULL};
				const char *winRunning[] = { "polybar-msg", "hook", "winstatus", "2", NULL};
				const char *winPaused[] = { "polybar-msg", "hook", "winstatus", "3", NULL};
				const char **winCurrent;
				if(input == "started") {
					vmWinStatus = VmWinStatus::RUNNING;
					winCurrent = winRunning;
				}
				else if(input == "paused") {
					vmWinStatus = VmWinStatus::PAUSED;
					winCurrent = winPaused;
				}
				else if(input == "stopped") {
					vmWinStatus = VmWinStatus::OFF;
					winCurrent = winOff;
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
				runCommand(winCurrent);
			}
		}
		else if(input[0] == 'T') {
			string line = input.substr(1);
			char *monitorName = getMonitorName();
			ofstream *title,*closebutton;
			if(monitorName[0] == 'D') { //DP-1 probably
				titleLeftFS.seekp(0);
				titleLeftFS << line <<endl;
				const char *closeButtonNotDisplay[] = { "polybar-msg", "hook", "closebutton-left", "1", NULL};
				const char *closeButtonDisplay[] = { "polybar-msg", "hook", "closebutton-left", "2", NULL};
				const char ** closeButtonHook;
				if(line.empty()) {
					closeButtonHook = closeButtonNotDisplay;
				}
				else {
					closeButtonHook = closeButtonDisplay;
				}
				const char *argv[] = { "polybar-msg", "hook", "title-left", "1", NULL};
				runCommand(argv);
				runCommand(closeButtonHook);
			}
			else if(monitorName[0] == 'H') { //HDMI-2 probably
				titleRightFS.seekp(0);
				titleRightFS << line << endl;
				const char *closeButtonNotDisplay[] = { "polybar-msg", "hook", "closebutton-right", "1", NULL};
				const char *closeButtonDisplay[] = { "polybar-msg", "hook", "closebutton-right", "2", NULL};
				const char ** closeButtonHook;
				if(line.empty()) {
					closeButtonHook = closeButtonNotDisplay;
				}
				else {
					closeButtonHook = closeButtonDisplay;
				}
				const char *argv[] = { "polybar-msg", "hook", "title-right", "1", NULL};
				runCommand(argv);
				runCommand(closeButtonHook);
			}
		}
	}
	return 0;
}
char out[20];
char * getMonitorName() {
	const char *bspc[] = {"bspc", "query", "-M", "--names", "-m", "focused" , NULL};
	runCommand(bspc, out, 20);
	return out;
}
int main2(int argc, char *argv[]) {
	char out[20];
	const char *bspc[] = {"bspc", "query", "-M", "--names", "-m", "focused" , NULL};
	int xtpfd[2]; //0 - read
	int pret = pipe(xtpfd);
	int xtpid = fork();
	char monitorStart = argv[2][0];
	if(xtpid == -1) {
		perror("fork before xtitle");
		return 2;
	}
	else if(xtpid == 0) {
		//child
		close(xtpfd[0]); //no reading
		dup2(xtpfd[1], STDOUT_FILENO); //write to pipe
		execl("/home/navn/aur/bspwm-tools/bin/xtitle","xtitle","-s",NULL);
		perror("exec xtitle");
		return 3;
	}
	else {
		//parent
		char buffer[200];
		buffer[199] = 0;
		close(xtpfd[1]);//no writing
		while(int r = read(xtpfd[0],buffer,199)) {
			buffer[r] = 0;
			runCommand(bspc, out, 20);
			if(out[0] == monitorStart) //assume DP-1
			/* if(out[0] == 'D') //assume DP-1 */
				cout << buffer<<flush;
				/* printf("%s\n",buffer); */
		}
	}
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
		cout<<argv;
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
		waitpid(pid,0,0);
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
inline string setColor(const string data, const char type, const string &col) {
	string color = col;
	if(col[0] != '#') {
		color = "#"+color;
	}
	stringstream ss;
	ss << "%{" << type << color << "}" << data << "%{" << type <<"-}";
	return ss.str();
}
