/* PreOrder: O(n)
 * 1. Visit tree inordely, and append new level if levels.size() == depth 
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
        private void preorder(TreeNode root, int depth, List<List<Integer>> levels){
        if(root == null){
            return;
        }
        
        if(levels.size() == depth){
            levels.add(new ArrayList<>());
        }
        
        levels.get(depth).add(root.val);
        preorder(root.left, depth + 1, levels);
        preorder(root.right, depth + 1, levels);
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        preorder(root, 0, levels);
        return levels;
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        List<List<Integer>> levels;

        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \  /
         *   4   7 13 
         */
        root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(13);

        sol = new Solution();
        levels = sol.levelOrder(root);
        
        System.out.println("levels:");
        for(List<Integer> level: levels){
            System.out.println(level);
        }
    }
}
