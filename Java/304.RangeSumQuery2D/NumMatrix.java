/* Use Dynamic Programing: O(n^2)
 * 1. Create a 2D array to store partual sum
 * 2. sum(i, j) = sum(i, j-1) + sum(i-1, j) + x(i, j) - sum(i-1, j-1)
 * 3. regionSum(row1, col1, row2, col2) = sum(row2, col2) - sum(row2, col1 - 1 )
      - sum(row1, col2 - 1) + sum(row1 - 1, col1 - 1)
 */

import java.util.*;

public class NumMatrix{

	/* partual sum */
	List<List<Integer> > sums;
	
    public NumMatrix(int[][] matrix) {
		int colSize;
		int rowSize;
		int x;
		int y;
		int upSum;
		int leftSum;
		int upLeftSum;
		int sum;
		List<Integer> row;

		sums = new ArrayList<List<Integer>>();
		rowSize = matrix.length;
		
		if(rowSize != 0){
			colSize = matrix[0].length;
		}
		else{
			return;
		}

		/* Acquire sums in 0th row*/
		row = new ArrayList<Integer>();
		row.add(matrix[0][0]);
		for(x = 1; x < colSize; x++){
			leftSum = row.get(x - 1);
			sum = leftSum + matrix[0][x];
			row.add(sum);
		}
		sums.add(row);

		/* Acquire sums in 0th colum*/
		for(y = 1; y < rowSize; y++){
			row = new ArrayList<Integer>();
			upSum = sums.get(y - 1).get(0);
			sum = upSum + matrix[y][0];
			row.add(sum);
			sums.add(row);
		}

		/* Acquire sums in the rest column and row*/
		for(y = 1; y < rowSize; y++){
			for(x = 1; x < colSize; x++){
				upSum = sums.get(y - 1).get(x);
				leftSum = sums.get(y).get(x - 1);
				upLeftSum = sums.get(y - 1).get(x - 1);
				sum = upSum + leftSum + matrix[y][x] - upLeftSum;
				sums.get(y).add(sum);
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int upSum;
		int leftSum;
		int upLeftSum;
	
		if(sums.size() == 0){
			return 0;
		}

		upSum = (row1 > 0)? sums.get(row1 - 1).get(col2): 0 ;
		leftSum =(col1 > 0)? sums.get(row2).get(col1 - 1): 0;
		upLeftSum = ((col1 > 0) && (row1 > 0))? sums.get(row1 - 1).get(col1 - 1): 0;

		return sums.get(row2).get(col2) - upSum - leftSum + upLeftSum;
	}

	public void dump(){
		System.out.println(sums);
	}

	public static void main(String[] args){
		NumMatrix sol;
		int[][] matrix = {{3, 0, 1, 4, 2},
						  {5, 6, 3, 2, 1},
						  {1, 2, 0, 1, 5},
						  {4, 1, 0, 1, 7},
						  {1, 0, 3, 0, 5}};
		
		sol = new NumMatrix(matrix);

		System.out.println("(2, 1, 4, 3) = " + sol.sumRegion(2, 1, 4, 3));
		System.out.println("(1, 1, 2, 2) = " + sol.sumRegion(1, 1, 2, 2));
		System.out.println("(1, 2, 2, 4) = " + sol.sumRegion(1, 2, 2, 4));
		System.out.println("(1, 2, 1, 2) = " + sol.sumRegion(1, 2, 1, 2));

	}
}
