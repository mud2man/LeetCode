/* DFS: Time:O(n), Space:O(h). LeetCode has shorter solution, which pass the min, max ancestor to child, and return the max diff
 * 1. The return value is {min, max, maxdiff}
 * 2. We can cauculate min = min(root.val, left[0], right[0]), max = max(root.val, left[1], right[1])
 * 3. maxDiff = max(left[2], right[2], max(root.val - left[0]), max(root.val - left[1]), max(root.val - left[1]), max(root.val - left[1]))
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private int[] dfs(TreeNode root){
        if(root.left == null && root.right == null){
            return new int[]{root.val, root.val, 0};
        }else if(root.right == null){
            int[] left = dfs(root.left);
            int maxDiff = Math.max(Math.abs(root.val - left[0]), Math.abs(root.val - left[1]));
            maxDiff = Math.max(maxDiff, left[2]);
            return new int[]{Math.min(root.val, left[0]), Math.max(root.val, left[1]), maxDiff};
        }else if(root.left == null){
            int[] right = dfs(root.right);
            int maxDiff = Math.max(Math.abs(root.val - right[0]), Math.abs(root.val - right[1]));
            maxDiff = Math.max(maxDiff, right[2]);
            return new int[]{Math.min(root.val, right[0]), Math.max(root.val, right[1]), maxDiff};
        }else{
            int[] left = dfs(root.left);
            int[] right = dfs(root.right);
            int maxDiff = Math.max(Math.abs(root.val - left[0]), Math.abs(root.val - left[1]));
            maxDiff = Math.max(maxDiff, left[2]);
            maxDiff = Math.max(maxDiff, Math.abs(root.val - right[0]));
            maxDiff = Math.max(maxDiff, Math.abs(root.val - right[1]));
            maxDiff = Math.max(maxDiff, right[2]);
            int min = Math.min(root.val, left[0]);
            min = Math.min(min, right[0]);
            int max = Math.max(root.val, left[1]);
            max = Math.max(max, right[1]);
            return new int[]{min, max, maxDiff};
        }
    }
    
    public int maxAncestorDiff(TreeNode root) {
        int[] ret = dfs(root);
        return ret[2];
    }

    public static void main(String[] args){
        /* Generate a input tree
         *     4
         *    / \
         *   2   5
         *  / \   
         * 1   3  
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        Solution sol = new Solution();
        int maxDiff = sol.maxAncestorDiff(root);
        System.out.println("maxDiff:" + maxDiff);
    }
}
