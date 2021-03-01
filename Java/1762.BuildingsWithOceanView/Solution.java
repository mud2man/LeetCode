/* Greedy: Time:O(n), Space:O(n)
 * 1. Scan from right end and keeping update max hight ever seen
 * 2. Put idx which is less than max high
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int[] findBuildings(int[] heights) {
        Deque<Integer> oceanBuildingIdx = new LinkedList<>();
        int maxHeight = 0;
        for(int i = heights.length - 1; i >= 0; i--){
            if(heights[i] > maxHeight){
                oceanBuildingIdx.addFirst(i);
            }
            maxHeight = Math.max(maxHeight, heights[i]);
        }
        
        int[] ret = new int[oceanBuildingIdx.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i] = oceanBuildingIdx.pollFirst();
        }
        return ret;
    }
  
    public static void main(String[] args){
        int[] heights = {4, 2, 3, 1};
        Solution sol = new Solution();
        System.out.println("heights:" + Arrays.toString(heights));
        System.out.println("ocean view buildings:" + Arrays.toString(sol.findBuildings(heights)));
    }
}
