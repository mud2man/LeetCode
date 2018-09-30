/* Use DFS: O(n)
 * 1. Traverse all nodes using DFS, and add the value if rightSides.size() == level
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
        private void dfs(TreeNode root, List<Integer> rightSides, int level){
        if(root == null){
            return;
        }
        
        if(rightSides.size() == level){
           rightSides.add(root.val); 
        }
        dfs(root.right, rightSides, level + 1);
        dfs(root.left, rightSides, level + 1);
    } 
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSides = new ArrayList();
        dfs(root, rightSides, 0);
        return rightSides;
    }
  
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        List<Integer> rightSide; 
        
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
        rightSide = sol.rightSideView(root);
        System.out.println("rightSide view: " + rightSide);
    }
}
