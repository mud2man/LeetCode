/* Time:O(n*m), Space:O(n), where n is the length of heights, m is V
 * 1. Move the drop to left as long as heights[i] <= heights[idx]
 * 2. If idx != K, increase heights[idx] and continue
 * 3. Otherwise, move the drop to right as long as heights[i] <= heights[idx]
 * 4. Increase heights[idx]
 */

import java.util.*;

public class Solution{
    public int[] pourWater(int[] heights, int V, int K) {
        while(V-- > 0){
            int idx = K;
            //left
            for(int i = idx - 1; i >= 0 && heights[i] <= heights[idx]; --i){
                if(heights[idx] > heights[i]){
                    idx = i;
                }
            }
            if(idx != K){
                heights[idx]++;
                continue;
            }
            //right
            for(int i = idx + 1; i < heights.length && heights[i] <= heights[idx]; ++i){
                if(heights[idx] > heights[i]){
                    idx = i;
                }
            }
            heights[idx]++;
        }
        return heights;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] heights = {2, 1, 1, 2, 1, 2, 2};
        int V = 4;
        int K = 3;
        System.out.println("heights:" + Arrays.toString(heights) + ", V:" + V + ", K:" + K);
        System.out.println("final state:" + Arrays.toString(sol.pourWater(heights, V, K)));
    }
}
