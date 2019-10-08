/* Math: Time:O(n), Space:O(1)
 * 1. The absolute value must coming from the following 8 combinations, where i > j
 * 2. combination0: (arr1[i] + arr2[i] + i) - (arr1[j] + arr2[j] + j)
 * 3. combination1: (arr1[i] + arr2[i] - i) - (arr1[j] + arr2[j] + j)
 * 4. combination2: (arr1[i] - arr2[i] + i) - (arr1[j] + arr2[j] + j)
 * 5. combination3: (arr1[i] - arr2[i] - i) - (arr1[j] + arr2[j] + j)
 * 6. combination4: (-arr1[i] + arr2[i] + i) - (-arr1[j] + arr2[j] + j)
 * 7. combination5: (-arr1[i] + arr2[i] - i) - (-arr1[j] + arr2[j] - j)
 * 8. combination6: (-arr1[i] - arr2[i] + i) - (-arr1[j] - arr2[j] + j)
 * 9. combination7: (-arr1[i] - arr2[i] - i) - (-arr1[j] - arr2[j] - j)
 * 10 When we traverse arr[i], the "left" factor is fixed, we need to get the smallest of right factor to achive maximum for each combination
 * 11 Update max, and update dp[m] by min(dp[m], left) in the loop
 */

import java.util.*; // Stack

public class Solution {
    private int combinationSum(int x, int y, int i, int mode){
        switch(mode){
            case 0:
                return x + y + i;
            case 1:
                return x + y - i;
            case 2:
                return x - y + i;
            case 3:
                return x - y - i;
            case 4:
                return -x + y + i;
            case 5:
                return -x + y - i;
            case 6:
                return -x - y + i;
            case 7:
                return -x - y - i;
        }
        return -1;
    }
    
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int length = arr1.length;
        int[] dp = new int[8];
        for(int m = 0; m < 8; ++m){
            dp[m] = combinationSum(arr1[0], arr2[0], 0, m);
        }
        int max = 0;
        for(int i = 1; i < length; ++i){
            for(int m = 0; m < 8; ++m){
                int left = combinationSum(arr1[i], arr2[i], i, m);
                max = Math.max(max, left - dp[m]);
                dp[m] = Math.min(dp[m], left);
            }
        }
        return max;
    }
 
    public static void main(String[] args){
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {-1, 4, 5, 6};
        Solution sol = new Solution();
        System.out.println("arr1:" + Arrays.toString(arr1));
        System.out.println("arr2:" + Arrays.toString(arr2));
        System.out.println("max absolute value:" + sol.maxAbsValExpr(arr1, arr2));
    }
}
