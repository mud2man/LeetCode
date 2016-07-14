/* 
 * 1. Traverse 
 * 2. Pop the "nodestack" and ckeck if it has right child, if yes go to step3, else go to step4
 * 3. Use its right child as the input parameter, and go back to step1
 * 4. Check if node stack empty, if yes, exit, else, go to step 2
 * 5. Reconstruct the tree using the nodes stored in "prestack"
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution 
{
public:
	int maxProfit(vector<int>& prices);

private:
	/* Minimun price */
	int minprice;

	/* Max profit */
	int maxprofit;

};/*End of class Solution */

int Solution::maxProfit(vector<int>& prices) 
{
	unsigned int size = prices.size();
	unsigned int idx;
	
	if(size == 0)
	{
		return 0;
	}

	maxprofit = 0;
	minprice = prices[0];

	for(idx = 0; idx < size; idx ++)
	{
		if((prices[idx] - minprice) > maxprofit)
		{
			maxprofit = prices[idx] - minprice;
		}

		if(minprice > prices[idx])
		{
			minprice = prices[idx];
		}
	}

	return maxprofit;
}

#define ARRAYSIZE 6

int main()
{
	Solution sol;
	int prices[ARRAYSIZE] = {7, 6, 4, 3, 2, 1};
	vector<int> pricelist;
	int maxprofit;
    
	/* Generate a input prices list */
	pricelist.assign(prices, prices + ARRAYSIZE -1);

    /* Obtain the best profit */
	maxprofit = sol.maxProfit(pricelist);

	/*Dump the answer */
	cout << "maxprofit: " << maxprofit << endl;

	return 0;
}
