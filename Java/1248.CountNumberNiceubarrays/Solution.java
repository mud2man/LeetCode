/* Two pointers: Time:O(n), Space:O(1)
 * 1. Have head and tail pointer to record the window containing k odd numbers
 * 2. Each round, move tail until reach an odd number and accumulate rightCount
 * 3. Move head until reach an odd number and accumulate leftCount
 * 3. Accumulate count by (rightCount * leftCount)
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int tail = 0;
        int oddCount = 0;
        while(tail < nums.length && oddCount < k){
            oddCount +=(nums[tail++] % 2 == 1)? 1: 0;
        }
        tail--;
        
        if(oddCount < k){
            return 0;
        }
        
        int head = 0;
        int count = 0;
        while(tail < nums.length){
            //shift tail
            int rightCount = 1;
            while(tail < nums.length - 1 && nums[tail + 1] % 2 == 0){
                tail++;
                rightCount++;
            }
            tail++;
            
            int leftCount = 1;
            while(head < tail && nums[head] % 2 == 0){
                head++;
                leftCount++;
            }
            head++;
            count += rightCount * leftCount;
        }
        return count;
    }
 
    public static void main(String[] args){
        String word1 = "cabaa";
        String word2 = "bcaaa";
        Solution sol = new Solution();
        System.out.println("word1" + word1 + ", word2:" + word2);
        System.out.println("merge:" + sol.largestMerge(word1, word2));
    }
}
