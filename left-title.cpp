#!/usr/bin/env cpprunner
#include <iostream>
#include <fcntl.h>
#include <sys/wait.h>
#include <unistd.h>
using namespace std;
void runCommand(const char **argv, char *buffer, int bsize);
const char nl = '\n';
int main(int argc, char *argv[]) {
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
