/* DFS: O(n)
 * 1. Have a class "ReturnValue" with 2 elements maxMoneyiWithRob and maxMoneyiWitouthRob
 * 2. In DFS, maxMoneyiWithRob = root.val + leftReturvValue.maxMoneyiWitouthRob + rightReturvValue.maxMoneyiWitouthRob
 * 3. maxMoneyiWitouthRob = leftReturvValue.maxMoneyiWithRob + rightReturvValue.maxMoneyiWithRob
 * 4. However, if maxMoneyiWitouthRob > maxMoneyiWithRob, let maxMoneyiWithRob = maxMoneyiWitouthRob
 * 5. Get the answer by return max(maxMoneyiWithRob, maxMoneyiWitouthRob)
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
    private class ReturvValue{
        int maxMoneyiWithRob;
        int maxMoneyiWitouthRob;
        ReturvValue(int with, int without){maxMoneyiWithRob = with; maxMoneyiWitouthRob = without;}
    }
    
    private ReturvValue dfs(TreeNode root){
        if(root == null){
            return new ReturvValue(0, 0);
        }
        
        ReturvValue leftReturvValue = dfs(root.left);
        ReturvValue rightReturvValue = dfs(root.right);
        
        int maxMoneyiWithRob = root.val + leftReturvValue.maxMoneyiWitouthRob + rightReturvValue.maxMoneyiWitouthRob;
        int maxMoneyiWitouthRob = leftReturvValue.maxMoneyiWithRob + rightReturvValue.maxMoneyiWithRob;
        maxMoneyiWithRob = Math.max(maxMoneyiWithRob, maxMoneyiWitouthRob);
        
        return new ReturvValue(maxMoneyiWithRob, maxMoneyiWitouthRob);
    }
    
    public int rob(TreeNode root) {
        ReturvValue returnValue = dfs(root);
        return Math.max(returnValue.maxMoneyiWithRob, returnValue.maxMoneyiWitouthRob);
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     3
         *    / \
         *   2   3
         *    \   \
         *     3   1
         */
        root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        sol = new Solution();
        
        System.out.println("maximum amount of robbed money: " + sol.rob(root));
    }
}
