/* Use BFS O(m*n)
 * 1. Use BFS starting every city, stop if intersection happen
 * 2. Caculate the distence around the intersection point to get the minimum
 */

#include <iostream>
#include <vector>
#include <deque>
#include <utility>
#include <climits>
#include <algorithm>

using namespace std;

class Building{
public:
    int x, y;
    vector<vector<bool>> reached;
    deque<pair<int, int>> queue;
    
    Building(vector<vector<int>>& grid, int x, int y){
        vector<bool> row;
        
        this->x = x;
        this->y = y;
        row.assign(grid[0].size(), false);
        this->reached.assign(grid.size(), row);
        for(y = 0; y < grid.size(); ++y){
            for(x = 0; x < grid[0].size(); ++x){
                if(grid[y][x] != 0){
                    this->reached[y][x] = true;
                }
            }
        }
        this->queue.push_front(make_pair(this->x, this->y));
    }
    
    /* 0: no move; 1: move; 2: foun */
    int move(int target, vector<vector<int>>& grid, int& x, int& y){
        int i, j, qSize, nextX, nextY;
        bool hasNext;
        pair<int, int> pos;
        
        qSize = queue.size();
        if(qSize == 0){
            return 0;
        }
        
        for(i = 0; i < qSize; ++i){
            pos = queue.back();
            queue.pop_back();
            
            for(j = 0; j < 4; ++j){
                hasNext = false;
                switch (j){
                    case 0:
                        if(pos.second > 0){
                            nextX = pos.first;
                            nextY = pos.second - 1;
                            hasNext = true;
                        }
                        break;
                    case 1:
                        if(pos.second < (grid.size() - 1)){
                            nextX = pos.first;
                            nextY = pos.second + 1;
                            hasNext = true;
                        }
                        break;
                    case 2:
                        if(pos.first > 0){
                            nextX = pos.first - 1;
                            nextY = pos.second;
                            hasNext = true;
                        }
                        break;
                    case 3:
                        if(pos.first < (grid[0].size() - 1)){
                            nextX = pos.first + 1;
                            nextY = pos.second;
                            hasNext = true;
                        }
                        break;
                }
                
                if((hasNext == true) && (this->reached[nextY][nextX] == false)){
                    this->reached[nextY][nextX] = true;
                    this->queue.push_front(make_pair(nextX, nextY));
                    
                    if(grid[nextY][nextX] == 0){
                        grid[nextY][nextX] = 3;
                    }
                    else{
                        grid[nextY][nextX] = grid[nextY][nextX] + 1;
                    }
                    if(grid[nextY][nextX] == target){
                        x = nextX;
                        y = nextY;
                        return 2;
                    }
                }
            }
        }
        return 1;
    }
};

class Solution {
public:
	void gridDisplay(vector<vector<int>> grid){
		int x, y;
		
		cout << "grid[][]: " << endl;
		for(y = 0; y < grid.size(); ++y){
			for(x = 0; x < grid[0].size(); ++x){
				cout << grid[y][x] << ",";
			}
			cout << endl;
		}
	}

    int bfsDistence(vector<vector<int>> grid, int x, int y, int bldNum){
        deque<pair<int, int>> queue;
        int i, distence, size, totalDis;
        pair<int, int> pos;
        
        if((x < 0) || (x >= grid[0].size()) || (y < 0) || (y >= grid.size()) || (grid[y][x] != 0)){
            return INT_MAX;
        }
        
        grid[y][x] == 3;
        queue.push_front(make_pair(x, y));
        totalDis = 0;
        distence = 0;
        while(!queue.empty()){
            distence++;
            size = queue.size();
            
            for(i = 0; i < size; ++i){
                pos = queue.back();
                queue.pop_back();
                
                if((pos.second > 0) && (grid[pos.second - 1][pos.first] == 1)){
                    grid[pos.second - 1][pos.first] = 3;
                    totalDis = totalDis + distence;
                    --bldNum;
                }else if((pos.second > 0) && (grid[pos.second - 1][pos.first] == 0)){
                    grid[pos.second - 1][pos.first] = 3;
                    queue.push_front(make_pair(pos.first, pos.second - 1));
                }
                
                if((pos.second < (grid.size() - 1)) && (grid[pos.second + 1][pos.first] == 1)){
                    grid[pos.second + 1][pos.first] = 3;
                    totalDis = totalDis + distence;
                    --bldNum;
                }else if((pos.second < (grid.size() - 1)) && (grid[pos.second + 1][pos.first] == 0)){
                    grid[pos.second + 1][pos.first] = 3;
                    queue.push_front(make_pair(pos.first, pos.second + 1));
                }
               	
                if((pos.first > 0) && (grid[pos.second][pos.first - 1] == 1)){
                    grid[pos.second][pos.first - 1] = 3;
                    totalDis = totalDis + distence;
                    --bldNum;
                }else if((pos.first > 0) && (grid[pos.second][pos.first - 1] == 0)){
                    grid[pos.second][pos.first - 1] = 3;
                    queue.push_front(make_pair(pos.first - 1, pos.second));
                }
                
                if((pos.first < (grid[0].size() - 1)) && (grid[pos.second][pos.first + 1] == 1)){
                    grid[pos.second][pos.first + 1] = 3;
                    totalDis = totalDis + distence;
                    --bldNum;
                }else if((pos.first < (grid[0].size() - 1)) && (grid[pos.second][pos.first + 1] == 0)){
                    grid[pos.second][pos.first + 1] = 3;
                    queue.push_front(make_pair(pos.first + 1, pos.second));
                }
            }
        }
        return (bldNum == 0)? totalDis: INT_MAX;
    }
    
