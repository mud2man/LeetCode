/* Select: Time:O(n), Space:O(n). LeetCode hase O(n) + O(1) solution
 * 1. Apply selectKth to find the median, s.t. array => l0, l1, m2, m3, s4, s5
 * 2. Interleave the array with the two half, s.t. array => m3, l0, s4, l1, s5, m2
 */

import java.util.*;

public class Solution{
    private void swap(int[] nums, int index0, int index1){
        int tmp = nums[index0];
        nums[index0] = nums[index1];
        nums[index1] = tmp;
    }
    
    private void selectKth(int[] nums, int start, int end, int medianIndex){
        if(medianIndex < start || medianIndex > end ){
            return;
        }
        else{
            int ptr0 = start -1;
            int ptr1 = start -1;
            int target = nums[end];
            for(int i = start; i <= end; ++i){
                if(target == nums[i]){
                    swap(nums, ++ptr1, i);
                }
                else if(target < nums[i]){
                    swap(nums, ++ptr1, i);
                    swap(nums, ++ptr0, ptr1);
                }
                else{
                    continue;
                }
            }

            if(medianIndex <= ptr0){
                selectKth(nums, start, ptr0, medianIndex);
            }
            else if(medianIndex > ptr1){
                selectKth(nums, ptr1 + 1, end, medianIndex);
            }
            else{
                return;
            }
        }
    }
    
    public void wiggleSort(int[] nums) {
        int size = nums.length;
        int medianIndex = size / 2;
        
        selectKth(nums, 0 , size - 1, medianIndex);
        System.out.println(Arrays.toString(nums));
        
        int[] tmp = new int[size];
        System.arraycopy(nums, 0, tmp, 0, size);
        
        int index = 0;
        for(int i = 1; i < size; i += 2){
            nums[i] = tmp[index++];
        }
        
        for(int i = 0; i < size; i += 2){
            nums[i] = tmp[index++];
        }
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 5, 1, 1, 6, 4};

        System.out.println("nums @ before: " + Arrays.toString(nums));
        sol = new Solution();    
        sol.wiggleSort(nums);
        System.out.println("nums @ after: " + Arrays.toString(nums));
    }
}
