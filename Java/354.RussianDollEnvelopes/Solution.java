/* Dynamic Progamming: Time:O(nlogn), Space:O(n). LeetCode has simpler solution
 * 1. Sort envelopes with envelope[0](width) with higher priority, ex: {5, 4}, {6, 4}, {6, 7}, {2, 3} => {2, 3}, {5, 4}, {6, 4}, {6, 7}
 * 2. Have a map "height2Depth" to record the mapping from height(envelope[1]) to depth, which keeps the depth are strickly accending
 * 3. In the loop, first, get the "heights" with the same width
 * 4. Get the depth by getting the gratest hight which among all smaller height and plus 1
 * 5. Remove the right height of "height" from height2Depth if its depth is bigger than or equal to that of right height
 * 6. Step 5 can guarantee strickly accending of depths of "height2Depth"
 *
 * ex: envelopes = {2, 3}, {2, 4}, {2, 7}, {5, 5}, {5, 7}, {6, 5}
 *     loop[0]: height2Depth = {3: 1}
 *     loop[1]: height2Depth = {3: 1, 5: 2}
 *     loop[2]: height2Depth = {3: 1, 5: 2}
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    private class DollComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            if(x[0] != y[0]){
                return (x[0] < y[0])? -1: 1;
            }
            else{
                return (x[1] < y[1])? -1: ((x[1] > y[1])? 1: 0);
            }
        }
    }
    
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new DollComparator());
        TreeMap<Integer, Integer> height2Depth = new TreeMap<>();
        int max = 0;
        for(int i = 0; i < envelopes.length; ++i){
            int width = envelopes[i][0];
            List<Integer> heights = new ArrayList<>();
            while(i < envelopes.length && width == envelopes[i][0]){
                heights.add(envelopes[i++][1]);
            }
            i--;
            
            Map<Integer, Integer> nextHeight2Depth = new HashMap<>();
            for(int height: heights){
                Integer lowerKey = height2Depth.lowerKey(height);
                if(lowerKey != null){
                    if(!nextHeight2Depth.containsKey(height)){
                        nextHeight2Depth.put(height, height2Depth.get(lowerKey) + 1);
                    }
                    else{
                        nextHeight2Depth.put(height, Math.max(nextHeight2Depth.get(height), height2Depth.get(lowerKey) + 1));
                    }
                }
                else{
                    nextHeight2Depth.put(height, 1);
                }
                max = Math.max(max, nextHeight2Depth.get(height));
            }

            for(Map.Entry<Integer, Integer> entry: nextHeight2Depth.entrySet()){
                height2Depth.put(entry.getKey(), entry.getValue());
            }
            
            for(int j = heights.size() - 1; j >= 0; --j){
                if(height2Depth.higherKey(heights.get(j)) != null){
                    int higherKey = height2Depth.higherKey(heights.get(j));
                    if(height2Depth.get(heights.get(j)) >= height2Depth.get(higherKey)){
                        height2Depth.remove(higherKey);
                    }
                }
            }
        }
        return max;
    }
    
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] envelopes = {{5, 4},{6, 4},{6, 7},{2, 3}};
        System.out.print("envelopes: ");
        for(int[] envelope: envelopes){
            System.out.print(Arrays.toString(envelope) + " ");
        }
        System.out.println("");
        System.out.println("max number of dolls: " + sol.maxEnvelopes(envelopes));
    }
}
