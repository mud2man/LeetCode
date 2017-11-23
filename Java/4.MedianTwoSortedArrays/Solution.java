/* Hash: Time:O(log(m + n)), Space:O(1)
 */

import java.util.*;

public class Solution{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] big = (nums1.length > nums2.length)? nums1: nums2;
        int[] small = (nums1.length <= nums2.length)? nums1: nums2;
        int size = nums1.length + nums2.length;
        
        //corner case
        if(small.length == 0){
            if(big.length == 0){
                return 0.0;
            }
            else if((big.length % 2) == 1){
                return (double)big[big.length / 2];
            }
            else{
                return (double)(big[big.length / 2] + big[(big.length / 2) - 1]) / 2.0;
            }
        }
        
        int halfSize = size / 2;
        int lbIndex = 0;
        int hbIndex = 0;
        if((size % 2) == 1){
            hbIndex = (small[small.length - 1] > big[big.length - 1])? small.length - 2: small.length - 1;
        }
        else{
            hbIndex = small.length - 1;
        }
        
        //binary search
        int lb = lbIndex;
        int hb = hbIndex;
        int bigIndex;
        while(lb <= hb){
            int midIndex = (lb + hb) / 2;
            bigIndex = halfSize - midIndex - 2;
            if(small[midIndex] <= big[bigIndex + 1] && (midIndex == hbIndex || big[bigIndex] <= small[midIndex + 1])){
                hb = midIndex;
                break;
            }
            else if(small[midIndex] > big[bigIndex + 1]){
                hb = midIndex - 1;
            }
            else{
                lb = midIndex + 1;
            }
        }
        
        int smallIndex = hb;
        bigIndex = (smallIndex == -1)? halfSize: (halfSize - smallIndex - 2);

        if(smallIndex == -1){
            if((size % 2) == 1){
                return (double)Math.min(small[0], big[halfSize]);
            }
            else{
                if(big.length > halfSize){
                    int median0 = big[halfSize - 1];
                    int median1 = Math.min(big[halfSize], small[0]);
                    return (double)(median0 + median1) / 2.0;
                }
                else{        
                    return (double)(big[halfSize - 1] + small[0]) / 2.0;
                }
            }
        }
        else{
            if((size % 2) == 1){
                if(smallIndex < (small.length - 1)){
                    return (double)Math.min(small[smallIndex + 1], big[bigIndex + 1]);
                }
                else{
                    return (double)Math.max(small[smallIndex], big[bigIndex + 1]);
                }
            }
            else{
                int median0 = (bigIndex != - 1)? Math.max(small[smallIndex], big[bigIndex]): small[smallIndex];
                int median1 = (smallIndex == (small.length - 1))? big[bigIndex + 1]: Math.min(small[smallIndex + 1], big[bigIndex + 1]);
                return (double)(median0 + median1) / 2.0;
            }
        }
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
