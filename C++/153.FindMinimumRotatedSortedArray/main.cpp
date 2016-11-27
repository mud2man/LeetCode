/* Binary search: O(logn)
 * ex: 4 5 6 7 0 1 2
 *
 * 1. Case1: /|  ,return
 *            |/
 *            *
 *
 *             /|
 *            * |
 * 2. Case2:    |/, move right
 *
 *             
 *            /|  
 * 3. Case3: / | *, move left
 *             |/
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
    
public:
	Solution();
	~Solution();
    int findMin(vector<int>& nums);
private:
};/*End of class Solution */

Solution::~Solution(){
}

Solution::Solution() {
}

int Solution::findMin(vector<int>& nums) {
    int startIdx;
    int endIdx;
    int minIdx;
    
    startIdx = 0;
    endIdx = nums.size() - 1;
    
    if(nums.size() == 1){
        nums[0];
    }
    
    while(startIdx <= endIdx){
        minIdx = (startIdx + endIdx) / 2;
        
        if(minIdx == 0){
            if(nums[minIdx] < nums[minIdx + 1]){
                break;
            }
            else{
                startIdx = minIdx + 1;
            }
        }
        else if (minIdx == (nums.size() - 1)){
            if(nums[minIdx] < nums[minIdx - 1]){
                break;
            }
            else{
                endIdx = minIdx - 1;
            }
        }
        else if((nums[minIdx] < nums[minIdx + 1]) && (nums[minIdx] < nums[minIdx - 1])){
            break;
        }
        else{
            if((nums[minIdx] < nums[startIdx]) && (nums[minIdx] < nums[endIdx])){
                endIdx = minIdx - 1;
            }
            else if((nums[minIdx] >= nums[startIdx]) && (nums[minIdx] > nums[endIdx])){
                startIdx = minIdx + 1;
            }
            else if((nums[minIdx] < nums[startIdx]) && (nums[minIdx] < nums[endIdx])){
                endIdx = minIdx - 1;
            }
            else{
                endIdx = minIdx - 1;
            }
        }
    }
    
    return nums[minIdx];
}

int main()
{
    Solution sol;
    vector<int> nums;
	int min;
	int i;
    
    nums.push_back(4);
	nums.push_back(5);
	nums.push_back(6);
	nums.push_back(7);
	nums.push_back(0);
	nums.push_back(1);
	nums.push_back(2);
 	
	cout << "nums: ";
	for(i = 0; i < nums.size(); ++i){
		cout << nums[i] << "," ;
	}
	cout << endl;

 	min = sol.findMin(nums);
	cout << "min: " << min  << endl;

	return 0;
}
