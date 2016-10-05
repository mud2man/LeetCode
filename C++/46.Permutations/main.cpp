/* Use recursive
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
	void helper(vector<int>* permutation, vector<int>& nums);
	vector< vector<int> > permute(vector<int>& nums);
	void dump();

private:
    vector< vector<int> > permutations;

};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

void Solution::helper(vector<int>* permutation, vector<int>& nums){
    int i;
    int tmp;
    vector<int> *branchPermutation;
    
    for(i = 0; i < nums.size(); i++){
        branchPermutation = new vector<int>(0);
        *branchPermutation = *permutation;
        branchPermutation->push_back(nums[i]);
        
        if(nums.size() == 1){
            permutations.push_back(*branchPermutation);
            delete branchPermutation;
            return;
        }
        
        tmp = nums[i];
        nums.erase(nums.begin() + i);
        helper(branchPermutation, nums);
        nums.insert(nums.begin() + i, tmp);
    }
}

vector< vector<int> > Solution::permute(vector<int>& nums) {
    vector<int>* permutation;
    
    permutation = new vector<int>();
    
    helper(permutation, nums);
    
    return permutations;
}

void Solution::dump(){
	int i;
	int j;
	vector<int> permutation;

	for(i = 0; i < this->permutations.size(); ++i){
		permutation = this->permutations[i];
		
		for(j = 0; j < permutation.size(); ++j){
			cout << permutation[j] << ",";
		}

		cout << endl;
	}
}

int main(){
    Solution sol;
    int i;
    vector< vector<int> > permutations;
	vector<int> nums;

	nums.push_back(1);
	nums.push_back(2);
	nums.push_back(3);

	cout << "nums: ";
	for(i = 0; i < nums.size(); ++i){
		cout << nums[i] << ",";
	}
	cout << endl;
	
	permutations = sol.permute(nums);

	sol.dump();

	return 0;
}
