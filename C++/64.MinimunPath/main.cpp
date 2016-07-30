/* Use Dynamic Programing 
 * 1. Instantiate a 2D vector to store the path sum from rightest and downest element
 * 2. Accumulate the path sum from rightest element in the last row, and traverse backwardly
 * 3. Accumulate the path sum from rightest element in the second last row, and traverse backwardly again
 * 4. Repeat the step 2 and 3 until the program reach the leftest and upest element
 */

#include <iostream>
#include <vector>
#include <algorithm> 
#include <new>

using namespace std;

class Solution 
{
public:
	int minPathSum(vector< vector<int> >& grid);
	friend void dumpgrid(Solution &S, vector< vector<int> >& grid);

private:
	/* Record the minimal path sum from the rightest and downest element */
	vector< vector<int> > pathsum;
};/*End of class Solution */

int Solution::minPathSum(vector< vector<int> >& grid)
{
	int x;
	int y;
	int colnum;
	int rownum;

	if(grid.empty())
	{
		return 0;
	}

	/* Instantiate "pathsum */
	pathsum = grid;

	colnum = grid[0].size();
	rownum = grid.size(); 

	for(y = rownum - 1; y >= 0; y--)
	{
		for(x = colnum - 1; x >= 0; x--)
		{
			if((x == colnum - 1) && (y == rownum - 1))
			{
				pathsum[y][x] = grid[y][x];
			}
			else if(y == rownum - 1)
			{
				pathsum[y][x] = grid[y][x] + pathsum[y][x + 1];
			}
			else if(x ==  colnum -1)
			{
				pathsum[y][x] = grid[y][x] + pathsum[y + 1][x];
			}
			else
			{
				pathsum[y][x] = grid[y][x] + min(pathsum[y][x + 1], pathsum[y + 1][x]);
			}
		}
	}

	return pathsum[0][0];
}

void dumpgrid(Solution &S ,vector< vector<int> >& grid)
{
	int x;
	int y;
	int colnum;
	int rownum;
	
	colnum = grid[0].size();
	rownum = grid.size();

	cout << "grid: " << endl;
	for(x= 0; x < colnum; x++)
	{
		for(y = 0; y < rownum; y++)
		{
			cout << grid[x][y] << "," ;
		}
		cout << endl;
	}

	cout << "pathsum: " << endl;
	for(x= 0; x < colnum; x++)
	{
		for(y = 0; y < rownum; y++)
		{
			cout << S.pathsum[x][y] << "," ;
		}
		cout << endl;
	}
}

int array[3][3] = {{1, 2, 2}, {1, 3, 4}, {3, 1, 2}};

int main()
{
	Solution sol;
	vector<int> *row;
	vector< vector<int> > grid;
	int sum;

	/* Instatiate a 2D grid */
	row = new vector<int>;
	row->assign(array[0], array[0] + 3);
	grid.push_back(*row);
	row = new vector<int>;
	row->assign(array[1], array[1] + 3);
	grid.push_back(*row);
	row = new vector<int>;
	row->assign(array[2], array[2] + 3);
	grid.push_back(*row);

	sum = sol.minPathSum(grid);
		
	dumpgrid(sol, grid);

	cout << "sum: " << sum << endl;

	return 0;
}
