/* Use devide and counquer, Complexity: O(nlogn)
 * 1. Caculate the maximum subarray containing nums[m]
 * 2. Return the maximum subarray between left side, middle side, and right side  
 */

#include <iostream>
#include <vector>
#include <cmath>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
    int dccHelper(vector<int>& nums, int l, int r);
    int maxSubArray(vector<int>& nums);

private:
    
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::dccHelper(vector<int>& nums, int l, int r){
    int m;
    int leftMax;
    int rightMax;
    int i;
    int sum;
    int medianMax;
    
    m = (l + r) /2;
    
    /* caculate maximum sequence containing nums[m] */
    for(i = m + 1, rightMax = 0, sum = 0; i <= r; ++i){
        sum = sum + nums[i];
        if(sum > rightMax){
            rightMax = sum;
        }
    }
    
    for(i = m - 1, leftMax = 0, sum = 0; i >= l; --i){
        sum = sum + nums[i];
        if(sum > leftMax){
            leftMax = sum;
        }
    }
    
    medianMax = rightMax + leftMax + nums[m];
    
    /* caculate the maximum sequence without containing nums[m] */
    if(l <= (m -1)){
        leftMax = dccHelper(nums, l , m - 1);
        medianMax = max(leftMax, medianMax);
    }
    
    if( r >= (m + 1)){
        rightMax = dccHelper(nums, m + 1 , r);
        medianMax = max(rightMax, medianMax);
    }
    
    return medianMax;
}

int Solution::maxSubArray(vector<int>& nums) {
    return dccHelper(nums, 0, nums.size() - 1);
}

int main(){
    Solution sol;
    vector<int> nums;
	int i;

	nums.push_back(-2);
	nums.push_back(1);
	nums.push_back(-3);
	nums.push_back(4);
	nums.push_back(-1);
	nums.push_back(2);
	nums.push_back(1);
	nums.push_back(-5);
	nums.push_back(4);	


	cout << "nums:" << endl;
	for(i = 0; i < nums.size(); ++i){
		cout << nums[i] << ",";
	}
	cout << endl;

	cout << "Max: " << sol.maxSubArray(nums) << endl;

	return 0;
}
