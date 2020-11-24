/* Min-max: Time:O(n), Space:O(1)
 * 1. Have "lastSeenIndex" to record the last seen index for 'a', 'b', and 'c'
 * 2. Traverse s and update lastSeenIndex, then accumulate count with min(lastSeenIndex[0], lastSeenIndex[1], lastSeenIndex[2])
 */

import java.util.*; // Stack


public class Solution {
    public int numberOfSubstrings(String s) {
        int[] lastSeenIndex = {-1, -1, -1};
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            lastSeenIndex[c - 'a'] = i;
            int maxIdxContainAll = Math.min(lastSeenIndex[0], Math.min(lastSeenIndex[1], lastSeenIndex[2]));
            count += (maxIdxContainAll + 1);
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "abcabc"; 
        System.out.println("s:" + s);
        System.out.println("number of substrings containging a, b, and c:" + sol.numberOfSubstrings(s));
    }
}
