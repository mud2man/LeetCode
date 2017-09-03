/* Binary search: O(nlogn)
 * 1. Have anincreasing sequence increaseList
 * 2. Iterate nums and binary search the index for nums[i], 
 * 3. If the index is equal to the size of increaseList, increase maxLen
 * 4. Otherwise, replace nums[index] with num
 *
 * ex: [10, 9, 2, 5, 3, 7, 101, 18]
 * time[0]: increaseList: 10
 * time[1]: increaseList: 9
 * time[2]: increaseList: 2
 * time[3]: increaseList: 2->5
 * time[4]: increaseList: 2->3
 * time[5]: increaseList: 2->3->7
 * time[6]: increaseList: 2->3->7->101
 * time[7]: increaseList: 2->3->7->18
 *
 */

import java.util.*;

public class Solution{
    public int lengthOfLIS(int[] nums) {
        LinkedList<Integer> increaseList = new LinkedList<Integer>();
        int maxLen = 0;

        for(int num: nums){
            int idx = Collections.binarySearch(increaseList, num);
            if(idx < 0){
                idx = -idx - 1;
                int size = increaseList.size();
                if(idx == size){
                    increaseList.add(num);
                    maxLen = size + 1;
                }
                else{
                    increaseList.set(idx, num);
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args){
        int i;
        Solution sol;
        int len;
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        
        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));

        len = sol.lengthOfLIS(nums);
        
        System.out.println("maximum increasing length: " + len);
    }
}
