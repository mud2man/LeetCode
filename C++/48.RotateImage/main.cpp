/* Break down like onion
 * 1. Complexity: O(n^2)
 */

#include <iostream>
#include <vector>

using namespace std;


class Solution {

public:
	Solution();
	~Solution();
    void rotateEdge(vector< vector<int> >& matrix, int rowId, int colId, int edgeLen);
    void rotate(vector< vector<int> >& matrix);

private:

};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

void Solution::rotateEdge( vector<vector<int> >& matrix, int rowId, int colId, int edgeLen){
    vector<int> upperEdge;
    int size;
    int i;
    int x;
    
    size = edgeLen - 1;
    
    /* backup upper edge */
    for(i = 0; i < size; ++i){
        upperEdge.push_back(matrix[rowId][colId + i]);
    }
    
    /* move left edge to upper edge */
    for(i = 0; i < size; ++i){
        x = matrix[rowId + size - i][colId];
        matrix[rowId][colId + i] = x;
    }
    
    /* move lower edge to left edge */
    for(i = 0; i < size; ++i){
        x = matrix[rowId + size][colId + size - i];
        matrix[rowId + size - i][colId] = x;
    }
    
    /* move right edge to lower edge */
    for(i = 0; i < size; ++i){
        x = matrix[rowId + i][colId + size];
        matrix[rowId + size][colId + size - i] = x;
    }
    
    /* move upper edge to right edge */
    for(i = 1; i <= size; ++i){
        x = upperEdge.back();
        upperEdge.pop_back();
        matrix[rowId + size -i][colId + size] = x;
    }
}

void Solution::rotate(vector< vector<int> >& matrix) {
    int edgeLen;
    int rowId;
    int colId;
    int i;
    
    edgeLen = matrix.size();
    
    rowId = 0;
    colId = 0;
    for(i = 0; i < (edgeLen / 2); ++i){
        rotateEdge(matrix, rowId + i, colId + i, edgeLen - i*2);
    }
}

int main(){
    Solution sol;
    int i;
    int j;
    vector< vector<int> > matrix;
	vector<int> row0;
	vector<int> row1;
	vector<int> row2;

	row0.push_back(1);
	row0.push_back(2);
	row0.push_back(3);
	matrix.push_back(row0);
	
	row1.push_back(4);
	row1.push_back(5);
	row1.push_back(6);
	matrix.push_back(row1);
	
	row2.push_back(7);
	row2.push_back(8);
	row2.push_back(9);
	matrix.push_back(row2);

	cout << "Matrix: " << endl;
	for(i = 0; i < matrix.size(); ++i){
		for(j = 0; j < matrix.size(); ++j){
			cout << matrix[i][j] << ",";
		}
		cout << endl;
	}
	cout << endl;
	
	sol.rotate(matrix);

	cout << "After rotate, Matrix: " << endl;
	for(i = 0; i < matrix.size(); ++i){
		for(j = 0; j < matrix.size(); ++j){
			cout << matrix[i][j] << ",";
		}
		cout << endl;
	}
	cout << endl;

	return 0;
}
