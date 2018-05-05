/* PostOrder: Time:O(n), Space:O(h)
 * 1. Traverse all nodes using postorder traversal to pure tree
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
    private boolean noOne(TreeNode root){
        if(root == null){
            return true;
        }
        
        boolean left = noOne(root.left);
        root.left = (left == true)? null: root.left;
        boolean right = noOne(root.right);
        root.right = (right == true)? null: root.right;
        
        if(root.val == 1){
            return false;
        }
        else{
            return (left & right);
        }    
    }
    
    public TreeNode pruneTree(TreeNode root) {
        noOne(root);
        return (root != null && root.val == 0 && root.left == null && root.right == null)? null: root;
    }

    private void preorder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + ",");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     1
         *      \
         *       0
         *      / \
         *     0   1
         */
        root = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        sol = new Solution();
        System.out.print("before prune: ");
        sol.preorder(root);
        sol.pruneTree(root);
        System.out.print("\nafter prune: ");
        sol.preorder(root);
        System.out.println("");
    }
}
