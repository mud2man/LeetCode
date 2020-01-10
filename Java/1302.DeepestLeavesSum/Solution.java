/* DFS: Time:O(n), Space:O(n).
 * 1. Visit each node and update level and level2Sum
 * 2. Return the max level between left and right
 */

import java.util.*;

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution{
    public int deepestLeavesSum(TreeNode root) {
        Map<Integer, Integer> level2Sum = new HashMap<>();
        int maxLevel = dfs(root, level2Sum, 0);
        return level2Sum.get(maxLevel);
    }
    
    private int dfs(TreeNode root, Map<Integer, Integer> level2Sum, int level){
        if(root == null){
            return level - 1;
        }
        level2Sum.putIfAbsent(level, 0);
        level2Sum.put(level, level2Sum.get(level) + root.val);
        return Math.max(dfs(root.left, level2Sum, level + 1), dfs(root.right, level2Sum, level + 1));
    }

    public static void main(String[] args){
        /* Generate a input tree
         *     3
         *    / \
         *   9   20
         *       / \
         *      15  7
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Solution sol = new Solution();
        System.out.println("deepest leaves sum:" + sol.deepestLeavesSum(root));
    }
}
