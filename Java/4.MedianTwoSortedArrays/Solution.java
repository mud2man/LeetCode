/* binary search: Time:O(log(m + n)), Space:O(1)
 * 1. Let midIndex be the next index of of left part in small, and bigIndex the he next index of of left part in big
 * 2. Decrease hb if (midIndex > 0 && small[midIndex - 1] > big[bigIndex])
 * 3. Increase lb if (midIndex < small.length && big[bigIndex - 1] > small[midIndex])
 * 4. Otherwise, midIndex found, the find the maximun of the left part
 * 5. Then, find the minimum of the right part
 * 6. If size is odd, return maxLeft, otherwise return (maxLeft + minRight)/2
 */

import java.util.*;

public class Solution{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] big = (nums1.length > nums2.length)? nums1: nums2;
        int[] small = (nums1.length <= nums2.length)? nums1: nums2;
        int size = nums1.length + nums2.length;
        int halfSize = (size + 1) / 2;
        
        //binary search
        int lb = 0;
        int hb = small.length;
        while(lb <= hb){
            int midIndex = (lb + hb) / 2;
            int bigIndex = halfSize - midIndex;
            if(midIndex > 0 && small[midIndex - 1] > big[bigIndex]){
                hb = midIndex - 1;
            }
            else if(midIndex < small.length && big[bigIndex - 1] > small[midIndex]) {
                lb = midIndex + 1;
            }
            else{
                int maxLeft = 0;
                if(midIndex == 0){
                    maxLeft = big[halfSize - 1];
                }
                else if(bigIndex == 0){
                    maxLeft = small[halfSize - 1];
                }
                else{
                    maxLeft = Math.max(small[midIndex - 1], big[bigIndex - 1]);
                }
                
                if(size % 2 == 1){
                    return maxLeft;
                }
                
                int minRight = 0;
                if(midIndex == small.length){
                    minRight = big[halfSize - small.length];
                }
                else if(bigIndex == big.length){
                    minRight = small[halfSize - big.length];
                }
                else{
                    minRight = Math.min(small[midIndex], big[bigIndex]);
                }
                
                return (double)(maxLeft + minRight) / 2.0;
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
