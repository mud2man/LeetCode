/* 
 * 1. Extract all the subarray without zero into a subarray list
 * 2. Obtain all the maximum product in the subarray list, there are only three cases 
 * 	2.1. the number of negative integer in the subarray is even, maximum = product them all
 *	2.2. the number of negative integer is odd, there are 4 products need be compared, ex
 *  2.2.1. ex: 2, -3, -2, 4, -3, 2, 8
 *  2.2.2. product0(product until reach a negative from left): 2
 *  2.2.3. product1(product of the remaining element except the negative): (-2)*4*(-3)*2*8
 *  2.2.4. product0(product until reach a negative from right): 2*8
 *  2.2.5. product1(product of the remaining element except the negative): 2*(-3)*(-2)*4
 * 3. Obtain the maximum from the maxlist 
 */

#include <iostream>
#include <vector>
#include <new>
#include <utility>    
#include <algorithm>    // std::max 

using namespace std;

#define ARRAYSIZE 8

class Solution 
{
public:
	Solution(void);
	~Solution(void);
	int maxProduct(vector<int>& nums);
	void extSubarray(vector<int>& nums);
	int caclMax(vector<int>& nums, pair<int, int> &subarray);
	void dump(vector<int>& nums);

private:
	/* Subarray list */
	vector< pair<int, int> >subarrays;
	
	/* Maximum product list */
	vector<int> maxList;

};/*End of class Solution */

Solution::Solution(void)
{
}

Solution::~Solution(void)
{
	maxList.clear();
	subarrays.clear();
}

int Solution::maxProduct(vector<int>& nums)
{
	unsigned int idx;
	int maxprod;

	maxList.clear();
	subarrays.clear();

	/* Corner case 1: Only 1 element */
	if(nums.size() == 1)
	{
		return nums[0];
	}

	/* Extract all the subarray without zero */
	extSubarray(nums);
	
	/* Obtain all the local maximun */
	for(idx = 0; idx < subarrays.size(); idx++)
	{
		maxList.push_back(caclMax(nums, subarrays[idx]));
	}
	
	/* Obtain the global maximun */
	maxprod = maxList[0];
	for(idx = 1; idx < maxList.size(); idx++)
	{
		maxprod = max(maxList[idx], maxprod);
	}
	
	/* Corner case 2: all the local maximum are negative, and zero exist */
	if((maxprod < 0) && (subarrays.size() > 0))
	{
		return 0;
	}

	return maxprod;
}

void Solution::extSubarray(vector<int>& nums)
{
	int idx;
	int head;
	int tail;
	pair<int, int> subarray;
	
	head = 0;
	for(idx = 0; idx < (int)nums.size(); idx++)
	{
		tail = idx;

		if(nums[idx] == 0)
		{
			tail = idx - 1;
			subarray.first = head;
			subarray.second = tail;
			
			if(tail >= 0)
			{
				subarrays.push_back(subarray);
			}

			head  = idx + 1;
		}

		if((idx == (int)(nums.size() - 1)) && (nums[idx] != 0))
		{
			subarray.first = head;
			subarray.second = tail;
			subarrays.push_back(subarray);
		}
	}
}

int Solution::caclMax(vector<int>& nums, pair<int, int> &subarray)
{
	int idx;
	int count;
	int bound;
	int maxprod;
	int lprod0;
	int lprod1;
	int rprod0;
	int rprod1;
	
	if(subarray.first == subarray.second)
	{
		return nums[subarray.first];
	}

	count = 0;
	/* Obtain the number of negative integer */
	for(idx = subarray.first; idx <= subarray.second; idx++)
	{
		if(nums[idx] < 0)
		{
			count++ ;
		}
	}

	if((count % 2) == 0)
	{
		maxprod = 1;
		for(idx = subarray.first; idx <= subarray.second; idx++)
		{
			maxprod = maxprod * nums[idx];
		}
	}
	else
	{
		lprod0 = 1;
		lprod1 = 1;
		rprod0 = 1;
		rprod1 = 1;

		/* Obtain the two local maxmums from left */ 
		for(idx = subarray.first; idx <= subarray.second; idx++)
		{
			if(nums[idx] < 0)
			{
				bound = idx;
				idx++;
				break;
			}
			lprod0 = lprod0 * nums[idx];
		}
		
		for( ; idx <= subarray.second; idx++)
		{
			lprod1 = lprod1 * nums[idx];
		}

		lprod0 = (bound == subarray.first) ? 0 : lprod0;
		lprod1 = (bound == subarray.second) ? 0 : lprod1;

		/* Obtain the two local maxmums from right */ 
		for(idx = subarray.second; idx >= subarray.first; idx--)
		{
			if(nums[idx] < 0)
			{
				bound = idx;
				idx--;
				break;
			}
			rprod1 = rprod1 * nums[idx];
		}
		
		for( ; idx >= subarray.first; idx--)
		{
			rprod0 = rprod0 * nums[idx];
		}

		rprod0 = (bound == subarray.first) ? 0 : rprod0;
		rprod1 = (bound == subarray.second) ? 0 : rprod1;

		/* Obtain the maximum from the four local maximums */
		lprod0 = max(lprod0, lprod1);
		rprod0 = max(rprod0, rprod1);
		maxprod = max(lprod0, rprod0);
	}

	return maxprod;
}

void Solution::dump(vector<int>& nums)
{
	vector<int>::iterator it;
	vector< pair<int, int> >::iterator it1;

	cout << "input nums: " << endl;
	for(it = nums.begin(); it != nums.end(); it++)
	{
		cout << *it << ",";
	}
	cout << endl;
	
	cout << "subarrays: " << endl;
	for(it1 = subarrays.begin(); it1 != subarrays.end(); it1++)
	{
		cout <<"(" << it1->first << ", " << it1->second << ")" << endl;
	}
	cout << endl;
	
	cout << "maxList: " << endl;
	for(it = maxList.begin(); it != maxList.end(); it++)
	{
		cout << *it << ",";
	}
	cout << endl;
}

int main()
{
	int array[] = {0, -1, 0, -2, 0, -3, 0, -22};
	vector<int> nums;
	nums.assign(array, array + ARRAYSIZE);
	Solution sol;
	int maxprod;
	
	/* Obtain the maximum product */
	maxprod = sol.maxProduct(nums);

	sol.dump(nums);

	cout << "Maximum product: " << maxprod << endl;
	return 0;
}
