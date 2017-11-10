// HIGHEST RESPONSE RATIO SCHEDULING
#include<bits/stdc++.h>
using namespace std;
struct process
{	int at,bt,tat,wt;
	int id;
};
int tim;
bool cmp(const process &a, const process &b)
{	if(a.at<b.at)
		return true;
	else if(a.at==b.at)
		return a.id<b.id;
	return false;
}
bool com(const process &a, const process &b)
{	float x=(tim-a.at+a.bt)/a.bt;
	float y=(tim-b.at+b.bt)/b.bt;
	return x>y;
}
int main()
{	int n;
	cin>>n;
	process p[n];
	for(int i=0;i<n;i++)
	{	cin>>p[i].at>>p[i].bt;
		p[i].id=i;
	}		
	float total_tat=0.0,total_wt=0.0;
	sort(p,p+n,cmp);
	for(int i=0;i<n;i++)
	{	cout<<p[i].id<<" ";
		tim=max(tim,p[i].at);
		tim+=p[i].bt;
		int j;
		for(j=i+1;j<n;j++)
		{	if(p[j].at>tim)
				break;
		}
		p[i].wt=tim-p[i].at;	total_wt+=p[i].wt;
		p[i].tat=p[i].wt+p[i].bt;	total_tat+=p[i].tat;
		sort(p+i+1,p+j,com);
	}
	cout<<"\nAvg. TAT: "<<total_tat/n;
	cout<<"\nAvg. WT : "<<total_wt/n; 
	return 0;
}
