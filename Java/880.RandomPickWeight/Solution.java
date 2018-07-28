/* TreeMap: Pick: Time:O(logn), Space:O(1), Constructor:O(n), Space:O(n)
 * 1. Use the size as the weight for random number, and have a tree map to store the key-vale(weight-rect)
 * 2. In pick, find a random number between 0 and total weight. The call floorKey to find the according weight, then get the rect
 * 3. Find a random point from the rect
 */

import java.util.*;

public class Solution {
    
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
        int[] w = {1, 3};
        Solution sol = new Solution(w);
        
        System.out.println("w: " + Arrays.toString(w));
        System.out.println("pickIndex(): " + sol.pickIndex());
        System.out.println("pickIndex(): " + sol.pickIndex());
        System.out.println("pickIndex(): " + sol.pickIndex());
        System.out.println("pickIndex(): " + sol.pickIndex());
    }
}
