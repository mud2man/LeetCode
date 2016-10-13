/* O(m*n)
 * 1. Traverse from outer layer to inner layer
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
	void circle(int row, int column, int depth, int width, vector< vector<int> >& matrix);
	vector<int> spiralOrder(vector< vector<int> >& matrix);

private:
    vector<int> order;    
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

void Solution::circle(int row, int column, int depth, int width, vector< vector<int> >& matrix){
    int i;
    
    if((depth <= 0) || (width <= 0)){
        return;
    }
    
    if(depth == 1){
        for(i = column; i < (column + width); ++i){
            order.push_back(matrix[row][i]);
        }
        return;
    }
    
    if(width == 1){
        for(i = row; i < (row + depth); ++i){
            order.push_back(matrix[i][column]);
        }
        return;
    }
    
    /* upper edge */
    for(i = column; i < (column + width - 1); ++i){
        order.push_back(matrix[row][i]);
    }
    
    /* right edge */
    for(i = row; i < (row + depth - 1); ++i){
        order.push_back(matrix[i][column + width - 1]);
    }
    
    /* lower edge */
    for(i = (column + width - 1); i > column; --i){
        order.push_back(matrix[row + depth - 1][i]);
    }
    
    /* left edge */
    for(i = (row + depth - 1); i > row; --i){
        order.push_back(matrix[i][column]);
    }
}

vector<int> Solution::spiralOrder(vector< vector<int> >& matrix) {
    int depth;
    int width;
    int row;
    int column;
    
    if(matrix.size() == 0){
        return order;
    }
    
    depth = matrix.size();
    width = matrix[0].size();
    row = 0;
    column = 0;
    
    while((depth > 0) && (width > 0)){
        circle(row, column, depth, width, matrix);
        ++row;
        ++column;
        depth = depth - 2;
        width = width - 2;
    }
    return order;
}

int main(){
	vector< vector<int> > matrix;
    Solution sol;
	vector<int> row;
	vector<int> ans;
	int i;
	int j;
	
	row.push_back(1);
	row.push_back(2);
	row.push_back(3);
	matrix.push_back(row);
	row.clear();
	row.push_back(6);
	row.push_back(5);
	row.push_back(4);
	matrix.push_back(row);
	row.clear();
	row.push_back(7);
	row.push_back(8);
	row.push_back(9);
	matrix.push_back(row);
	
	ans = sol.spiralOrder(matrix);
	
	cout << "matrix: " << endl;
	for(i = 0; i < matrix.size(); ++i){
		row = matrix[i];
		for(j = 0; j < row.size(); ++j){
			cout << row[j] << ",";
		}
		cout << endl;
	}
	cout << endl;

	cout << "order: " << endl;;
	for(i = 0; i < ans.size(); ++i){
		cout << ans[i] << ",";
	}
	cout << endl;

	return 0;
}
