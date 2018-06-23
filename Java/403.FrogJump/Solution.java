/* Dynamic Programming: Time:O(n^2), Space:O(n^2). LeetCode has a O(3^n) DFS solution, but beat 99%
 * 1. pos2Idx is to store stone's position to index
 * 2. dp[i] is the previous steps which can reach the current stone
 * 3. In each iteration, traverse the prevSteps of the current stoe, and see if prevStep - 1, prevStep, prevStep + 1 can reach next
 * 4. Return true if idx (stones.length - 1) can be reached
 */         

import java.util.*;

public class Solution {
    public boolean canCross(int[] stones) {
        HashMap<Integer, Integer> pos2Idx = new HashMap<Integer, Integer>();
        List<Set<Integer>> dp = new ArrayList<Set<Integer>>();
        for(int i = 0; i < stones.length; ++i){
            dp.add(new HashSet<Integer>());
            pos2Idx.put(stones[i], i);
        }
        
        dp.get(0).add(0);
        for(int i = 0; i < stones.length; ++i){
            Set<Integer> prevSteps = dp.get(i);
            int currPos = stones[i];
            for(int prevStep: prevSteps){
                for(int j = -1; j < 2; ++j){
                    int nextStep = prevStep + j;
                    if(nextStep <= 0){
                        continue;
                    }
                    int nextPos = nextStep + currPos;
                    if(pos2Idx.containsKey(nextPos)){
                        int nextIdx = pos2Idx.get(nextPos);
                        if(nextIdx == stones.length - 1){
                            return true;
                        }
                        dp.get(nextIdx).add(nextPos - currPos);
                    }
                }
            }
        }
        return (dp.get(stones.length - 1).size() > 0);
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17}; 
        System.out.println("stones: " + Arrays.toString(stones));
        System.out.println("can cross river? " + sol.canCross(stones));
    }
}
