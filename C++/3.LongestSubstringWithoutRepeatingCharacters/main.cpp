/* Complexity: O(n) 
 * 1. Traverse from right to left
 * 2. Use an array asciiRecord with size 96 the record the current used character
 * 3. If the next character cause no duplicate, then length++
 * 4. Otherwise, find the duplicate character, and update the asciiRecord and length
 */

#include <iostream>
#include <string>

using namespace std;

class Solution 
{
public:
	Solution();
	~Solution();
	int lengthOfLongestSubstring(string s);
private:
    int asciiRecord[96];

};/*End of class Solution */
Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::lengthOfLongestSubstring(string s) {
    int size = s.size();
    int i;
    int j;
    char c;
    int length;
    int preLength;
    int maxLength;
    
    /* Reset record */
    for(i = 0; i < 96; i++){
        this->asciiRecord[i] = 0;
    }
    length = 0;
    maxLength = 0;

    for(i = 0; i < size; i++){
        c = s[i];
        
        if(this->asciiRecord[c - ' '] == 0){
            this->asciiRecord[c - ' '] = 1;
            ++length;
            if(length > maxLength){
                maxLength = length;
            }
        }
        else{
            /* Reset record */
            for(j = 0; j < 96; j++){
                this->asciiRecord[j] = 0;
            }
            
            /* Reset length*/
            preLength = length;
			length = 0;
            
            /* Find the duplicate*/
            for(j = i - 1; j >= (i - preLength); --j){
                if( c == s[j]){
                    break;
                }
                else{
                    this->asciiRecord[s[j] - ' '] = 1;
                    ++length;
                }
            }
            
            this->asciiRecord[c - ' '] = 1;
            ++length;
        }
    }
    return maxLength;
}

int main()
{
	int maxLength;
    Solution sol;
    string s;
    
    s = "abcabcbb";
	cout << "s: " << s << endl;
    
	/* Obtain the solution */
	maxLength = sol.lengthOfLongestSubstring(s);

	cout << "maxLength: " << maxLength << endl;
	return 0;
}
