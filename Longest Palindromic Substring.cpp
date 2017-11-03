class Solution {
public:
    string longestPalindrome(string s) {
        
        int len = s.length() ;
        int end = 1 ;
        int st = 0 ;
        int l ,h ;
        for(int i=1;i<len;++i){
            //even length string with  centers i-1,i ;
            l=i-1 ;
            h=i ;
            while(l>=0&&h<len&&s[l]==s[h]){
                if(h-l+1>end){
                    st = l ;
                    end  = h-l+1 ;
                }
                --l;
                ++h ;
            }
                //odd length string with center i 
                 l=i-1 ;
            h=i+1 ;
            while(l>=0&&h<len&&s[l]==s[h]){
                if(h-l+1>end){
                    st = l ;
                    end  = h-l+1 ;
                }
                --l;
                ++h ;
                
            }
        }
        
        string res = "" ;
        for(int i=st;i<st+end;i++){
            res+=s[i] ;
        }
        
        return res ;
        
    }
};
