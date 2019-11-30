/* DFS: Time:O(n), Space:O(h). Onather solution: We can put node into list inorderly, and confirm if thre previous has smaller val
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
    private boolean preOrder(TreeNode root, TreeNode lb, TreeNode hb){
        if(root == null){
            return true;
        }
        if((lb != null && root.val <= lb.val) || (hb != null && root.val >= hb.val)){
            return false;
        }
        return preOrder(root.left, lb, root) & preOrder(root.right, root, hb);
    }

    public boolean isValidBST(TreeNode root) {
        return preOrder(root, null, null);
    } 

    public static void main(String[] args){
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \  /
         *   4   7 13 
         */
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(13);

        Solution sol = new Solution();
        System.out.println("isValid: " + sol.isValidBST(root));
    }
}
