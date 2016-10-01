/*  
 * 1. Traverse from left to right and record the maximum palindrome
 * 2. Return the palindrome with maximum length
 */

#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Solution 
{
public:
	Solution();
	~Solution();
	string longestPalindrome(string s);
private:

};/*End of class Solution */
Solution::Solution(){
}

Solution::~Solution(){
}

string Solution::longestPalindrome(string s){
    int i;
    int j;
    int interval;
    int size;
    int maxlen;
    int longestIdx;
    int headIdx;
    vector<int> len(s.size(), 1);
    string pal;
    
    size = s.size();
    maxlen = 0;
    
    for(i = 0; i < s.size(); i++){
        /* case1 */
        interval = 1;
        while(((i - interval) >= 0) && ((i + interval) < size) &&
              (s[i - interval] == s[i + interval])){
            ++interval;
        }
        --interval;
        len[i] = interval*2 + 1;
        
        /* case2 */
        interval = 1;
        while( ((i - interval + 1) >= 0) && ((i + interval) < size) &&
              (s[i - interval + 1] == s[i + interval])){
            ++interval;
        }
        --interval;
        
        if(interval*2 > len[i]){
            len[i] = interval*2;
        }
        
        if(maxlen < len[i]){
            maxlen = len[i];
            longestIdx = i;
        }
    }
    
    if((maxlen % 2) == 0){
        headIdx = longestIdx - (maxlen/2 - 1);
        i = 0;
        while(i < maxlen){
            pal.push_back(s[headIdx]);
            headIdx++;
            i++;
        }
    }
    else{
        headIdx = longestIdx - (maxlen - 1)/2 ;
        i = 0;
        while(i < maxlen){
            pal.push_back(s[headIdx]);
            headIdx++;
            i++;
        }
    }
    
    return pal;
}

int main()
{
    Solution sol;
    string s = "ababc";
    string longestPal;
   	
	cout << "s: " << s << endl;
    
	/* Obtain the solution */
	longestPal = sol.longestPalindrome(s);

	cout << "Longest Palindrome: " << longestPal << endl;

	return 0;
}
