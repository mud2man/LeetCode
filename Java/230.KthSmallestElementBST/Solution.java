/* Inorder traverse: O(n)
 * 1. Traverse all nodes with inorder, and keep a global count
 * 2. If count reach k, return
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode 
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private int inorderTraverse(TreeNode root, int[] count, int k){
        if(root == null){
            return 0;
        }
        
        int left = inorderTraverse(root.left, count, k);
        if(count[0] == k){
            return left;
        }
        
        if(k == ++count[0]){
            return root.val;
        }
        
        return inorderTraverse(root.right, count, k);
    }
    
    public int kthSmallest(TreeNode root, int k) {
        int[] count = {0};
        return inorderTraverse(root, count, k); 
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        int k = 3;
        
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
        System.out.println(k + "th smallest: " + sol.kthSmallest(root, k));
    }
}
