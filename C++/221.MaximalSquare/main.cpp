/* Use dynamic programing: complexity:O(m*n)
 * 1. If matrix[i][j] == '1', we get the right side length first, and down side length latter
 * 2. If min(rifgtSide, downSide) >= dp[i - 1][j - 1], which means the square can grow. 
      dp[i][j] = dp[i - 1][j - 1] + 1
 * 3. If min(rifgtSide, downSide) < dp[i - 1][j - 1], which means the square cannot grow. 
      dp[i][j] = min(rifgtSide, downSide) + 1

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
	int getRightSide(int i, int j, vector<vector<char>>& matrix);
	int getDownSide(int i, int j, vector<vector<char>>& matrix);
	int maximalSquare(vector<vector<char>>& matrix);

private:
};/*End of class Solution */

Solution::Solution()
{
}

Solution::~Solution()
{
}

int Solution::getRightSide(int i, int j, vector<vector<char>>& matrix){
    int y;
    int len;
    
    for(len = 0, y = i - 1; y >= 0; --y){
        if(matrix[y][j] == '0'){
            break;
        }
        else{
            len++;
        }
    }
    return len;
}

int Solution::getDownSide(int i, int j, vector<vector<char>>& matrix){
    int x;
    int len;
    
    for(len = 0, x = j - 1; x >= 0; --x){
        if(matrix[i][x] == '0'){
            break;
        }
        else{
            len++;
        }
    }
    return len;
}

int Solution::maximalSquare(vector<vector<char>>& matrix) {
    vector<vector<int>> dp;
    vector<int> row;
    int i;
    int j;
    int maxLen;
    int rightSide;
    int downSide;
    int minSide;
    
    if(matrix.size() == 0){
        return 0;
    }
    
    maxLen = 0;
    row.assign(matrix[0].size(), 0);
    dp.assign(matrix.size(), row);
    
    for(i = 0; i < dp[0].size(); ++i){
        if(matrix[0][i] == '1'){
            dp[0][i] = 1;
            maxLen = 1;
        }
    }
    
    for(i = 0; i < dp.size(); ++i){
        if(matrix[i][0] == '1'){
            dp[i][0] = 1;
            maxLen = 1;
        }
    }
    
    for(i = 1; i < dp.size(); ++i){
        for(j = 1; j < dp[i].size(); ++j){
            if(matrix[i][j] == '1'){
                rightSide = getRightSide(i, j, matrix);
                downSide = getDownSide(i, j, matrix);
                minSide = min(downSide, rightSide);
                if(minSide >= dp[i - 1][j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if(maxLen < dp[i][j]){
                        maxLen = dp[i][j];
                    }
                }
                else{
                    dp[i][j] = minSide + 1;
                }
            }
            else{
                dp[i][j] = 0;
            }
        }
    }
    
    return maxLen*maxLen;
}

int main()
{
    Solution sol;
    vector<char> row;
    vector<vector<char>> matrix;
    int area;

	row.push_back('1');
	row.push_back('0');
	row.push_back('1');
	row.push_back('0');
	row.push_back('0');
	matrix.push_back(row);
	row.clear();
	row.push_back('1');
	row.push_back('0');
	row.push_back('1');
	row.push_back('1');
	row.push_back('1');
	matrix.push_back(row);
	row.clear();
	row.push_back('1');
	row.push_back('0');
	row.push_back('1');
	row.push_back('1');
	row.push_back('1');
	matrix.push_back(row);
	row.clear();
	row.push_back('1');
	row.push_back('0');
	row.push_back('0');
	row.push_back('1');
	row.push_back('0');
	matrix.push_back(row);
	
	area = sol.maximalSquare(matrix);
	
	cout << "area: " << area << endl;	

	return 0;
}
