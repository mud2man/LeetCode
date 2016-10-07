/* Use prime number to caculate signature of every string, and use map to store
 * 
 */

#include <iostream>
#include <vector>
#include <map>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
    vector< vector<string> > groupAnagrams(vector<string>& strs);

private:
    int prime[26] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
    vector< vector<string> > anagrams;
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

vector< vector<string> > Solution::groupAnagrams(vector<string>& strs) {
    int i;
    int j;
    int key;
    string str;
    map<int, vector<string> > map;
    
    for(i = 0; i < strs.size(); ++i){
        str = strs[i];
        key = 1;
        for( j = 0; j < str.size(); j++){
            key = key*prime[str[j] - 'a'];
        }
        map[key].push_back(str);
    }
    
    for(auto& x: map){
        anagrams.push_back(x.second);
    }
    
    return this->anagrams;
}
int main(){
    Solution sol;
	vector<string> strs;
	vector< vector<string> > anagrams;
	int i;
	int j;

	strs.push_back("tea");
	strs.push_back("eat");
	strs.push_back("tan");
	strs.push_back("nat");
	strs.push_back("bat");
	
	anagrams = sol.groupAnagrams(strs);

	cout << "input: " << endl;
	for(i = 0; i < strs.size(); ++i){
		cout << strs[i] << ",";
	}
	cout << endl;

	cout << "anagrams: " << endl;
	for(i = 0; i < anagrams.size(); ++i){
		for(j = 0; j < anagrams[i].size(); ++j){
			cout << anagrams[i][j] << ",";
		}
		cout << endl;
	}
	
	return 0;
}
