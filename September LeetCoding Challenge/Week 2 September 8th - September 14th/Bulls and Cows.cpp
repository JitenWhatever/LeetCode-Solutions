/*
You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. 
Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and 
how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:

Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
Example 2:

Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
*/

class Solution {
public:
    string getHint(string s, string g) {
        vector<int> hash1(10, 0), hash2(10, 0);        
        int bulls = 0;
        
        for(int index = 0; index < s.length(); ++index) {
            if(s[index] == g[index]) {
                ++bulls;
            }
            else {
                hash1[s[index] - '0']++;
                hash2[g[index] - '0']++;
            }
        }
        
        int cows = 0;
        for(int index = 0; index < 10; index++) {
            cows += min(hash1[index], hash2[index]);
        }
        
        return to_string(bulls) + "A" + to_string(cows) + "B";
    }
};