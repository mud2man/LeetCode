/* Use dyanmic programming O(n^2)
 * 1. Traverse from outer layer to inner layer
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
    int maxProfit(vector<int>& prices);

private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::maxProfit(vector<int>& prices) {
    int day;
    int maxProfit;
    int profit;
    int i;
    vector<int> dp;
    
    
    if(prices.size() == 0){
        return 0;
    }
    
    /* max profit */
    dp.assign(prices.size(), 0);
    
    for(day = 1; day < prices.size(); ++day){
        maxProfit = dp[day - 1];
        
        for(i = (day - 1); i >= 0; --i){
            profit = prices[day] - prices[i];
            
            if((i - 2) >= 0){
                profit =  profit + dp[i - 2];
                
                if(maxProfit > (dp[i - 2] + prices[day])){
                    break;
                }
            }
            
            if(profit > maxProfit){
                maxProfit = profit;
            }
        }
        
        dp[day] = maxProfit;
    }
    return dp[day - 1];
}

int main(){
    vector<int> prices;
    Solution sol;
	int maxProfit;
	
	prices.push_back(1);
	prices.push_back(2);
	prices.push_back(3);
	prices.push_back(0);
	prices.push_back(2);
	
	maxProfit = sol.maxProfit(prices);
	
	cout << "maxProfit: " << maxProfit << endl;;

	return 0;
}
