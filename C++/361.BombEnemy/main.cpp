/* O(m*n)
 * 1. Caculate the enemy to right, left, up, and down
 * 2. enemy[y][x] =  right[y][x - 1] + left[y][x + 1] + down[y - 1][x] + up[y + 1][x]
 */

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
    int maxKilledEnemies(vector<vector<char>>& grid) ;
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}
int Solution::maxKilledEnemies(vector<vector<char>>& grid) {
    vector<vector<int>> right;
    vector<vector<int>> left;
    vector<vector<int>> down;
    vector<vector<int>> up;
    vector<int> row;
    int rowNum;
    int colNum;
    int x;
    int y;
    int maxEnemy;
    int currEnemy;
    
    rowNum = grid.size();
    if(rowNum == 0){
        return 0;
    }
    
    colNum = grid[0].size();
    if(colNum  == 0){
        return 0;
    }
    
    row.assign(colNum, 0);
    right.assign(rowNum, row);
    left.assign(rowNum, row);
    down.assign(rowNum, row);
    up.assign(rowNum, row);
    
    /* caculate enemies to right */
    for(y = 0; y < rowNum; ++y){
        if(grid[y][0] == 'E'){
            right[y][0] = 1;
        }
        for(x = 1; x < colNum; ++x){
            if(grid[y][x] == 'E'){
                right[y][x] = right[y][x - 1] + 1;
            }
            else if(grid[y][x] == 'W'){
                right[y][x] = 0;
            }
            else{
                right[y][x] = right[y][x - 1];
            }
        }
    }
    
    /* caculate enemies to left */
    for(y = 0; y < rowNum; ++y){
        if(grid[y][colNum - 1] == 'E'){
            left[y][colNum - 1] = 1;
        }
        for(x = colNum - 2; x >= 0; --x){
            if(grid[y][x] == 'E'){
                left[y][x] = left[y][x + 1] + 1;
            }
            else if(grid[y][x] == 'W'){
                left[y][x] = 0;
            }
            else{
                left[y][x] = left[y][x + 1];
            }
        }
    }
    
    /* caculate enemies to down */
    for(x = 0; x < colNum; ++x){
        if(grid[0][x] == 'E'){
            down[0][x] = 1;
        }
        for(y = 1; y < rowNum; ++y){
            if(grid[y][x] == 'E'){
                down[y][x] = down[y - 1][x] + 1;
            }
            else if(grid[y][x] == 'W'){
                down[y][x] = 0;
            }
            else{
                down[y][x] = down[y - 1][x];
            }
        }
    }
    
    /* caculate enemies to up */
    for(x = 0; x < colNum; ++x){
        if(grid[rowNum - 1][x] == 'E'){
            up[rowNum - 1][x] = 1;
        }
        for(y = rowNum - 2; y >= 0; --y){
            if(grid[y][x] == 'E'){
                up[y][x] = up[y + 1][x] + 1;
            }
            else if(grid[y][x] == 'W'){
                up[y][x] = 0;
            }
            else{
                up[y][x] = up[y + 1][x];
            }
        }
    }
    
    /* caculate maximum number of enemy */
    maxEnemy = 0;
    for(y = 0; y < rowNum; ++y){
        for(x = 0; x < colNum; ++x){
            currEnemy = 0;
            if(grid[y][x] == '0'){
                if(x > 0){
                    currEnemy = currEnemy + right[y][x - 1];
                }
                
                if(x < (colNum - 1)){
                    currEnemy = currEnemy + left[y][x + 1];
                }
                
                if(y > 0){
                    currEnemy = currEnemy + down[y - 1][x];
                }
                
                if(y < (rowNum - 1)){
                    currEnemy = currEnemy + up[y + 1][x];
                }
                
                if(maxEnemy < currEnemy){
                    maxEnemy = currEnemy;
                }
            }
        }
    }
    
    return maxEnemy;
}

int main(){
    int maxEnemyNum;
    Solution sol;
    vector<char> row;
    vector<vector<char>> grid;
    int i;
    int j;
    
    row.assign(4, 'W');
	row[0] = '0';
	row[1] = 'E';
	row[2] = '0';
	row[3] = '0';
	grid.push_back(row);
	row[0] = 'E';
	row[1] = '0';
	row[2] = 'W';
	row[3] = 'E';
	grid.push_back(row);
	row[0] = '0';
	row[1] = 'E';
	row[2] = '0';
	row[3] = '0';
	grid.push_back(row);
   	
	maxEnemyNum = sol.maxKilledEnemies(grid);
	
	cout << "grid: " << endl;
	for(i = 0; i < grid.size(); ++i){
		for(j = 0; j < grid[i].size(); ++j){
			cout << grid[i][j] << ", ";
		}
		cout << endl;
	}

	cout << "maxEnemyNum: " << maxEnemyNum << endl;
	
	return 0;
}
