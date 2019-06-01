/* Inorder traverse: O(n)
 * 1. Traverse all nodes with inorder, and keep a global count
 * 2. If count reach k, return
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
    private TreeNode inorder(TreeNode root, int[] count, int k){
        if(root == null){
            return null;
        }
        
        TreeNode left = inorder(root.left, count, k);
        count[0]++;
        return (left != null)? left: ((count[0] == k)? root: inorder(root.right, count, k));
    }
    
    public int kthSmallest(TreeNode root, int k) {
        int[] count = {0};
        TreeNode ret = inorder(root, count, k);
        return ret.val;
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
        int k = 3;
        System.out.println(k + "th smallest: " + sol.kthSmallest(root, k));
    }
}