    int shortestDistance(vector<vector<int>>& grid) {
        vector<vector<int>> cloneGrid;
        int x, y, optX, optY, movResult, minDis, bldNum, range, i;
        Building *ptrBld;
        vector<Building *> bldList;
        bool isMove, isFound;
        
        cloneGrid = grid;
        optX = 0;
        optY = 0;
        bldNum = 0;
        for(y = 0; y < cloneGrid.size(); ++y){
            for(x = 0; x < cloneGrid[0].size(); ++x){
                if(cloneGrid[y][x] == 1){
                    bldList.push_back(new Building(grid, x, y));
                    bldNum++;
                }
            }
        }
        
        isMove = true;
        isFound = false;
        while((isMove == true) && (isFound == false)){
            isMove = 0;
            
            for(x = 0; x < bldList.size(); ++x){
                movResult = bldList[x]->move(bldList.size() + 2, cloneGrid, optX, optY);
                if(movResult == 2){
                    isFound = true;
                    break;
                }
                else if(movResult == 1){
                    isMove = true;
                }
            }
        }
        
        if(isFound == false){
            return -1;
        }
        else{
            minDis = bfsDistence(grid, optX, optY, bldNum);
            for(range = 4; range > 0; range = range - 2){
                x = optX - range / 2;
                y = optY - range / 2;
                
                for(i = 0; i < range; ++i, ++x){
                    minDis = min(minDis, bfsDistence(grid, x, y, bldNum));
                }
                
                for(i = 0; i < range; ++i, ++y){
                    minDis = min(minDis, bfsDistence(grid, x, y, bldNum));
                }
                
                for(i = 0; i < range; ++i, --x){
                    minDis = min(minDis, bfsDistence(grid, x, y, bldNum));
                }
                
                for(i = 0; i < range; ++i, --y){
                    minDis = min(minDis, bfsDistence(grid, x, y, bldNum));
                }
            }
            return minDis;
        }
    }
};

int main(){
	int x;
	int y;
	int distence;
    int bldNum;
	Solution sol;
	vector<int> row;
	vector<vector<int>> grid;

	row.push_back(0);
	row.push_back(2);
	row.push_back(0);
	row.push_back(2);
	row.push_back(2);
	row.push_back(0);
	row.push_back(2);
	row.push_back(2);
	grid.push_back(row);

	row.clear();
	row.push_back(0);
	row.push_back(2);
	row.push_back(2);
	row.push_back(2);
	row.push_back(1);
	row.push_back(0);
	row.push_back(1);
	row.push_back(2);
	grid.push_back(row);
	
	row.clear();
	row.push_back(0);
	row.push_back(0);
	row.push_back(0);
	row.push_back(1);
	row.push_back(0);
	row.push_back(2);
	row.push_back(0);
	row.push_back(0);
	grid.push_back(row);
	
	row.clear();
	row.push_back(2);
	row.push_back(0);
	row.push_back(0);
	row.push_back(2);
	row.push_back(0);
	row.push_back(2);
	row.push_back(2);
	row.push_back(0);
	grid.push_back(row);
	
	row.clear();
	row.push_back(0);
	row.push_back(0);
	row.push_back(0);
	row.push_back(2);
	row.push_back(0);
	row.push_back(0);
	row.push_back(0);
	row.push_back(0);
	grid.push_back(row);
    
    bldNum = 0;
    for(y = 0; y < grid.size(); ++y){
        for(x = 0; x < grid[0].size(); ++x){
            if(grid[y][x] == 1){
                bldNum++;
            }
        }
    }
	
	distence = sol.shortestDistance(grid);
	sol.gridDisplay(grid);	
	cout << "\nminimum distence: " << distence << endl;

	return 0;
}
