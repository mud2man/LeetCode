/* Complexity: O(n)
 * 1. Traverse from left to right, and record the maximum index you can reach
 * 2. If the current position + nums[i] > max, which means it can jump further, update max
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
    bool canJump(vector<int>& nums);

private:
    
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

bool Solution::canJump(vector<int>& nums) {
    int max;
    int pos;
    
    if(nums.size() == 0){
        return false;
    }
    
    for(pos = 0, max = 0; pos < nums.size(); ++pos){
        if(((pos + nums[pos]) > max) && (max >= pos)){
            max = pos + nums[pos];
            if(max >= (nums.size() - 1)){
                return true;
            }
        }
    }
    
    if(max >= (nums.size() - 1)){
        return true;
    }
    else{
        return false;
    }
}

int main(){
    Solution sol;
    vector<int> nums;
	int i;

	nums.push_back(4);
	nums.push_back(2);
	nums.push_back(1);
	nums.push_back(2);
	nums.push_back(0);
	nums.push_back(2);

	cout << "nums:" << endl;
	for(i = 0; i < nums.size(); ++i){
		cout << nums[i] << ",";
	}
	cout << endl;

	cout << "canReach: " << sol.canJump(nums) << endl;

	return 0;
}
