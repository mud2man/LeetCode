/* TreeMap: Pick: Time:O(logn), Space:O(1), Constructor:O(n), Space:O(n)
 * 1. Use the size as the weight for random number, and have a tree map to store the key-vale(weight-rect)
 * 2. In pick, find a random number between 0 and total weight. The call floorKey to find the according weight, then get the rect
 * 3. Find a random point from the rect
 */

import java.util.*;

public class Solution {
    TreeMap<Integer, int[]> weight2Rec;
    int weight;
    Random rand;
    
    public Solution(int[][] rects) {
        weight = 0;
        weight2Rec = new TreeMap<>();
        for(int[] rect: rects){
            weight2Rec.put(weight, rect);
            int size = (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            weight += size;
        }
        rand = new Random();
    }
    
    public int[] pick() {
        int randNum = rand.nextInt(weight);
        int key = weight2Rec.floorKey(randNum);
        int[] rect = weight2Rec.get(key);
        int x = rect[0] + rand.nextInt(rect[2] - rect[0] + 1);
        int y = rect[1] + rand.nextInt(rect[3] - rect[1] + 1);
        return new int[]{x, y};
    }

    public static void main(String[] args){
        int[][] rects = {{-2, -7, 3, -1}, {3, -6, 10, 10}};
        Solution sol = new Solution(rects);
        
        System.out.println("pick(): " + Arrays.toString(sol.pick()));
        System.out.println("pick(): " + Arrays.toString(sol.pick()));
        System.out.println("pick(): " + Arrays.toString(sol.pick()));
        System.out.println("pick(): " + Arrays.toString(sol.pick()));
        System.out.println("pick(): " + Arrays.toString(sol.pick()));
    }
}
