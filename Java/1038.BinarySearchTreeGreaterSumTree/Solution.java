/* Reversed in order: Time:O(n), Space:O(h).
 * 1. We visit tree in reversed inorder, and accumulate the visied node.val to "sum"
 * 2. Update node.val by node.val + sum[0]
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
    private void helper(TreeNode root, int[] sum){
        if(root == null){
            return;
        }
        helper(root.right, sum);
        root.val += sum[0];
        sum[0] = root.val;
        helper(root.left, sum);
    }
    
    public TreeNode bstToGst(TreeNode root) {
        int[] sum = {0};
        helper(root, sum);
        return root;
    }
  
    public void preorder(TreeNode root) {
        if(root == null){
            return;
        }
        System.out.print(root.val + "->");
        preorder(root.left);
        preorder(root.right);
    }
 
    public static void main(String[] args){
        /* Generate a input tree
         *     4
         *    / \
         *   1   6
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        Solution sol = new Solution();
        System.out.print("before:");
        sol.preorder(root);
        System.out.println("");
        root = sol.bstToGst(root);
        System.out.print("after:");
        sol.preorder(root);
        System.out.println("");
    }
}
