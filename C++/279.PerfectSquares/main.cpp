/* Dynamic programming: Complexity: O(n*logn)
 * 1. dp[i] = min(dp[i -j*j] + 1, all j, s.t. j*j <= i) 
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
    
public:
	Solution();
	~Solution();
    int numSquares(int n);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::numSquares(int n) {
    vector<int> dp;
    long i;
    long j;
    long min;
    
    if(n == 0){
        return 0;
    }
    
    dp.assign(n + 1, 0);
    dp[1] = 1;
    
    for(i = 2; i <= (long)n; ++i){
        min = dp[i - 1] + 1;
        
        j = 2;
        while( j*j <= i){
            if(min > (1 + dp[i - j*j])){
                min = 1 + dp[i - j*j];
            }
            ++j;
        }
        dp[i] = min;
    }
    return dp[n];
}

int main()
{
    Solution sol;
	int n;
	int minCount;

	n = 13;

	minCount = sol.numSquares(n);
	
	cout << "minCount: " << minCount << endl;

	return 0;
}
