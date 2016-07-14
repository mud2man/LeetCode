/* 
 * 1. Accumulate the positive profit difference between day to day to maximum profit
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution 
{
public:
	int maxProfit(vector<int>& prices);

private:

};/*End of class Solution */

int Solution::maxProfit(vector<int>& prices) 
{
	int idx;
	int size = prices.size();
	int profit;
	int maxprofit;

	maxprofit = 0;

	/* Traverse "prices" and add the positive profit into maximum profit "maxprofit" */
	for(idx = 1; idx < size; idx ++)
	{
		profit = prices[idx] - prices[idx - 1];

		if(profit > 0)
		{
			maxprofit += profit;
		}
	}

	return maxprofit;
}

#define ARRAYSIZE 6

int main()
{
	Solution sol;
	int prices[ARRAYSIZE] = {7, 1, 5, 3, 6, 4};
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
