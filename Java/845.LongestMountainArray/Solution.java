/* Time:O(n), Space:O(1)
 * 1. Have a variable mode (0:flat, 1:increase, 2:decrease) to record previous status
 * 2. Update left slope and right slop, then update "ret" only if (leftSlope > 0 && rightSlope > 0)
 */

import java.util.*;

public class Solution{
    public int longestMountain(int[] A) {
        int ret = 0;
        int leftSlope = 0;
        int rightSlope = 0;
        int mode = 0; //0:flat, 1:increase, 2:decrease
        for(int i = 1; i < A.length; ++i){
            if(A[i] > A[i - 1]){
                leftSlope = (mode != 1)? 2: leftSlope + 1;
                rightSlope = 0;
                mode = 1;
            }
            else if(A[i] < A[i - 1]){
                rightSlope = (mode != 2)? 2: rightSlope + 1;
                mode = 2;
            }
            else{
                leftSlope = 0;
                rightSlope = 0;
                mode = 0;
            }
            ret = (leftSlope > 0 && rightSlope > 0)? Math.max(ret, leftSlope + rightSlope - 1): ret;
        }
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {2, 1, 4, 7, 3, 2, 5};
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("longest mountain: " + sol.longestMountain(A));
    }
}
