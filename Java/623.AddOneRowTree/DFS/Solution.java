/* DFS: Time:O(n), Space:O(h)
 * 1. Use DFS to traverse tree, and find the insertion level, then insert the new nodes
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
    private void dfs(TreeNode root, int currDepth, int v, int d){
        if(root == null){
            return;
        }
        
        if(currDepth == d){
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = new TreeNode(v);
            root.left.left = left;
            root.right = new TreeNode(v);
            root.right.right = right;
            return;
        }
        else{
            dfs(root.left, currDepth + 1, v, d);
            dfs(root.right, currDepth + 1, v, d);
        }
    }
    
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d == 1){
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        dfs(root, 0, v, d - 2);
        return root;
    }

    private void traverse(TreeNode head){
        if(head == null){
            return;
        }
        System.out.print(head.val + "->");
        traverse(head.left);
        traverse(head.right);
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        int v = 1;
        int d = 2;
        
        /* Generate a input tree
         *     4
         *    / \
         *   2   6
         *  / \   
         * 3   1  
         */
        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        System.out.println("v: " + v);
        System.out.println("d: " + d);
        System.out.println("before insert: ");
        sol.traverse(root);
        System.out.println("");
        System.out.println("after insert: ");
        sol.traverse(sol.addOneRow(root, v, d));
        System.out.println("");
    }
}
