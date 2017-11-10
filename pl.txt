#include<bits/stdc++.h>

    using namespace std ;

int LRU(int time[], int n){
    int min = time[0], pos = 0;

    for(int i = 1; i < n; ++i){
        if(time[i] < min){
            min= time[i];
            pos = i;
        }
    }

    return pos;
}

int main(){
	string page;
	cout<<"Enter The Page Sequence : \n";
	cin>>page;

	int frame ;
	cout<<"Enter The Number of Frames :\n";
	cin>>frame;

	int frm[frame],time[10],flag,iflag,pos ;
	int pgFault = 0 ,count=0;
	 for(int i = 0; i <frame; ++i)
        	frm[i] = -1;

        for(int i = 0; i < page.length(); ++i){
        	flag = iflag = 0;
         for(int j = 0; j < frame; ++j){
            if(frm[j] == page[i]){
                count++;
                time[j] = count;
                   flag = iflag = 1;
                   break;
               }
        }


    if(flag == 0){
            for(int j = 0; j < frame; ++j){
                	if(frm[j] == -1){
                  	  count++;
                    	pgFault++;
                   	 frm[j] = page[i];
                   	 time[j] = count;
                   	 iflag = 1;
                   	 break;
                }
            }
        }

	if(iflag == 0){
            pos = LRU(time,frame);
            count++;
            pgFault++;
            frm[pos] = page[i];
            time[pos] = count;
        }

        cout<<"\n";
       for(int j = 0; j < frame; ++j){
            cout<<"\t"<<frm[j];
       }
        }

	cout<<"\nTotal Page Fault :\n"<<pgFault<<endl;
return 0;
}
