/* Recursive bottom-up traversal: O(n)
 * 1. If left subtree and right subtree is BST, root.val > left.max and root.val < right.min 
 * 2. Then, the current subtree is BST
 * 3. Accumulate the number of nodes and return
 * 3. Else, return the number of nodes = 0
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
    private class ReturnValue{
        boolean isBst;
        int nodeNum;
        int lb;
        int hb;
        ReturnValue(boolean i, int n, int l, int h){isBst = i; nodeNum = n; lb = l; hb = h;}
    }
    
    public ReturnValue dfs(TreeNode root){
        if(root == null){
            return new ReturnValue(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        
        ReturnValue leftReturnValue = dfs(root.left);
        ReturnValue rightReturnValue = dfs(root.right);
        
        if((leftReturnValue.isBst == true) && (rightReturnValue.isBst == true) && (root.val > leftReturnValue.hb) && (root.val < rightReturnValue.lb)){
            int lb = Math.min(root.val, leftReturnValue.lb);
            int hb = Math.max(root.val, rightReturnValue.hb);
            return new ReturnValue(true, leftReturnValue.nodeNum + rightReturnValue.nodeNum + 1, lb, hb);
        }
        else{
            return new ReturnValue(false, Math.max(leftReturnValue.nodeNum, rightReturnValue.nodeNum), root.val, root.val);
        }
    }
    
    public int largestBSTSubtree(TreeNode root) {
        ReturnValue returnValue = dfs(root);
        return returnValue.nodeNum;
    }

    public static void main(String[] args){
        int size;
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     10
         *    / \
         *   5   15
         *  / \   \
         * 1   8   7
         */
        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(7);

        sol = new Solution();
        
        size = sol.largestBSTSubtree(root);
        
        System.out.println("The size of largest BST subtree: " + size);
    }
}
