/* Math: Time:O(logn), Space:O(logn)
 * 1. In the original complete binary tree, the left child is 2x and right child is (2x + 1) given the parent node is x
 * 2. Originally, we can track back to parent node by label / 2. However, the parent leve has reverse order. 
 * 3. So we need to do correction by end - (parent - start)
 * 4. We continue the while loop until level == 0
 */

import java.util.*;

public class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> levelAccumulatedSum = new ArrayList<>();
        int levelSum = 0;
        int diff = 1;
        while(levelSum < label){
            levelSum += diff;
            levelAccumulatedSum.add(levelSum);
            diff *= 2;
        }
        List<Integer> path = new ArrayList<>();
        path.add(label);
        int level = levelAccumulatedSum.size() - 1;
        while(level > 0){
            level--;
            int start = (level > 0)? levelAccumulatedSum.get(level - 1) + 1: 1;
            int end = levelAccumulatedSum.get(level);
            int parent = label / 2;
            label = end - (parent - start);
            path.add(label);
        }
        Collections.reverse(path);
        return path;
    }
  
    public static void main(String[] args){
        int label = 14;
        Solution sol = new Solution();
        System.out.println("label:" + label);
        System.out.println("path:" + sol.pathInZigZagTree(label));
    }
}
