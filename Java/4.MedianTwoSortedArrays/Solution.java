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
        int[] big = null;
        int[] small = null;
        if(nums1.length <= nums2.length){
            big = nums1;
            small = nums2;
        }
        else{
            big = nums2;
            small = nums1;
        }
        
        int lb = 0;
        int hb = big.length;
        int halfSize = (big.length + small.length) / 2;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int bigRight = (mid == big.length)? Integer.MAX_VALUE: big[mid];
            int smallLeft = ((halfSize - mid - 1) < 0)? Integer.MIN_VALUE: small[halfSize - mid - 1];
            int bigLeft = (mid > 0)? big[mid - 1]: Integer.MIN_VALUE;
            int smallRight = ((halfSize - mid) == small.length)? Integer.MAX_VALUE: small[halfSize - mid];
            
            if(bigRight < smallLeft){
                lb = mid + 1;
            }
            else if(bigLeft > smallRight){
                hb = mid - 1;
            }
            else{
                int right = Math.min(bigRight, smallRight);
                int left = Math.max(bigLeft, smallLeft);
                if((nums1.length + nums2.length) % 2 == 1){
                    return (double)right;
                }
                else{
                    return (double)(right + left) / 2.0;
                }
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
