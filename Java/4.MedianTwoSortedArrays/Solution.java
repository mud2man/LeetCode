/* binary search: Time:O(log(m + n)), Space:O(1)
 * 1. bigRight: the smallest element of right part in "big" array
 * 2. bigLeft: the biggest element of left part in "big" array
 * 3. smallRight: the smallest element of right part in "small" array
 * 4. smallLeft: the biggest element of left part in "small" array
 * 5. Increase lb if bigLeft > smallRight
 * 6. Decrease hb if smallLeft > bigRight
 * 7. Otherwise, pick the smaller between bigRight and smallRight, bigger between bigLeft and smallLeft
 */

import java.util.*;

public class Solution{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] big = (nums1.length >= nums2.length)? nums1: nums2;
        int[] small = (nums1.length < nums2.length)? nums1: nums2;
        int len = nums1.length + nums2.length;
        int lb = 0;
        int hb = small.length;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int smallLeft = (mid > 0)? small[mid - 1]: Integer.MIN_VALUE;
            int smallRight = (mid < small.length)? small[mid]: Integer.MAX_VALUE;
            int bigIdx = len / 2 - mid;
            int bigLeft = (bigIdx > 0)? big[bigIdx - 1]: Integer.MIN_VALUE;
            int bigRight = (bigIdx < big.length)? big[bigIdx]: Integer.MAX_VALUE;
            
            if(smallLeft <= bigRight && smallRight >= bigLeft){
                if(len % 2 == 1){
                    return (double)Math.min(bigRight, smallRight);
                }
                else{
                    int left = Math.max(bigLeft, smallLeft);
                    int right = Math.min(bigRight, smallRight);
                    return (double)(left + right) / 2.0;
                }
            }
            else if(smallLeft > bigRight){
                hb = mid - 1;
            }
            else{
                lb = mid + 1;
            }
        }
        return 0.0;
    }
  
    public static void main(String[] args){
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        Solution sol = new Solution();

        System.out.println("nums1:" + Arrays.toString(nums1));
        System.out.println("nums2:" + Arrays.toString(nums2));
        System.out.println("median: " + sol.findMedianSortedArrays(nums1, nums2));
    }
}
