/* BFS: O(n)
 * 1. Compare current pair, and invoke helper(left.left, right.right) && helper(left.right, right.left)
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
    private boolean helper(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }
        else if(left != null && right != null){
            if(left.val != right.val){
                return false;
            }
            return helper(left.left, right.right) && helper(left.right, right.left);
        }
        else{
            return false;
        }
    }
    
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return helper(root.left, root.right);
    }

    public static void main(String[] args){
        List<List<Integer>> list;
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     3
         *    / \
         *   9   9
         *  /     \
         * 7       7
         */
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(7);
        root.right.right = new TreeNode(7);

        sol = new Solution();
        System.out.println("isSymmetric? " + sol.isSymmetric(root));
    }
}
