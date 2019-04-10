/* DFS: Time:O(n), Space:O(h)
 * 1. Have "prev" to record the previous visited node
 * 2. Traverse tree inorderly, return root if prev[0] == p
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
    private TreeNode inorderSuccessor(TreeNode root, TreeNode p, TreeNode[] prev){
        if(root == null){
            return null;
        }
        
        TreeNode leftRet = inorderSuccessor(root.left, p, prev);
        if(leftRet != null){
            return leftRet;
        }
        else if(prev[0] == p){
            return root;
        }
        else{
            prev[0] = root;
            return inorderSuccessor(root.right, p, prev);
        }
    }
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode[] prev = {null};
        return inorderSuccessor(root, p, prev);
    }
 
    public static void main(String[] args){
        /* Generate a input tree
         *     4
         *    / \
         *   2   5
         *  / \ 
         * 1   3
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        TreeNode p = root.left.right;

        Solution sol = new Solution();
        System.out.println("p:" + p.val);
        System.out.println("The successor of p:" + sol.inorderSuccessor(root, p).val);
    }
}
