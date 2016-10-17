/* Complexity = O(n)
 * 1. Traverse all the element and record all the number of bits
 * 2. Module the number of bit by 3 to get the answer
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
	void countBit(int num);
	int bitCountToNum(void);
	int singleNumber(vector<int>& nums);

private:
	int bitCount[32] = {0};
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

void Solution::countBit(int num){
    int i;
    
    for(i = 0; i < 32; ++i){
        if((num & (1 << i)) != 0){
            bitCount[i]++;
        }
    }
}

int Solution::bitCountToNum(void){
    int i;
    int num;
    
    for(i = 0; i < 32; ++i){
        bitCount[i] = bitCount[i] % 3;
    }
    
    for(i = 0, num = 0; i < 32; ++i){
        num = num + (bitCount[i] << i);
    }
    
    return num;
}

int Solution::singleNumber(vector<int>& nums) {
    int i;
    
    for(i = 0; i < nums.size(); ++i){
        countBit(nums[i]);
    }
    
    return bitCountToNum();
}

int main(){
    vector<int> nums;
    Solution sol;
	int single;

	nums.push_back(2);
	nums.push_back(2);
	nums.push_back(2);
	nums.push_back(3);
	nums.push_back(4);
	nums.push_back(4);
	nums.push_back(3);
	nums.push_back(4);
	nums.push_back(3);
	nums.push_back(5);

	cout << "the single number: " << sol.singleNumber(nums) << endl;
	
	return 0;
}
