/* DFS: Time:O(n), Space:O(h).
 * 1. The return value is {moves need to make to distributes coins on the subtree, remains coins need to give to farther}
 * 2. When remains < 0, which means the subtree needs more coins
 * 3. moves = |(left remains)| + |(right remains)| + (left moves) + (right moves)
 * 4. remians = (left remains) + (right remains) + root.val - 1;
 * 5. Traverse the tree in post order, and return the moves on root
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
    private int[] postOrderDfs(TreeNode root){
        if(root == null){
            return new int[2];
        }
        int[] leftRet = postOrderDfs(root.left);
        int[] rightRet = postOrderDfs(root.right);
        int moves = Math.abs(leftRet[1]) + Math.abs(rightRet[1]) + leftRet[0] + rightRet[0];
        int remains = leftRet[1] + rightRet[1] + root.val - 1;
        return new int[]{moves, remains};
    }
    
    public int distributeCoins(TreeNode root) {
        int[] ret = postOrderDfs(root);
        return ret[0];
    }
 
    public static void main(String[] args){
        /* Generate a input tree
         *     0
         *    / \
         *   3   0
         */
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(3);
        root.right = new TreeNode(0);
        Solution sol = new Solution();
        System.out.println("moves:" + sol.distributeCoins(root));
    }
}
