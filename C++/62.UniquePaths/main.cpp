/* Use dynamic programming: Complexity: O(m*n)
 * 1. Traverse from the bottom-right corner
 * 2. Accumulate the sub-solution by dp[i][j] = dp[i+1][j] + dp[i][j+1]
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
	int uniquePaths(int m, int n);

private:
	vector< vector<int> > dp;    
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::uniquePaths(int m, int n) {
    int i;
    int j;
    
    if((m == 0) || (n == 0)){
        return 0;
    }
    
    if((m == 1) || (n == 1)){
        return 1;
    }
    
    /* Reset dp */
    this->dp.resize(m);
    for(i = 0; i < m; ++i){
        this->dp[i].assign(n, 0);
    }
    
    /* Reset last row */
    for(i = (n - 2); i >= 0; --i){
        this->dp[m - 1][i] = 1;
    }
    
    /* Reset last column */
    for(i = (m - 2); i >= 0; --i){
        this->dp[i][n - 1] = 1;
    }
    
    for(i = (m - 2); i >= 0; --i){
        for(j = (n - 2); j >= 0 ; --j){
            this->dp[i][j] = this->dp[i + 1][j] + this->dp[i][j + 1];
        }
    }
    
    return this->dp[0][0];
}

int main(){
    Solution sol;
    int m;
	int n;
	
	m = 3;
	n = 3;

	cout << "number of unique paths: " << sol.uniquePaths(m, n) << endl;

	return 0;
}
