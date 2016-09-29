/* Use recursive method: Complexity: O(n)
 * 1. If the #lefts==n, complete the string and push into answer
 * 2. If the #lefts<n, push "(" into string and call the method 
 * 3. If #right < #left, push ")" into string and call the method
 */

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution 
{
public:
	Solution();
	~Solution();
	void recursiveHelper(string s, int lefts, int rights);
	void dump(void);
	vector<string> generateParenthesis(int n);
private:
    int upperBond;
	vector<string> ans;
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

void Solution::recursiveHelper(string s, int lefts, int rights){
    int i;
    
    if(lefts == this->upperBond){
        for(i = 0; i < (this->upperBond - rights); ++i){
            s.push_back(')');
        }
        this->ans.push_back(s);
        return ;
    }
    else{
        s.push_back('(');
        recursiveHelper(s, lefts + 1, rights);
        s.pop_back();
    }
    
    if(rights < lefts){
        s.push_back(')');
        recursiveHelper(s, lefts, rights + 1);
    }
}

void Solution::dump(void){
    int i;
    string s;
    
    for(i = 0; i < this->ans.size(); i++){
        cout << ans[i] << endl;
    }
}

vector<string> Solution::generateParenthesis(int n) {
    string s;
    this->upperBond = n;
    
    recursiveHelper(s, 0, 0);
    
    return ans;
}

int main()
{
	int n;
    Solution sol;

	n = 3;
   	
	sol.generateParenthesis(n);
	sol.dump();
	
	return 0;
}
