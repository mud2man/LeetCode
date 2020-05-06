/* Binary Search: Time:O(m + nlogm), Space:(m)
 * 1. Have a map "char2Idxs" with key:char, value:list of indexs
 * 2. Track the current position of source by "sourceIdx"
 * 3. We need to find the least value in char2Idxs.get(c) which is larger of equal to sourceIdx
 * 4. We accumulate count and reset sourceIdx by char2Idxs.get(c).get(0) + 1, if sourceIdx is larger than any of char2Idxs.get(c)
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    public int shortestWay(String source, String target) {
        Map<Character, List<Integer>> char2Idxs = new HashMap<>();
        for(int i = 0; i < source.length(); ++i){
            char2Idxs.computeIfAbsent(source.charAt(i), key -> new ArrayList<>()).add(i);
        }
        int count = 1;
        int sourceIdx = 0;
        for(char c: target.toCharArray()){
            if(!char2Idxs.containsKey(c)){
                return -1;
            }
            List<Integer> idxs = char2Idxs.get(c);
            int idxOfIdxs = Collections.binarySearch(idxs, sourceIdx);
            if(idxOfIdxs >= 0){
                sourceIdx++;
            }else{
                idxOfIdxs = -(idxOfIdxs + 1);
                if(idxOfIdxs == idxs.size()){
                    count++;
                    sourceIdx = char2Idxs.get(c).get(0) + 1;
                }else{
                    sourceIdx = idxs.get(idxOfIdxs) + 1;
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String source = "abc";
        String target = "abcbc";
        System.out.println("source:" + source);
        System.out.println("target:" + target);
        System.out.println("minimum number of subsequences:" + sol.shortestWay(source, target));
    }
}
