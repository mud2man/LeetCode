/* binary search: Time:O(log(m + n)), Space:O(1)
 * 1. bigRight: the smallest element of right part in "big" array
 * 2. bigLeft: the biggest element of left part in "big" array
 * 3. smallRight: the smallest element of right part in "small" array
 * 4. smallLeft: the biggest element of left part in "small" array
 * 5. Increase lb if bigRight < smallLeft
 * 6. Decrease hb if bigLeft > smallRight
 * 7. Otherwise, pick the smaller between bigRight and smallRight, bigger between bigLeft and smallLeft
 */

import java.util.*;

public class Solution{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] big = (nums1.length >= nums2.length)? nums1: nums2;
        int[] small = (nums1.length < nums2.length)? nums1: nums2;  
        int halfSize = (small.length + big.length) / 2;
        int lb = halfSize - small.length; //count all nums in small
        int hb = halfSize; //doesn't count any num in small
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int bigRight = (mid < big.length)? big[mid]: Integer.MAX_VALUE;
            int bigLeft = (mid > 0)? big[mid - 1]: Integer.MIN_VALUE;
            int smallIdx = halfSize - mid;
            int smallRight = (smallIdx < small.length)?  small[smallIdx]: Integer.MAX_VALUE;
            int smallLeft = (smallIdx > 0)? small[smallIdx - 1]: Integer.MIN_VALUE;
            
            if(bigRight >= smallLeft && smallRight >= bigLeft){
                if((small.length + big.length) % 2 == 0){
                    int right = Math.min(bigRight, smallRight); 
                    int left = Math.max(bigLeft, smallLeft); 
                    return (double)(right + left) / 2.0;
                }
                else{
                    return (double)Math.min(bigRight, smallRight);   
                }
            }
            else if(bigRight < smallLeft){
                lb = mid + 1;
            }
            else{
                hb = mid - 1;
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
