/* Greedy: Time:O(n), Space:O(1)
 * 1. Shift a sliding window (headIdx, tailIdx), where all chars between headIdx and tailIdx are same
 * 2. We can only pick one of char from (headIdx, tailIdx), which should has max cost
 * 3. Move headIdx until it reaches s.length()
 */     

import java.util.*; // Stack

public class Solution {
    public int minCost(String s, int[] cost) {
        int headIdx = 0;
        int tailIdx = 0;
        int minCost = 0;
        while(headIdx < s.length()){
            char haedChar = s.charAt(headIdx);
            int costSum = 0;
            int costMax = 0;
            tailIdx = headIdx;
            while(tailIdx < s.length() && s.charAt(tailIdx) == haedChar){
                costSum += cost[tailIdx];
                costMax = Math.max(costMax, cost[tailIdx]);
                tailIdx++;
            }
            if(tailIdx > headIdx + 1){
                minCost += (costSum - costMax);
            }
            headIdx = tailIdx;
        }
        return minCost;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "abaac";
        int[] cost = {1, 2, 3, 4, 5};
        System.out.println("s:" + s);
        System.out.println("cost:" + Arrays.toString(cost));
        System.out.println("min cost:" + sol.minCost(s, cost));
    }
}
