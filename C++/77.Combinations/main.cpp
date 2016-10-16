/* Use Backtracking, complexity:O(#answer)
 * 1. record start(first index), end(last index), and residure(how many needed to be selected)
 * 2. Iterate every current element, until the number od left element is equel to "residure"
 * 3. Call recursive helper function with start = i + 1, and residure--
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
    vector< vector<int> > combine(int n, int k);
    void helper(vector<int> seq, int start, int end, int residure);

private:
    vector< vector<int> > com;
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

void Solution::helper(vector<int> seq, int start, int end, int residure){
    int i;
    
    residure--;
    
    for(i = start; i <= (end - residure); ++i){
        seq.push_back(i);
        
        if(residure == 0){
            com.push_back(seq);
        }
        else{
            helper(seq, i + 1, end, residure);
        }
        
        seq.pop_back();
    }
}

vector< vector<int> > Solution::combine(int n, int k) {
    vector<int> seq;
    
    helper(seq, 1, n, k);
    
    return com;
}

int main(){
    vector< vector<int> > combinations;
    vector<int> seq;
    Solution sol;
	int n;
	int k;
	int i;
	int j;

	n = 4;
	k = 2;

	combinations = sol.combine(n, k);
	
	for(i = 0; i < combinations.size(); ++i){
		seq = combinations[i];
		for(j = 0; j < seq.size(); ++j){
			cout << seq[j] << ",";
		}
		cout << endl;
	}
	return 0;
}
