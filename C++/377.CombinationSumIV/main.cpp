/* Use Dynamic Programing: O(target*nums.size())
 * 1. Caculate from target=1 to target=n - 1 to get target = n
 */

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
	int combinationSum4(vector<int>&, int);
	void dump();

private:
	
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::combinationSum4(vector<int>& nums, int target) {
    int i;
    int j;
    vector<int> cpnums = nums;
    vector<int> dp (target + 1, 0);
    
    if(nums.size() == 0){
        return 0;
    }
    
    sort(cpnums.begin(), cpnums.end());
    
    for(i = 1; i <= target; i++){
        for(j = 0; (cpnums[j] < i) && (j < cpnums.size()); j++){
            dp[i] += dp[i - cpnums[j]];
        }
        
        if(cpnums[j]  == i){
            ++dp[i];
        }
    }
    
    return dp[target];
}

void Solution::dump(){
}

int main(){
	vector<int> nums;
	Solution sol;
	int target;

	target = 4;
	nums.push_back(1);
	nums.push_back(2);
	nums.push_back(3);
   	
	cout << "target: " << target << " , ";
	cout << "#combinations: " << sol.combinationSum4(nums, target) << endl;
	
	return 0;
}
