/* Dynamic programming: Time:O(n * m), Space:O(m)
 * 1. dp[y][x] = max dot product considering {nums1[0], nums1[1], .., nums1[y]} and {nums2[0], nums2[1], ..., nums2[y]}
 * 2. dp[y][x] = max(dp[y - 1][x], dp[y][x - 1], dp[y - 1][x - 1] + nums1[y] * nums2[x], nums1[y] * nums2[x]) 
 * 3. Return dp[(nums1.length - 1) % 2][nums2.length - 1]
 */     


import java.util.*; // Stack

public class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int[][] dp = new int[2][nums2.length];
        for(int y = 0; y < nums1.length; ++y){
            for(int x = 0; x < nums2.length; ++x){
                int top =(y > 0)? dp[(y - 1) % 2][x]: Integer.MIN_VALUE;
                int left =(x > 0)? dp[y % 2][x - 1]: Integer.MIN_VALUE;
                int topLeft =(y > 0 && x > 0)? dp[(y - 1) % 2][x - 1]: 0;
                topLeft = nums1[y] * nums2[x] + topLeft;
                dp[y % 2][x] = Math.max(Math.max(top, left), topLeft);
                //Corner case: take no number before nums1[y] and nums[x]
                dp[y % 2][x] = Math.max(dp[y % 2][x], nums1[y] * nums2[x]); 
            }
        }
        return dp[(nums1.length - 1) % 2][nums2.length - 1];
    }
   
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums1 = {2, 1, -2, 5};
        int[] nums2 = {3, 0, -6};
        System.out.println("nums1:" + Arrays.toString(nums1));
        System.out.println("nums2:" + Arrays.toString(nums2));
        System.out.println("max dot product:" + sol.maxDotProduct(nums1, nums2));
    }
}
