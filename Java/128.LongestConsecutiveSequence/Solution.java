/* Hash: Time:O(n), Space:O(n). LeetCode has simpler solution
 * 1. Keep a hashMap "lengthsMap" to store the mapping between node and length. e.g.,{1, 2, 3, 4, 5}, map.get(1) = 1, map.get(5) = 5
 * 2. In each loop, check if the new node can be attached to the begin or end of existing sequence
 * 3. Then, update lengthsMap and maxLength
 * 4. Because we prevent duplicated, the nodes on the "leftLength" and "rightLength" will not overlap
 */         

import java.util.*;

public class Solution {
    public int longestConsecutive(int[] nums) {
        int maxLength = 0;
        HashMap<Integer, Integer> lengthsMap = new HashMap<Integer, Integer>();
        
        for(int num: nums){
            if(lengthsMap.containsKey(num)){
                continue;
            }
            else{
                int leftLength = lengthsMap.containsKey(num - 1)? lengthsMap.get(num - 1): 0;
                int rightLength = lengthsMap.containsKey(num + 1)? lengthsMap.get(num + 1): 0;
                int sum = leftLength + rightLength + 1;
                
                if(leftLength == 0 || rightLength == 0){
                    lengthsMap.put(num, sum);
                }
                else {
                    lengthsMap.put(num, 0);
                }

                lengthsMap.put(num - leftLength, sum);
                lengthsMap.put(num + rightLength, sum);
                maxLength = Math.max(maxLength, sum);
            }
        }
        
        return maxLength;
    }

    public static void main(String[] args){
        Solution sol; 
        int[] nums = {100, 4, 200, 1, 3, 2};
        sol = new Solution();
        
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("longest consecutive length: " + sol.longestConsecutive(nums));
    }
}
