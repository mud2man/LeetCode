/* Complexity: O(n)
 * 1. Create a "lcp" to store the current longest common string
 * 2. Traverse from left to right, to get the intersection with every string
 */

#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

class Solution 
{
public:
	Solution();
	~Solution();
	string longestCommonPrefix(vector<string>& );

private:
	void helper (string&, string&);

};/*End of class Solution */

Solution::Solution(){
};

Solution::~Solution(){
};


void Solution::helper (string& lcp, string& s){
    int size;
    int i;
    
    size = min(lcp.size(), s.size());
    
    for(i = 0; i < size; ++i){
        if(lcp[i] != s[i]){
            break;
        }
    }
    
    if( i < lcp.size() ){
        lcp.erase(lcp.begin() + i, lcp.end());
    }
}

string Solution::longestCommonPrefix(vector<string>& strs) {
    int i;
    string lcp;
    
    if(strs.size() == 0){
        return lcp;
    }
    
    lcp = strs[0];
    
    for(i = 1; i < strs.size(); ++i){
        helper(lcp, strs[i]);
    }
    
    return lcp;
}

int main(){
	vector<string> strs;
    Solution sol;
	int i;
    
    strs.push_back("aba");
    strs.push_back("abad");
    strs.push_back("ab");

	cout << "strings:" << endl;
	for(i = 0; i < strs.size(); i++){
		cout << strs[i] << endl;;
	}

	cout << "Longest prefix array: " << sol.longestCommonPrefix(strs) << endl;

	return 0;
}
