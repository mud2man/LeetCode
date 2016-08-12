/* 
 * 1. Sort and select
 */

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution 
{
public:
	Solution();
	~Solution();
	int findKthLargest(vector<int>& nums, int k); 
	void dump(void);

private:
};/*End of class Solution */

Solution::Solution()
{
}

Solution::~Solution()
{
}

int Solution::findKthLargest(vector<int>& nums, int k) 
{
	unsigned int size;
	vector<int> tmpnums;
	int idx;

	tmpnums = nums;
	size = tmpnums.size();
	
	sort(tmpnums.begin(), tmpnums.end());
	
	idx = size - k;
	
	return tmpnums[idx];
}


void Solution::dump(void)
{
}

#define ARRAYSIZE 6

int main()
{

	int array[] = {3, 2, 1, 5, 6, 4};
	vector<int> nums;
	Solution sol;
	int kthelmt;
	
	nums.assign(array, array + ARRAYSIZE);

	kthelmt = sol.findKthLargest(nums, 2);

	cout << "kthelmt: " << kthelmt << endl;

	/* Dump the answer */
	sol.dump();

	return 0;
}
