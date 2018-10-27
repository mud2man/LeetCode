/* Post order: Time:O(n), Space:O(h)
 * 1. Invert left and right subtree, and then invert left and right themselves
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
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    public void dump(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + "->");
        dump(root.left);
        dump(root.right);
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     1
         *    / \ 
         *   3   4
         */
        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        
        System.out.println("before invert: ");
        sol.dump(root);
        System.out.println("");

        sol.invertTree(root);

        System.out.println("after invert: ");
        sol.dump(root);
        System.out.println("");
    }
}
