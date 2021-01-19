/* DFS: Time:O(n), Space:O(h)
 * 1. Visit nodes with "dfs" where return count-sum pair and update maxAvg
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
    private int[] dfs(double[] maxAvg, TreeNode node){
        if(node == null){
            return new int[]{0, 0};
        }
        int[] countAndSum = new int[]{1, node.val};
        int[] leftResult = dfs(maxAvg, node.left);
        countAndSum[0] += leftResult[0];
        countAndSum[1] += leftResult[1];
        int[] rightResult = dfs(maxAvg, node.right);
        countAndSum[0] += rightResult[0];
        countAndSum[1] += rightResult[1];
        maxAvg[0] = Math.max(maxAvg[0], (double)countAndSum[1] / (double)countAndSum[0]);
        return countAndSum;
    }
    
    public double maximumAverageSubtree(TreeNode root) {
        double[] maxAvg = new double[1];
        dfs(maxAvg, root);
        return maxAvg[0];
    }
 
    public static void main(String[] args){
        /* Generate a input tree
         *     5
         *    / \
         *   6   1
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(6);
        root.right = new TreeNode(1);
        Solution sol = new Solution();
        System.out.println("max average:" + sol.maximumAverageSubtree(root));
    }
}
