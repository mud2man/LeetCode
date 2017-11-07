/* Hash: Time:O(n) Space:O(1)
 * 1. Becuase the minimum distace = sum(treeToNut[i]*2) + min(squirrelToNut[i] - treeToNut[i]), where 0 <= i <= (length - 1)
 * 2. We can iterate all nuts to compute distance = sum(treeToNut[i]*2), and update minDiff = min(squirrelToNut[i] - treeToNut[i])
 * 3. Therefore, the answer is distance + minDiff
 */

import java.util.*;

public class Solution{
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int minDiff = Integer.MAX_VALUE;
        int distance = 0;
        
        for(int[] nut: nuts){
            int treeToNut = Math.abs(nut[0] - tree[0]);
            treeToNut += Math.abs(nut[1] - tree[1]);
            distance += treeToNut * 2;
            int squirrelToNut = Math.abs(nut[0] - squirrel[0]);
            squirrelToNut += Math.abs(nut[1] - squirrel[1]);
            int diff = squirrelToNut - treeToNut;
            minDiff = Math.min(minDiff, diff);  
        }
        
        return distance + minDiff;
    }

    public static void main(String[] args){
        int height = 5;
        int width = 7;
        int[] tree = {2, 2};
        int[] squirrel = {4, 4};
        int[][] nuts = {{3, 0}, {2, 5}};
        Solution sol = new Solution();

        System.out.println("height:" + height);
        System.out.println("width:" + width);
        System.out.println("tree:" + Arrays.toString(tree));
        System.out.println("squirrel:" + Arrays.toString(squirrel));
        System.out.println("nuts:");
        for(int[] nut: nuts){
            System.out.println(Arrays.toString(nut));
        }
        System.out.println("minimum distance: " + sol.minDistance(height, width, tree, squirrel, nuts));
    }
}
