/* Dfs: Time:O(n), Space:O(h). LeetCode has greedy solution
 * 1. The ret = {coveredWithCamera, coveredWithoutCamera, coveredLeftTree, coveredRightTree}
 * 2. Use dfs to caculate the current w.r.t if the node has left/right child
 * 3. The answer is Math.min(ret[0], ret[1]), where ret = dfs(root)
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
    private int[] dfs(TreeNode node){
        if(node.left == null && node.right == null){
            return new int[]{1, 1, 0, 0};
        }else if(node.left == null){
            int[] rightRet = dfs(node.right);
            int coveredWithCamera = Math.min(1 + rightRet[0], 1 + rightRet[1]);
            coveredWithCamera = Math.min(coveredWithCamera, 1 + rightRet[2] + rightRet[3]);
            int coveredWithoutCamera = rightRet[0];
            return new int[]{coveredWithCamera, coveredWithoutCamera, 0, Math.min(rightRet[0], rightRet[1])};
        }else if(node.right == null){
            int[] leftRet = dfs(node.left);
            int coveredWithCamera = Math.min(1 + leftRet[0], 1 + leftRet[1]);
            coveredWithCamera = Math.min(coveredWithCamera, 1 + leftRet[2] + leftRet[3]);
            int coveredWithoutCamera = leftRet[0];
            return new int[]{coveredWithCamera, coveredWithoutCamera, 0, Math.min(leftRet[0], leftRet[1])};
        }else{
            int[] leftRet = dfs(node.left);
            int minLeft = Math.min(leftRet[0], leftRet[1]);
            minLeft = Math.min(minLeft, leftRet[2] + leftRet[3]);
            int[] rightRet = dfs(node.right);
            int minRight = Math.min(rightRet[0], rightRet[1]);
            minRight = Math.min(minRight, rightRet[2] + rightRet[3]);
            int coveredWithCamera = 1 + minLeft + minRight;
            int coveredWithoutCamera = Math.min(leftRet[0] + rightRet[1], leftRet[1] + rightRet[0]);
            coveredWithoutCamera = Math.min(coveredWithoutCamera, leftRet[0] + rightRet[0]);
            int coveredLeftTree = Math.min(leftRet[0], leftRet[1]);
            int coveredRightTree = Math.min(rightRet[0], rightRet[1]);
            return new int[]{coveredWithCamera, coveredWithoutCamera, coveredLeftTree, coveredRightTree};
        }
    }
    
    public int minCameraCover(TreeNode root) {
        int[] ret = dfs(root);
        return Math.min(ret[0], ret[1]);
    }

    public static void main(String[] args){
        /* Generate a input tree
         *     4
         *    /
         *   2  
         *  / \   
         * 1   3  
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        Solution sol = new Solution();
        System.out.println("minimum camera#: " + sol.minCameraCover(root));
    }
}
