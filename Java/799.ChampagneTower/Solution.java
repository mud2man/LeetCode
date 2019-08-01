/* Dynamic Programming: Time:O(n^2), Space:O(n), where n is query_row
 * 1. Simulate the cups by "rows"
 * 2. We calculate next row by previous row, and the overflow of cup row[y][x] contribute (overflow / 2.0) to row[y + 1][x] and row[y + 1][x + 1]
 * 3. Return the answer from rows[query_row][query_glass]
 */

import java.util.*;

public class Solution{
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] rows = new double[2][query_row + 1];
        rows[0][0] = (double)poured;
        for(int y = 1; y <= query_row; ++y){
            Arrays.fill(rows[y % 2], 0.0);
            double[] prevRow = rows[(y - 1) % 2];
            for(int x = 0; x < y; ++x){
                if(prevRow[x] > 1.0){
                    double overflow = prevRow[x] - 1.0;
                    rows[y % 2][x] += overflow / 2.0;
                    rows[y % 2][x + 1] += overflow / 2.0;
                }
            }
        }
        return (rows[query_row % 2][query_glass] > 1.0)? 1.0: rows[query_row % 2][query_glass];
    }
  
    public static void main(String[] args){
        int poured = 2;
        int query_glass = 1;
        int query_row = 1;
        Solution sol = new Solution();
        System.out.println(String.format("poured=%d, query_glass=%d, query_row=%d, answer=%f", poured, query_glass, query_row, sol.champagneTower(poured, query_glass, query_row)));
    }
}
