/* Time:O(n*m), Space:O(n), where n is the length of heights, m is V
 * 1. Move the drop to left if it eventually drop, and invoke "helper" with the index of dropped
 * 2. If drop[0] == 0, which means the drop used. Then, return
 * 3. Otherwise, move drop to right if it eventually drop
 * 4. If drop[0] == 1, whcih means the drop cannot be used, so just rise at the current index
 */

import java.util.*;

public class Solution{
    private void helper(int[] heights, int index, int[] drop){
        int nextIndex = index - 1;
        while(nextIndex >= 0){
            if(heights[nextIndex] < heights[index]){
                helper(heights, nextIndex, drop);
                break;
            }
            else if (heights[nextIndex] > heights[index]){
                break;
            }
            nextIndex--;
        }
        
        if(drop[0] == 0){
            return;
        }
        
        nextIndex = index + 1;
        while(nextIndex < heights.length){
            if(heights[nextIndex] < heights[index]){
                helper(heights, nextIndex, drop);
                break;
            }
            else if (heights[nextIndex] > heights[index]){
                break;
            }
            nextIndex++;
        }
        
        if(drop[0] == 1){
            heights[index]++;
            drop[0]--;
        }
    }
    
    public int[] pourWater(int[] heights, int V, int K) {
        int[] drop = new int[1];
        for(int i = 0; i < V; ++i){
            drop[0] = 1;
            helper(heights, K, drop);
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
