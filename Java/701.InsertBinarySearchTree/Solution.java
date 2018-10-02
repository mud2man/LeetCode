/* Time:O(h), Space:O(h)
 * 1. Traverse BST and find the correct null, and insert
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
    private void helper(TreeNode farther, TreeNode node, int val){
        if(node == null){
            if(farther.val > val){
                farther.left = new TreeNode(val);
            }
            else{
                farther.right = new TreeNode(val);
            }
            return;
        }    
        
        if(node.val > val){
            helper(node, node.left, val);
        }
        else{
            helper(node, node.right, val);
        }
    } 
    
    public TreeNode insertIntoBST(TreeNode root, int val) {
        helper(null, root, val);
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
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     4
         *    / \
         *   2   7
         *  / \   
         * 1   3  
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        int val = 5;
        System.out.println("val: " + val);
        System.out.println("before insert: ");
        sol.preorder(root);
        System.out.println("");

        root = sol.insertIntoBST(root, 5);

        System.out.println("after insert: ");
        sol.preorder(root);
        System.out.println("");
    }
}
