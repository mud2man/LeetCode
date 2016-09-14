/* Complexity: O(n)
 * 1. Traverse the string and store the vowels into a stack
 * 2. Pop the stack and repalce the vowels when traverse the sting
 */

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
	string reverseVowels(string s);
	void dump();

private:
	vector<char> stack;;
	
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

string Solution::reverseVowels(string s){
	unsigned int i;
	string rs;
	
	rs = s;

	/* Scan s and store vowels into stack */
	for(i = 0; i < s.length(); i++){
		if((s[i] == 'a') || (s[i] == 'e') || (s[i] == 'i') || (s[i] == 'o') || 
		   (s[i] == 'u') || (s[i] == 'A') || (s[i] == 'E') || (s[i] == 'I') ||
		   (s[i] == 'O') || (s[i] == 'U')){
		   stack.push_back(s[i]);
		}
	}
	
	/* Store vowels in a reversed order */
	for(i = 0; i < rs.length(); i++){
		if((rs[i] == 'a') || (rs[i] == 'e') || (rs[i] == 'i') || (rs[i] == 'o') || 
		   (rs[i] == 'u') || (rs[i] == 'A') || (rs[i] == 'E') || (rs[i] == 'I') ||
		   (rs[i] == 'O') || (rs[i] == 'U')){

		   rs[i] = stack.back();
		   stack.pop_back();
		}
	}

	return rs;
}

void Solution::dump(){
	vector<char>::iterator it;

	for(it = stack.begin(); it != stack.end(); it++){
		cout << *it << ",";
	}
}

int main(){
	string s = "leetcode";
	string rs;
	Solution sol;
   	
	rs = sol.reverseVowels(s);

	cout << "s: " << s << endl; 
	cout << "reversed s: " << rs << endl; 
	
	return 0;
}
