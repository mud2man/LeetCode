/* Hash: Time:O(n), Space:O(n). LeetCode has simpler solution
 * 1. Keep a hashMap "point2Length" to store the mapping between node and length. e.g.,{1, 2, 3, 4, 5}, map.get(1) = 5, map.get(5) = 5
 * 2. In each loop, check if the new node can be attached to the begin or end of existing sequence
 * 3. Then, update point2Length and maxLength
 * 4. Because we prevent duplicated, the nodes on the "leftLength" and "rightLength" will not overlap
 */         

import java.util.*;

public class Solution {
    public int longestConsecutive(int[] nums) {
        int maxLength = 0;
        HashMap<Integer, Integer> point2Length = new HashMap<Integer, Integer>();
        for(int num: nums){
            if(point2Length.containsKey(num)){
                continue;
            }else{
                int leftLength = point2Length.getOrDefault(num - 1, 0);
                int rightLength = point2Length.getOrDefault(num + 1, 0); 
                int sum = leftLength + rightLength + 1;
                point2Length.put(num, 0);
                point2Length.put(num - leftLength, sum);
                point2Length.put(num + rightLength, sum);
                maxLength = Math.max(maxLength, sum);
            }
        }
        return maxLength;
    }

    public static void main(String[] args){
        int[] nums = {100, 4, 200, 1, 3, 2};
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("longest consecutive length: " + sol.longestConsecutive(nums));
    }
}
