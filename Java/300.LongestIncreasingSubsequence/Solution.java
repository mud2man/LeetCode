/* Dynamic programing + binary search: O(nlogn)
 * 1. Creaet dp[] to store the smallest tail of increasing sequence with length i+1 on dp[i+1]
 * 2. Iterate nums and binary search the index for nums[i], 
 * 3. Update dp[i]
 *
 * ex: [10, 9, 2, 5, 3, 7, 101, 18]
 *    dp[0]: 10->9->2 (10) (9) (2) (2)    (2)    (2)       (2)            (2)
 *    dp[1]: 5->3     ()   ()  ()  (2->5) (2->3) (2->3)    (2->3)         (2->3)
 *    dp[2]: 7        ()   ()  ()  ()     ()     (2->3->7) (2->3->7)      (2->3->7)
 *    dp[3]: 101->18  ()   ()  ()  ()     ()     ()        (2->3->7->101) (2->3->7->18)
 *
 */

import java.util.*;

public class Solution{
    public int lengthOfLIS(int[] nums) {
        int[] dp;
        int size;
        int i;
        int len;
        int pos;
        
        size = nums.length;
        dp = new int[size];
        
        if(size == 0){
            return 0;
        }
        
        dp[0] = nums[0];
        len = 1;
        for(i = 1; i < size; ++i){
            pos = Arrays.binarySearch(dp, 0, len, nums[i]);
            if(pos < 0){
                pos = 0 - (pos + 1);
                dp[pos] = nums[i];
                if(pos == len){
                    len++;
                }
            }
        }
        
        return len;
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
