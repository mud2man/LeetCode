/* BFS: Time:O(4^n) Space:O(4^n)
 */

import java.util.*; // Stack

public class Solution {
    public double soupServings(int N) {
        if(N == 0){
            return 0.5;
        }
        int times = ((N - 1) / 50) + 1;
        int ub = (N - 1) / 25 + 1;
        int[][] diffs = {{4, 0}, {3, 1}, {2, 2}, {1, 3}};
        HashMap<String, Double> queue = new HashMap<String, Double>(); 
        queue.put("0,0", 1.0);
        double aEndProb = 0.0;
        int[] nextVolume = {0, 0};
        int[] volume = {0, 0};
        
        for(int i = 0; i < times; ++i){
            HashMap<String, Double> nextqQeue = new HashMap<String, Double>();
            for(Map.Entry<String, Double> entry: queue.entrySet()){
                String[] keys = entry.getKey().split(",");
                volume[0] = Integer.parseInt(keys[0]);
                volume[1] = Integer.parseInt(keys[1]);
                double prob = entry.getValue();
                for(int[] diff: diffs){
                    nextVolume[0] = volume[0] + diff[0];
                    nextVolume[1] = volume[1] + diff[1];
                    if(nextVolume[0] >= ub && nextVolume[1] >= ub){
                        aEndProb += 0.125*prob;
                    }
                    else if(nextVolume[0] >= ub){
                        aEndProb += 0.25*prob;
                    }
                    else if(nextVolume[1] >= ub){
                        continue;
                    }
                    else{
                        String key = Integer.toString(nextVolume[0]) + "," + Integer.toString(nextVolume[1]);
                        if(nextqQeue.containsKey(key)){
                            nextqQeue.put(key, nextqQeue.get(key) + prob * 0.25);
                        }
                        else{
                            nextqQeue.put(key, prob * 0.25);
                        }
                    }
                }
            }
            queue = nextqQeue;
        }
        return aEndProb;
    }

    public static void main(String[] args){
        int N = 50;;
        Solution sol;
        sol = new Solution();
        System.out.println("N: " + N);
        System.out.println("Probability: " + sol.soupServings(N));
    }
}
