/* Trie and HashSet: O(n)
 * 1. Scan from the most i-th MSB, where i is from 31 to 0
 * 2. Keep the variable "max" to store the maximum "xor" with the mask of the most i-th MSB
 *
 * ex: nums = {2'b0011, 2'b1010, 2'b0100}
 * i = 3, max = 2'b0000, tmp = 2'b1000, prefix = {2'b0000, 2'b1000, 2'b0000} => max = 2'b1000 
 * i = 2, max = 2'b1000, tmp = 2'b1100, prefix = {2'b0000, 2'b1000, 2'b0100} => max = 2'b1100 
 * i = 1, max = 2'b1100, tmp = 2'b1110, prefix = {2'b0010, 2'b1010, 2'b0100} => max = 2'b1110 
 * i = 0, max = 2'b1110, tmp = 2'b1111, prefix = {2'b0011, 2'b1010, 2'b0100} => max = 2'b1110 
 */

import java.util.*; // Stack

public class Solution {
    public int findMaximumXOR(int[] nums) {
        int max, tmp, mask, idx;
        HashSet<Integer> set;
        
        max = 0;
        tmp = 0;
        mask = 0;
        for(idx = 31 ; idx >= 0 ; --idx){
            mask = mask | (1<<idx);
            set = new HashSet<Integer>();
            
            for(int num: nums){
                set.add(num & mask);
            }
            
            tmp = max | (1<<idx);
            for(int prefix: set){
                if(set.contains(tmp ^ prefix)){
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args){
        int removeCount;
        Solution sol;
        int[] nums = {3, 10, 4};

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("max xor: " + sol.findMaximumXOR(nums));
    }
}
