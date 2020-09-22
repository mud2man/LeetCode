/* Dynamic programming + Recursive: Time:O(n^4), Space:O(n^3)
 * 1. dp[l][r][k] = the solution of {boxes[l], boxes[l + 1], ... boxes[r]} with appended k duplicates of boxes[r], which can express the case of merge
 * 2. dp[l][r][k] = max(dp[l][r][k], helper(boxes, dp, l, i, k + 1) + helper(boxes, dp, i + 1, r - 1, 0)), where l <= i < r
 */     

import java.util.*; // Stack

public class Solution {
    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[boxes.length][boxes.length][boxes.length];
        return helper(boxes, dp, 0, boxes.length - 1, 0);
    }
    
    private int helper(int[] boxes, int[][][] dp, int l, int r, int k){
        if(l > r){
            return 0;
        }
        
        while(r > l && boxes[r] == boxes[r - 1]){
            r--;
            k++;
        }
        if(dp[l][r][k] > 0){
            return dp[l][r][k];
        }
        dp[l][r][k] = helper(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1); //removed the right part first
        for(int i = l; i < r; ++i){
            // if there is a middle section be removed, and left part merged with right part
            // otherwise, the solution would be the same as the one removing the right part first
            if(boxes[i] == boxes[r]){  
                dp[l][r][k] = Math.max(dp[l][r][k], helper(boxes, dp, l, i, k + 1) + helper(boxes, dp, i + 1, r - 1, 0));
            }
        }
        return dp[l][r][k];
    } 
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};
        System.out.println("boxes:" + Arrays.toString(boxes));
        System.out.println("maximum points:" + sol.removeBoxes(boxes));
    }
}
