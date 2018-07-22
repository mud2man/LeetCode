/* Backtrack: Time:O(n!), Space:O(n) 
 * 1. Use backtrack to find all the combinations, and get tne minimum steps
 */

import java.util.*;

public class Solution{
    private void backtrack(String key, int kIdx, String ring, int rIdx, int steps, Map<Character, List<Integer>> map, int[] min){
        if(kIdx == key.length()){
            min[0] = Math.min(min[0], steps);
            return;
        }
        
        if(steps >= min[0]){
            return;
        }
        
        char k = key.charAt(kIdx);
        List<Integer> nextIdxes = map.get(k);
        for(int nextIdx: nextIdxes){
            int dis = Math.abs(rIdx - nextIdx);
            int currStep = Math.min(dis, ring.length() - dis) + 1;
            backtrack(key, kIdx + 1, ring, nextIdx, steps + currStep, map, min);
        }
    }
    
    public int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> keyMap = new HashMap<>();
        for(int i = 0; i < ring.length(); ++i){
            char c = ring.charAt(i);
            keyMap.putIfAbsent(c, new ArrayList<Integer>());
            keyMap.get(c).add(i);
        }
        
        int[] min = {Integer.MAX_VALUE};
        backtrack(key, 0, ring, 0, 0, keyMap, min);
        return min[0];
    }
 
    public static void main(String[] args){
        String ring = "godding";
        String key = "gd";
        Solution sol = new Solution();

        System.out.println("ring:" + ring);
        System.out.println("key:" + key);
        System.out.println("minimum steps: " + sol.findRotateSteps(ring, key));
    }
}
