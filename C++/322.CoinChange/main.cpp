/* Dynamic programming
 * 1. Create an array of position w.r.t. every coin
 * 2. Get the minimum amount based on the current of position among all the coins
 * 3. Get the minimum time,
 * 4. Accumulate the position of coin, which is equal to the minimum amount
 *
 * Example:
 * coins = [1, 2, 5], amount = 11
 * dp.amount: 0
 * dp.time:   0
 *            p1
 *            p2
 *            p5
 ==================================================================================
 * dp.amount: 0   1
 * dp.time:   0   1
 *                p1
 *            p2   
 *            p5
 ==================================================================================
 * dp.amount: 0   1   2
 * dp.time:   0   1   1
 *                    p1
 *                p2  
 *            p5
 ==================================================================================
 * dp.amount: 0   1   2   3
 * dp.time:   0   1   1   2
 *                        p1
 *                    p2  
 *            p5
 ==================================================================================
 * dp.amount: 0   1   2   3   4
 * dp.time:   0   1   2   2   2
 *                            p1
 *                        p2  
 *            p5
 ==================================================================================
 * dp.amount: 0   1   2   3   4   5
 * dp.time:   0   1   1   2   2   1
 *                                p1
 *                            p2  
 *                p5
 ==================================================================================
 */

#include <iostream>
#include <vector>
#include <climits>
#include <utility>      // std::pair, std::make_pair

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
	int coinChange(vector<int>& coins, int amount);

private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::coinChange(vector<int>& coins, int amount) {
    vector< pair<int, int> > dp;
    vector<int> pos;
    int minAmount;
    int minTimes;
    int i;
    
    pos.assign(coins.size(), 0);
    dp.push_back(make_pair(0, 0));
    
    while(dp[dp.size() - 1].first < amount){
        /* get the minimum amount */
        minAmount = dp[pos[0]].first + coins[0];
        for(i = 1; i < pos.size(); ++i){
            if(minAmount > (dp[pos[i]].first + coins[i])){
                minAmount = dp[pos[i]].first + coins[i];
            }
        }
        
        /* Accumulate positions */
        minTimes = INT_MAX;
        for(i = 0; i < pos.size(); ++i){
            if(minAmount == (dp[pos[i]].first + coins[i])){
                if(minTimes > (dp[pos[i]].second + 1)){
                    minTimes = dp[pos[i]].second + 1;
                }
                pos[i] = pos[i] + 1;
            }
        }
        
        dp.push_back(make_pair(minAmount, minTimes));
    }
    
    if(dp[dp.size() - 1].first == amount){
        return dp[dp.size() - 1].second;
    }
    else{
        return -1;
    }
}

int main(){
    Solution sol;
    vector<int> coins;
	int amount;
	int i;

	coins.push_back(1);
	coins.push_back(2);
	coins.push_back(5);
	amount = 11;

	cout << "coins: ";
	for(i = 0; i < coins.size(); ++i){
		cout << coins[i] << ",";
	}
	cout << endl;
	
	cout << "amount: " << amount << endl;;
	
	cout << "minimum times: " << sol.coinChange(coins, amount) << endl;

	return 0;
}
