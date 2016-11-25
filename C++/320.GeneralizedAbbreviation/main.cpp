/* DFS + backtrack O(#abbs)
 * 1. Append the previous abb, and iterate all the possible abbreviations
 * ex: idx = 0 :{ w-ord,              1o-rd,           2r-d,     3d, 4} 
 *                 ||                   ||              ||
 *                 \/                   \/              \/
 *       {wo-rd, w1r-d, w2d, w3}  {1or-d, 1o1d, 1o2}  {2rd, 2r1}
 *               .....                 .....             ....
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
    
public:
	Solution();
	~Solution();
    void helper(vector<string> &abbs, string prevAbb, int idx, string& word);
    vector<string> generateAbbreviations(string word);
private:
};/*End of class Solution */

Solution::~Solution(){
}

Solution::Solution() {
}

void Solution::helper(vector<string> &abbs, string prevAbb, int idx, string& word){
    int i;
    string currAbb;
    
    if(idx == word.size()){
        abbs.push_back(prevAbb);
        return ;
    }
    
    for(i = 0; i < (word.size() - idx); ++i){
        currAbb.clear();
        currAbb = prevAbb;
        if(i > 0){
            currAbb.append(to_string(i));
        }
        currAbb.push_back(word[idx + i]);
        helper(abbs, currAbb, idx + i + 1, word);
    }
    
    prevAbb.append(to_string(word.size() - idx));
    abbs.push_back(prevAbb);
}

vector<string> Solution::generateAbbreviations(string word) {
    vector<string> abbs;
    
    helper(abbs, "", 0, word);
    
    return abbs;
}

int main()
{
    int i;
    vector<string> abbs;
	string word;
    Solution sol;

	word = "word";
    abbs = sol.generateAbbreviations(word);

	cout << abbs.size() << " abbreviations:" << endl;
	for(i = 0; i < abbs.size(); ++i){
		cout << abbs[i] << endl;
	}

	return 0;
}
