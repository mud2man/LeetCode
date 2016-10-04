/* Use recursive method
 * 1. Create a hash table to store the mapping table from bottom 2 to bottom 9
 * 2. Enter the recursive function digit2letter with 2 input parameters "digits" and previous translated string 
 * 3. Call the digit2letter three times respectively, with three different characters mapped to the digit  
 */

#include <iostream>
#include <vector>
#include <algorithm> 

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
	void helper(vector<int>& candidates, vector<int>& seq, int position, int target);
	vector< vector<int> > combinationSum(vector<int>& candidates, int target);
	void dump();

private:
    vector<int> cloneCandidates;
    vector< vector<int> > ans;
	int count;

};/*End of class Solution */

Solution::Solution(){
	this->count = 0;
}

Solution::~Solution(){
}

void Solution::dump(){
	int i;
	int j;
	vector<int> seq;
	
	cout << "#helper: " << this->count << endl;

	for(i = 0; i < ans.size(); i++){
		seq = ans[i];
		for(j = 0; j < seq.size(); j++ ){
			cout << seq[j] << ",";
		}
		cout << endl;
	}
}

void Solution::helper(vector<int>& candidates, vector<int>& seq, int position, int target){
    int i;
    int j;
    vector<int> *branchSeq;
   
   	this->count++;
    if(position == candidates.size()){
        return;
    }
   	
    for(i = 0; (i * candidates[position]) < target; ++i){
        branchSeq = new vector<int>();
        *branchSeq = seq;
        
        for(j = 0; j < i; ++j){
            branchSeq->push_back(candidates[position]);
        }
        
        helper(candidates, *branchSeq, position + 1, target - (i * candidates[position]));
    }
    
    
    if((i * candidates[position]) == target){
        for(j = 0; j < i; ++j){
            seq.push_back(candidates[position]);
        }
        this->ans.push_back(seq);
    }
    
    seq.clear();
}

vector< vector<int> > Solution::combinationSum(vector<int>& candidates, int target) {
    vector<int> seq;
    
    this->cloneCandidates = candidates;
    sort(this->cloneCandidates.begin(), this->cloneCandidates.end());
    helper(this->cloneCandidates, seq, 0, target);
    
    return this->ans;
}

int main(){
	Solution sol;
	int i;
	vector<int> candidates;
	vector< vector<int> > ans;
	int target;

	candidates.push_back(2);
	candidates.push_back(3);
	candidates.push_back(6);
	candidates.push_back(7);
	target = 7;
	
	for(i = 0; i < candidates.size(); ++i){
		cout << candidates[i] << ",";
	}
	cout << endl;
	
	ans = sol.combinationSum(candidates, target);

	cout << "#ans: " << ans.size() << endl;
	sol.dump();

	return 0;
}
