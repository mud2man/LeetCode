/* Binary search: O(nlogn)
 * 1. Binary search the integer m between matrix[0][0] to matrix[size - 1][size - 1], s.t. the number of integer which is smaller or equal to m = k
 * 2. Traverse the matrix from left-bottom corner to locate the maximum smaller number "smaller", and minimum bigger number "bigger" w.r.t m
 * 3. If the the number of integer which is smaller or equal to "smaller" >= k, then return "smaller"
 * 4. Otherwise, return "bigger"
 * 5. Note that the number of integer == matrix[0][0] may be bigger than k, so the number of integer which is smaller or equal to "smaller" >= k is possible
 */

import java.util.*;

public class Solution{
    public int lessEqualCount(int[][] matrix, int n){
        int y;
        int x;
        int size;
        int count;
        
        size = matrix.length;
        count = 0;
        y = size - 1;
        
        for(x = 0; (x < size)  && (y >= 0); ++x){
            while((y >= 0) && (matrix[y][x] > n)){
                --y;
            }
            count = count + y + 1;
        }
        return count;
    }
    
    public int kthSmallest(int[][] matrix, int k){
        long lb;
        long ub;
        int m;
        int count;
        int size;
        int x;
        int y;
        int smaller;
        int bigger;
        
        size = matrix.length;
        lb = matrix[0][0];
        ub = matrix[size - 1][size - 1];
        m = 1;
        
        while(lb <= ub){
            m = (int) ((lb + ub) / 2);
            count = lessEqualCount(matrix, m);
            if(count == k){
                break;
            }
            else if(count < k){
                lb = m + 1;
            }
            else{
                ub = m - 1;
            }
        }
        
        y = size - 1;
        smaller = matrix[0][0];
        bigger = matrix[size - 1][size - 1];
        for(x = 0; (x < size) && (y >= 0); ++x){
            while((y >= 0) && (matrix[y][x] > m)){
                --y;
            }
         
            if((y >= 0) && ((m - smaller) > (m - matrix[y][x]))){
                smaller = matrix[y][x];
            }
            
            if((y < (size - 1)) && ((bigger - m) > (matrix[y + 1][x]) - m)){
                bigger = matrix[y + 1][x];
            }
        }
        return (lessEqualCount(matrix, smaller) >= k)? smaller: bigger;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k;
        int num;

        k = 8;

        System.out.println("matrix: ");
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        sol = new Solution();
        num = sol.kthSmallest(matrix, k);

        System.out.println("");
        System.out.println(k+  "th num: " + num);
    }
}
