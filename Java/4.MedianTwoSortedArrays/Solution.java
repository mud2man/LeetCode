/* Hash: Time:O(logn*logm) Space:O(1)
 * 1. Assume we have findKth API
 * 2. If (length1 + length2) is odd, then k = ((length1 + length2 - 1) / 2), retrun findKth(nums1, nums2, k)
 * 3. Otherwise, return (findKth(nums1, nums2, k) + findKth(nums1, nums2, k + 1)) / 2
 * 4. Implement findKth by getLessEqualCount, findKth may fail because the kth element may not in nums1
 * 5. Implement getLessEqualCount by Arrays.binarySearch, which return the number of elemnet less than or equal to target
 * 6. In the main function findMedianSortedArrays, if the invoke of findKth(nums1, nums2, k) fail, switch to findKth(nums2, nums1, k)
 */

import java.util.*;

public class Solution{
    private int getLessEqualCount(int[] nums1, int[] nums2, int target){
        int count1 = Arrays.binarySearch(nums1, target);
        if(count1 < 0){
            count1 = -(count1 + 1);
        }
        else{
            count1 = Arrays.binarySearch(nums1, target + 1);
            count1 = (count1 < 0)? -(count1 + 1): count1;
        }
        
        int count2 = Arrays.binarySearch(nums2, target);
        if(count2 < 0){
            count2 = -(count2 + 1);
        }
        else{
            count2 = Arrays.binarySearch(nums2, target + 1);
            count2 = (count2 < 0)? -(count2 + 1): count2;
        }
        return (count1 + count2);
    }
    
    public boolean findKth(int[] nums1, int[] nums2, int k, double[] target){
        boolean isFound = false;
        int hb = nums1.length - 1;
        int lb = 0;
        while(lb <= hb){
            int midIndex = (lb + hb) / 2;
            int lessEqualCount = getLessEqualCount(nums1, nums2, nums1[midIndex]);
            int lessCount = getLessEqualCount(nums1, nums2, nums1[midIndex] - 1);
            if(lessCount == k){
                hb = midIndex;
                isFound = true;
                break;
            }
            else if(lessCount < k && lessEqualCount > k){
                hb = midIndex;
                isFound = true;
                break;
            }
            else if(lessCount > k){
                hb = midIndex - 1;
            }
            else{
                lb = midIndex + 1;
            }
        }
        
        if(isFound == false){
            return false;
        }
        else{
            target[0] = (double)nums1[hb];
            return true;
        }
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        
        if(((length1 + length2) % 2) == 1){
            int k = (length1 + length2 - 1) / 2;
            double[] target = new double[1];
            if(findKth(nums1, nums2, k, target)){
                return target[0];
            }
            else{
                findKth(nums2, nums1, k, target);
                return target[0];
            }
        }
        else{
            int k = (length1 + length2 - 1) / 2;
            double[] target = new double[1];
            double[] medians = new double[2];
            if(findKth(nums1, nums2, k, target)){
                medians[0] = target[0];
            }
            else{
                findKth(nums2, nums1, k, target);
                medians[0] = target[0];
            }
            
            k++;
            if(findKth(nums1, nums2, k, target)){
                medians[1] = target[0];
            }
            else{
                findKth(nums2, nums1, k, target);
                medians[1] = target[0];
            }
            
            return (medians[0] + medians[1]) / 2;
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
