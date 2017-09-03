/* Inorder traverse: O(n)
 * 1. Traverse all nodes with inorder, and store them into a list "sequence"
 * 2. Return sequence.get(k - 1)
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
    private void inorderTraverse(List<Integer> sequence, TreeNode root){
        if(root == null){
            return;
        }
        inorderTraverse(sequence, root.left);
        sequence.add(root.val);
        inorderTraverse(sequence, root.right);
    }
    
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> sequence = new ArrayList<Integer>();
        inorderTraverse(sequence, root);
        return sequence.get(k - 1);   
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