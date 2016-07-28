/* 
 * 1. Check the first two element, to determine if nums[1] is a peak
 * 2. Traverse the array from left to right with three elements per time
 * 3. Check the last two element, to determine if nums[n-1] is a peak
 */

#include <iostream>
#include <vector>
#include <algorithm>    // std::max 

using namespace std;

class Solution 
{
public:
	Solution(void);
	~Solution(void);
	int findPeakElement(vector<int>& nums);
	void dump(vector<int>& nums);

private:

};/*End of class Solution */

Solution::Solution(void)
{
}

Solution::~Solution(void)
{
}

int Solution::findPeakElement(vector<int>& nums)
{
	int dummy0;
	int dummy1;
	int size;
	int idx;
	vector<int>::iterator it;

	size = nums.size();
	it = nums.begin();
	
	/* Insert the infinite negative to the head and bottom */
	dummy0 = nums[0] - 1;
	dummy1 = nums[size - 1] - 1;
	nums.insert(it, dummy0);
	nums.push_back(dummy1);

	/* Traverse the modified nums, and return the peak element with right shift 1 */
	for(idx = 1; idx <= size; idx++)
	{
		if((nums[idx] > nums[idx - 1]) && (nums[idx] > nums[idx + 1]))
		{
			nums.pop_back();
			nums.erase(nums.begin());
			return idx - 1;
		}
	}

	return 0;
}

void Solution::dump(vector<int>& nums)
{
	vector<int>::iterator it;

	cout << "input nums: " << endl;
	for(it = nums.begin(); it != nums.end(); it++)
	{
		cout << *it << ",";
	}
	cout << endl;
}

#define ARRAYSIZE 4

int main()
{
	int array[] = {1, 4, 3, 1};
	vector<int> nums;
	nums.assign(array, array + ARRAYSIZE);
	Solution sol;
	int peakid;
	
	for (int i = 0; i < 1000000; i++)
	{
		/* Obtain the peak element */
		peakid = sol.findPeakElement(nums);
	}

	sol.dump(nums);
	cout << "Peak element: " << peakid << endl;

	return 0;
}
