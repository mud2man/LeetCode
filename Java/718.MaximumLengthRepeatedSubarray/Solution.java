/* Dynamic programming: Time:O(n*m), Space:O(m)
 * 1. dp[y][x] is the longest repeated array ended with B[y] and A[x]
 * 2. Update dp[y][x] with (y > 0 && x > 0)? (dp[(y - 1) % 2][x - 1] + 1): 1;
 */

import java.util.*;

public class Solution{
    public int findLength(int[] A, int[] B) {
        int width = A.length;
        int depth = B.length;
        int[][] dp = new int[2][width];
        
        int maxLength = 0;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(B[y] == A[x]){
                    dp[y % 2][x] = (y > 0 && x > 0)? (dp[(y - 1) % 2][x - 1] + 1): 1;
                    maxLength = Math.max(dp[y % 2][x], maxLength);
                }
                else{
                    dp[y % 2][x] = 0;
                }
            }
        }
        return maxLength;
    }
 
    public static void main(String[] args){
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};
        Solution sol = new Solution();

        System.out.println("A: " + Arrays.toString(A));
        System.out.println("B: " + Arrays.toString(B));
        System.out.println("Maximum length: " + sol.findLength(A, B));
    }
}
