#include<bits/stdc++.h>

using namespace std ;

struct process{
    int pid,at,bt,tat ;
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

   int qt ;
     cout<<"enter time quantum:\n";
            cin>>qt ;
 vector<int>wt(n,0);
 vector<int>rmbt(n,0);
 vector<int>ct(n,0);
 vector<int>rt(n,0);
    int cs=-1 ;
    vector<process>v(n) ;
    cout<<"enter the details in following order pid ,at ,bt :\n";
            for(int i=0;i<n;i++){
                cin>>v[i].pid>>v[i].at>>v[i].bt;
            }
    cout<<" Sequence :\n";
        sort(v.begin(),v.end(),cmp);

        for(int i=0;i<n;i++){
            rmbt[v[i].pid] = v[i].bt ;
        }
        int done=0,time =0,j=0;
        queue<process>q;
        while(j<n&&v[j].at<=time)
            q.push(v[j++]);
            while(done<n){
                if(!q.empty()){
                    process tp=q.front() ;
                    q.pop() ;
                    int pid = tp.pid ;
                    //gantt chart
                    cout<<pid<<"--";    cs++;
                    if(rmbt[pid]==tp.bt){
                        rt[pid]=time-tp.at ;
                    }
                    if(rmbt[pid]>qt){
                        rmbt[pid]-=qt ;
                        time+=qt;
                    }
                else{
                    time+=rmbt[pid];
                    rmbt[pid]=0;
                    done++;
                    ct[pid]=time ;
                    wt[pid]=ct[pid]-tp.at-tp.bt ;
                }
                while(j<n&&v[j].at<=time)
                    q.push(v[j++]);
                if(rmbt[pid])
                    q.push(tp);
            }
            else{
                    time++;
                while(j<n&&v[j].at<=time)
                    q.push(v[j++]);

            }

        }

        int ttat=0,twt=0,trt=0;
        cout<<"!\n";
        cout<<"\t pid \t ct \t tat \t wt \t rt \n ";
                sort(v.begin(),v.end(),icmp);
                for(int i=0;i<n;i++){
                    int pid = v[i].pid ;
                    v[i].tat = ct[pid]-v[i].at;
                    cout<<v[i].pid<<"\t"<<ct[pid]<<"\t"<<v[i].tat<<"\t"<<wt[pid]<<"\t"<<rt[pid]<<endl;
                    ttat+=v[i].tat;
                    twt+=wt[pid];
                    trt+=rt[pid] ;
                }
                cout<<(double)ttat/n<<endl;
                cout<<(double)twt/n<<endl;
                cout<<(double)trt/n<<endl;
                cout<<cs<<endl;
                cout<<(double)n/time<<endl;

return 0;
}
