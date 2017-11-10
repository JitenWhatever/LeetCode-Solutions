#include<bits/stdc++.h>

using namespace std ;

struct process{
    int pid,at,bt,tat,rmbt,ct,wt ;
};

bool cmp(process a,process b){
    return a.at<b.at ;
}
bool icmp(process a,process b){
return a.pid<b.pid;
}
int main(){
    int n;
    cout<<" enter number of processes :\n";
            cin>>n;

   int qt=1 ;
   int prev=0;
    int cs=0 ;
 vector<int>wt(n,0);
 vector<int>rmbt(n,0);
 vector<int>ct(n,0);
 vector<int>rt(n,0);
int flag[n];
int restime[n] ;
    vector<process>v(n) ;
    cout<<"enter the details in following order pid ,at ,bt :\n";
            for(int i=0;i<n;i++){
                cin>>v[i].pid>>v[i].at>>v[i].bt;
                v[i].rmbt=v[i].bt ;
                flag[i]=0;
            }
    cout<<" Sequence :\n";
        int done=0,time=0;

            while(done<n){
                    int min_pid=-1,mm=INT_MAX ;
                    for(int i=0;i<n;i++){
                            if(v[i].at<=time&&v[i].rmbt!=0&&v[i].rmbt<mm){
                                mm=v[i].rmbt;
                                min_pid = i ;
                            }
                    }
                    if(min_pid!=-1){
                        if(flag[min_pid]==0){
                            restime[min_pid]=time ;
                            flag[min_pid] = 1 ;
                        }
                        if(prev!=v[min_pid].pid){
                            cout<<v[min_pid].pid<<"--";
                            prev = v[min_pid].pid ;cs++ ;
                        }
                        if(v[min_pid].rmbt>qt){
                            v[min_pid].rmbt-=qt ;
                            time+=qt ;//print
                        }
                        else{
                            time+=v[min_pid].rmbt;
                            v[min_pid].rmbt=0;
                            done++;
                            v[min_pid].ct=time ;
                            v[min_pid].wt=v[min_pid].ct-v[min_pid].at-v[min_pid].bt;
                        }
                    }
                    else
                        time++;
        }

        int ttat=0,twt=0;
        cout<<"!\n";
        cout<<"\t pid \t ct \t tat \t wt \n ";
                sort(v.begin(),v.end(),icmp);
                for(int i=0;i<n;i++){
                    //int pid = v[i].pid ;
                    v[i].tat = v[i].ct-v[i].at;
                    cout<<v[i].pid<<"\t"<<v[i].ct<<"\t"<<v[i].tat<<"\t"<<v[i].wt<<"\t"<<endl;
                    ttat+=v[i].tat;
                    twt+=v[i].wt;

                }
                cout<<(double)ttat/n<<endl;
                cout<<(double)twt/n<<endl;
               // cout<<(double)trt/n<<endl;
                cout<<cs-1<<endl;
                cout<<(double)n/time<<endl;
                cout<<"\n rt \n";
                for(int i=0;i<n;i++){
                    cout<<i+1<<"--"<<restime[i]-v[i].at<<endl;
                }

return 0;
}
