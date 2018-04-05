/* Greedy + Two pointers: Time:O(n), Space:O(1)
 * 1. Traverse the string record the last index of each char.
 * 2. Traverse again, if the end pointer is equal to the current index, then add end - start + 1
 * 3. Update end with max(end, lastIndex[c - 'a']);
 */

import java.util.*; // Stack

public class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] lastIndex = new int[26];
        for(int i = 0; i < S.length(); ++i){
            lastIndex[S.charAt(i) - 'a'] = Math.max(lastIndex[S.charAt(i) - 'a'], i);
        }
        
        List<Integer> ret = new ArrayList<Integer>();
        int start = 0;
        int end = 0;
        for(int index = 0; index < S.length(); ++index){
            char c = S.charAt(index);
            end = Math.max(end, lastIndex[c - 'a']);
            if(end == index){
                ret.add(end - start + 1);
                end++;
                start = end;
            }
        }
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol;
        String S = "ababcbacadefegdehijhklij";

        sol = new Solution();
        System.out.println("S: " + S);
        System.out.println("partitions: " + sol.partitionLabels(S));
	}
}
