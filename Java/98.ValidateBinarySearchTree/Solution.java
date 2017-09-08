/* DFS: O(n)
 * 1. Traverse all nodes using DFS with lower and upper bound
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
    public boolean dfs(TreeNode root, TreeNode lb, TreeNode hb){
        if(root == null){
            return true;
        }
        
        boolean isValid = true;
        TreeNode left = root.left;
        TreeNode right = root.right;
          
        if(left != null && left.val >= root.val){
            return false;
        }
        
        if(left != null && lb != null && left.val <= lb.val){
            return false;
        }
        
        if(dfs(left, lb, root) == false){
            return false;
        }
        
        if(right != null && right.val <= root.val){
            return false;
        }

        if(right != null && hb != null && right.val >= hb.val){
            return false;
        }
        
        return dfs(right, root, hb);  
    }
    
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }
  
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
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
        
        System.out.println("isValid: " + sol.isValidBST(root));
    }
}
