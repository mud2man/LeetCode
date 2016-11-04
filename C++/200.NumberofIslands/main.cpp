/* Complexity = O(n*m) 
 * 1. Record the traversed history
 * 2. Ripple all the directions via recursive function "ripple"
 * 3. Call the "ripple" function on every element
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
    
public:
	Solution();
	~Solution();
    void dumpRecord(vector<vector<char>>& record);
    bool ripple(vector<vector<char>>& grid, vector<vector<char>>& record, int x, int y);
    int numIslands(vector<vector<char>>& grid);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}
void Solution::dumpRecord(vector<vector<char>>& record){
    int y;
    int x;
    cout << "record:" << endl;
    for(y = 0; y < record.size(); ++y){
        for(x = 0; x < record[0].size(); ++x){
            cout << record[y][x] << "," ;
        }
        cout << endl;
    }
}

bool Solution::ripple(vector<vector<char>>& grid, vector<vector<char>>& record, int x, int y){
    
    if(record[y][x] != '2'){
        return false;
    }
    
    if(grid[y][x] == '0'){
        record[y][x] = '0';
        return false;
    }
    else{
        record[y][x] = '1';
        
        if(x < (record[y].size() - 1)){
            ripple(grid, record, x + 1, y);
        }
        
        if(x > 0){
            ripple(grid, record, x - 1, y);
        }
        
        if(y < (record.size() - 1)){
            ripple(grid, record, x, y + 1);
        }
        
        if(y > 0){
            ripple(grid, record, x, y - 1);
        }
        
        return true;
    }
}

int Solution::numIslands(vector<vector<char>>& grid) {
    vector<vector<char>> record;
    int x;
    int y;
    int islandNum;
    
    if(grid.size() == 0){
        return 0;
    }
    
    islandNum = 0;
    record = grid;
    
    for(y = 0; y < record.size(); ++y){
        for(x = 0; x < record[0].size(); ++x){
            record[y][x] = '2';
        }
    }
    
    for(y = 0; y < record.size(); ++y){
        for(x = 0; x < record[0].size(); ++x){
            if(record[y][x] == '2'){
                if(ripple(grid, record, x, y)){
                    ++islandNum;
                }
  				dumpRecord(record);
            }
        }
    }
    
    return islandNum ;
}

int main(){
    Solution sol;
    vector<vector<char>> grid;
    vector<char> row;

	row.push_back('1');
	row.push_back('1');
	row.push_back('1');
	row.push_back('1');
	row.push_back('0');
	grid.push_back(row);

	row.clear();
	row.push_back('1');
	row.push_back('1');
	row.push_back('0');
	row.push_back('1');
	row.push_back('0');
	grid.push_back(row);
	
	row.clear();
	row.push_back('1');
	row.push_back('1');
	row.push_back('0');
	row.push_back('0');
	row.push_back('0');
	grid.push_back(row);
	
	row.clear();
	row.push_back('0');
	row.push_back('0');
	row.push_back('0');
	row.push_back('0');
	row.push_back('0');
	grid.push_back(row);

    cout << "#islans: "  << sol.numIslands(grid) << endl;

	return 0;
}
