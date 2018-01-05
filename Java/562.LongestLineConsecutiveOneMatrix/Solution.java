/* Dynamic Programming: O(n*m)
 * 1. horizontal[y][x] = longest consecutive one from left ended on (y, x)
 * 2. vertical[y][x] = longest consecutive one from top ended (y, x)
 * 3. diagonal[y][x] = longest consecutive one from left-top ended (y, x)
 * 4. antiDiagonal[y][x] = longest consecutive one from right-top ended (y, x)
 * 5. update max length from these matrix
 */

import java.util.*;

public class Solution{
    public int longestLine(int[][] M) {
        int depth = M.length;
        int width = (depth > 0)? M[0].length: 0;
        int[][] horizontal = new int[depth][width];
        int[][] vertical = new int[depth][width];
        int[][] diagonal = new int[depth][width];
        int[][] antiDiagonal = new int[depth][width];
        int maxLength = 0;
        
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(M[y][x] == 0){
                    continue;
                }
                else if(x == 0 && y == 0){
                    vertical[y][x] = 1;
                    horizontal[y][x] = 1;
                    diagonal[y][x] = 1;
                    antiDiagonal[y][x] = 1;
                    maxLength = 1; 
                }
                else{
                    vertical[y][x] = (y > 0 && M[y - 1][x] == 1)? vertical[y - 1][x] + 1: 1;
                    horizontal[y][x] = (x > 0 && M[y][x - 1] == 1)? horizontal[y][x - 1] + 1: 1;
                    diagonal[y][x] = (y > 0 && x > 0 && M[y - 1][x - 1] == 1)? diagonal[y - 1][x - 1] + 1: 1;
                    antiDiagonal[y][x] = (y > 0 && x < (width - 1) && M[y - 1][x + 1] == 1)? antiDiagonal[y - 1][x + 1] + 1: 1;
  
                    maxLength = Math.max(maxLength, vertical[y][x]);
                    maxLength = Math.max(maxLength, horizontal[y][x]);
                    maxLength = Math.max(maxLength, diagonal[y][x]);
                    maxLength = Math.max(maxLength, antiDiagonal[y][x]);
                }
            }
        }
        return maxLength;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] M = {{0, 1, 1, 0},
                     {0, 1, 1, 0},
                     {0, 0, 0, 1}};

        System.out.println("M: ");
        for(int[] row: M){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("longestLine: " + sol.longestLine(M));
    }
}
