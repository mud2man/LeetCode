/* O(n^2)
 * 1. Swap the right upper part
 * 2. Reverse row by row
 *
 * EX: matrix:{{1, 2, 3}
 *             {4, 5, 6}
 *             {7, 8, 9}}
 *
 * time[0](swap): matrix: {{1, 4, 7}
 *                         {2, 5, 8}
 *                         {3, 6, 9}}
 *
 * time[0](reverse): matrix: {{7, 4, 1}
 *                            {8, 5, 2}
 *                            {9, 8, 7}}
 *
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

void Solution::rotate(vector< vector<int> >& matrix) {
    int y, x, len, tmp;
    
    len = matrix.size();
    
    //swap
    for(y = 0; y < len; ++y){
        for(x = y; x < len; ++x){
            tmp = matrix[y][x];
            matrix[y][x] = matrix[x][y];
            matrix[x][y] = tmp;
        }
    }
    
    //reverse
    for(y = 0; y < len; ++y){
        for(x = 0; x < (len / 2); ++x){
            tmp = matrix[y][x];
            matrix[y][x] = matrix[y][len - x - 1];
            matrix[y][len - x - 1] = tmp;
        }
    }
}

int main(){
    Solution sol;
    unsigned int i;
    unsigned int j;
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
