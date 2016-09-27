/* Complexity: O(n^2)
 * 1. Sort the input array
 * 2. Fix the first element and reduce this three-sum problem to two-sum problem
 * 3. In the two-sum function, declare two pointers 
 * 4. If (nums[end] + nums[start]) > sum, end-- because strat++ will cause difference larger
 * 5. If (nums[end] + nums[start]) < sum, start++
 * 6. two-sum function will return the sum with minimum difference
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
    int twoSumCloset(int target, int start, int end, vector<int>& nums);
    int threeSumClosest(vector<int>& nums, int target);
	void dump();

private:
	vector<char> stack;;
	
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::twoSumCloset(int target, int start, int end, vector<int>& nums){
    int min;
    int tmp;
    int sum;
    int ptr0;
    int ptr1;
    
    min = abs(target - (nums[start] + nums[end]));
    
    while(start < end){
        sum = nums[start] + nums[end];
        tmp = abs(target - sum);
        
        if(tmp <= min){
            min = tmp;
            ptr0 = start;
            ptr1 = end;
            if(min == 0){
                return nums[ptr0] + nums[ptr1];
            }
        }
        
        if(sum > target){
            --end;
        }
        else{
            ++start;
        }
    }
    
    return (nums[ptr0] + nums[ptr1]);
}

int Solution::threeSumClosest(vector<int>& nums, int target) {
    int i;
    int size = nums.size();
    int min;
    int tmp;
    int ptr0;
    int twoSum;
    
    /* Sort input array */
    sort(nums.begin(), nums.end());
    
    min = abs(target - (nums[0] + nums[1] + nums[2]));
    
    for( i = 0; i < (size - 2); ++i){
        tmp = twoSumCloset(target - nums[i], (i + 1), (size - 1), nums);
        if(abs(target - tmp - nums[i]) <= min){
            min = abs(target - tmp - nums[i]);
            twoSum = tmp;
            ptr0 = i;
        }
    }
    
    return (twoSum + nums[ptr0]);
}

void Solution::dump(){
}

int main(){
	Solution sol;
	vector<int> nums;
	int i;
	int target;

	nums.push_back(2);
	nums.push_back(10);
	nums.push_back(-9);
	nums.push_back(1);
	nums.push_back(4);
   	
	for(i = 0; i < nums.size(); i++){
		cout << nums[i] << ",";
	}
	cout << endl;

	target = 9;
	cout << "target: " << target << endl; 
	cout << "closestSum: " << sol.threeSumClosest(nums, target) << endl; 
	
	return 0;
}
