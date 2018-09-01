/* TreeMap: Time:O(logn), Space:O(n)
 * 1. Create a TreeMap to store weight->index, and use floorKey() to get the weight
 */

import java.util.*;

public class Solution{
    int weight = 0;
    TreeMap<Integer, Integer> map;
    Random rand;
    
    public Solution(int[] w) {
        map = new TreeMap<>();
        for(int i = 0; i < w.length; ++i){
            map.put(weight, i);
            weight += w[i];
        }
        rand = new Random();
    }
    
    public int pickIndex() {
        int num = rand.nextInt(weight);
        int key = map.floorKey(num);
        return map.get(key);
    }

    public static void main(String[] args){
        Solution sol;
        int[] w = {1, 3};
        sol = new Solution(w);
        
        System.out.println("w: " + Arrays.toString(w));
        System.out.println("pick(): " + sol.pickIndex());
        System.out.println("pick(): " + sol.pickIndex());
        System.out.println("pick(): " + sol.pickIndex());
        System.out.println("pick(): " + sol.pickIndex());
    }
}
