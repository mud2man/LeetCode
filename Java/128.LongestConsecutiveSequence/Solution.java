/* Hash: Time:O(n), Space:O(n). LeetCode has simpler solution
 * 1. Keep a hashMap "lengthsMap" to store the mapping between node and length
 * 2. Keep a hashMap "headsMap" to store the mapping between node and available head
 * 3. Keep a hashMap "tailsMap" to store the mapping between node and available tail
 * 4. In each loop, check if the new node can be attached to the beginning of existing sequence
 * 5. Then, check if the new sequence can be merged to the tail of a existing sequence
 */         

import java.util.*;

public class Solution {
    public int longestConsecutive(int[] nums) {
        int maxLength = (nums.length > 0)? 1: 0;
        Set<Integer> used = new HashSet<Integer>();
        HashMap<Integer, Integer> lengthsMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> headsMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> tailsMap = new HashMap<Integer, Integer>();
        
        for(int num: nums){
            if(used.contains(num)){
                continue;
            }
            else{
                used.add(num);
            }
            
            if(headsMap.containsKey(num)){
                int previousHead = headsMap.get(num);
                int previousLength = lengthsMap.get(previousHead);
                int previousTail = previousHead + previousLength;
                headsMap.remove(previousHead - 1);
                lengthsMap.remove(previousHead);
                tailsMap.put(previousTail, num);
                headsMap.put(num - 1, num);
                lengthsMap.put(num, previousLength + 1);
                maxLength = Math.max(maxLength, previousLength + 1);
            }
            else{
                lengthsMap.put(num, 1);
                tailsMap.put(num + 1, num);
                headsMap.put(num - 1, num);
            }
            
            //merge
            if(tailsMap.containsKey(num)){
                int firstHead = tailsMap.get(num);
                int mergedLength = lengthsMap.get(num) + lengthsMap.get(firstHead);
                headsMap.remove(num - 1);
                tailsMap.remove(num);
                lengthsMap.remove(num);
                lengthsMap.put(firstHead, mergedLength);
                tailsMap.put(firstHead + mergedLength, firstHead);
                maxLength = Math.max(maxLength, mergedLength);
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
