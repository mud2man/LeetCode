/* Inorder: O(n)
 * 1. Traverse tree inorderly (flip the left subtree firstly), and return the new root
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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null){
            return null;
        }
        else if(root.left == null){
            return root;
        }
        else{
            TreeNode left = root.left;
            TreeNode right = root.right;
            TreeNode newRoot = upsideDownBinaryTree(left);
            left.left = right;
            left.right = root;
            root.left = null;
            root.right = null;
            return newRoot;
        }
    }
 
    private void preOrder(TreeNode root){
        if(root == null){
            return;
        }

        System.out.print(root.val + ", ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         *  / \ 
         * 4   5
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        sol = new Solution();

        System.out.println("Before upside down:");
        sol.preOrder(root); 
        System.out.println("");
        TreeNode newRoot = sol.upsideDownBinaryTree(root);
        System.out.println("After upside down:");
        sol.preOrder(newRoot); 
        System.out.println("");
    }
}
