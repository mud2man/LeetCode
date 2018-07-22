/* Dynamic Programming: Time:O(n^3), Space:O(n) 
 * 1. Get the mapping, where key is character, value is the list of its index in ring
 * 2. dp.get(i).get(j) = the pair with minimum steps for substring ket.substring(i) starting at j'th pai's idx dp.get(i).get(j).idx
 * 3. Update dp.get(i).get(j) by currP.steps = Math.min(currP.steps, 1 + dis + nextPiar.steps), where nextPairs is dp.get(i + 1)
 * 4. Traverse the first pair list p.get(0), and get minSteps
 */

import java.util.*;

public class Solution{
    private class Pair{
        int idx;
        int steps;
        Pair(int i, int s){idx = i; steps =s;}
    }
    
    public int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> keyMap = new HashMap<>();
        for(int i = 0; i < ring.length(); ++i){
            char c = ring.charAt(i);
            keyMap.putIfAbsent(c, new ArrayList<Integer>());
            keyMap.get(c).add(i);
        }
        
        List<List<Pair>> dp = new ArrayList<List<Pair>>();
        for(int i = 0; i < key.length(); ++i){
            dp.add(new ArrayList<Pair>());
        }
        
        for(int i = key.length() - 1; i >= 0; --i){
            char k = key.charAt(i);
            if(i ==  (key.length() - 1)){
                List<Integer> nextIdxes = keyMap.get(k);
                for(int nextIdx: nextIdxes){
                    dp.get(i).add(new Pair(nextIdx, 1));
                }
            }
            else{
                List<Integer> currIdxes = keyMap.get(k);
                List<Pair> nextPairs = dp.get(i + 1);
                for(int currIdx: currIdxes){
                    Pair currP = new Pair(currIdx, Integer.MAX_VALUE);
                    for(Pair nextP: nextPairs){
                        int dis = Math.abs(currIdx - nextP.idx);
                        dis = Math.min(dis, ring.length() - dis);
                        currP.steps = Math.min(currP.steps, 1 + dis + nextP.steps);
                    }
                    dp.get(i).add(currP);
                }
            }
        }
        
        int minSteps = Integer.MAX_VALUE;
        for(Pair p: dp.get(0)){
            int dis = Math.abs(0 - p.idx);
            dis = Math.min(dis, ring.length() - dis);
            minSteps = Math.min(minSteps, p.steps + dis);
        }
        return minSteps;
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
