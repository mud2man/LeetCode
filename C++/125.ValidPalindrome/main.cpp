/* Complexity = O(n)
 */

#include <iostream>
#include <vector>
#include <ctype.h>


using namespace std;


class Solution {

public:
	Solution();
	~Solution();
    bool isPalindrome(string s);

private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

bool Solution::isPalindrome(string s) {
    int headPtr;
    int tailPtr;
    char c0;
    char c1;
    
    headPtr = 0;
    tailPtr = s.size() - 1;
    do{
        /*get the character from head */
        while((headPtr < s.size()) && (!isalnum(s[headPtr]))){
            headPtr++;
        }
        c0 = s[headPtr];
        
        /*get the character from tail */
        while((tailPtr >= 0) && (!isalnum(s[tailPtr]))){
            tailPtr--;
        }
        c1 = s[tailPtr];
        
        if(headPtr < tailPtr){
            if(isalpha(c0)){
                c0 = tolower(c0);
            }
            
            if(isalpha(c1)){
                c1 = tolower(c1);
            }
            
            if(c0 == c1){
                tailPtr--;
                headPtr++;
                continue;
            }
            else{
                return false;
            }
        }
    }while(headPtr < tailPtr);
    
    return true;
}

int main(){
    //string s = "A man, a plan, a canal: Panama";
    string s = "race a car";
    Solution sol;

	cout << "isPalindrome: " << sol.isPalindrome(s) << endl;
	
	return 0;
}
