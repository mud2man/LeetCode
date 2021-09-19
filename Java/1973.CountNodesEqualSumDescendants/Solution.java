/* DFS: Time:O(n), Space:O(n)
 * 1. Use DFS based helper to return SumAndCount
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
    private int[] helper(TreeNode root){
        if(root == null){
            return new int[2];
        }
        int[] leftSumAndCount = helper(root.left);
        int[] rightSumAndCount = helper(root.right);
        int[] currSumAndCount = new int[2];
        currSumAndCount[0] += leftSumAndCount[0];
        currSumAndCount[0] += rightSumAndCount[0];
        currSumAndCount[1] += leftSumAndCount[1];
        currSumAndCount[1] += rightSumAndCount[1];
        currSumAndCount[1] +=(root.val == currSumAndCount[0])? 1: 0;
        currSumAndCount[0] += root.val;
        return currSumAndCount;
        
    }
    
    public int equalToDescendants(TreeNode root) {
        int[] sumAndCount = helper(root);
        return sumAndCount[1];
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     10
         *    / \
         *   3   4
         *  / \   
         * 2   1  
         */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        Solution sol = new Solution();
        System.out.println("count:" + sol.equalToDescendants(root));
    }
}
