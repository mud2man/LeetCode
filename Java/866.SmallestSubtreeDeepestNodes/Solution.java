/* Post Order: Time:O(n), Space:O(n)
 * 1. Use post order to update returned node "ret", when both subtree contains the same depth, and the depth >= max
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
       private int postOrder(TreeNode root, TreeNode[] ret, int depth, int[] max){
        if(root == null){
            return depth;
        }
        
        int leftDepth = postOrder(root.left, ret, depth + 1, max);
        int rightDepth = postOrder(root.right, ret, depth + 1, max);
        
        if(leftDepth == rightDepth){
            if(max[0] <= leftDepth){
                ret[0] = root;
                max[0] = leftDepth;
            }
        }
        return Math.max(leftDepth, rightDepth);
    }
    
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        TreeNode[] ret = new TreeNode[1];
        int[] max = new int[1];
        postOrder(root, ret, 0, max);
        return ret[0];
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     3
         *    / \
         *   5   1
         *  / \   \
         * 6   2   8
         *    / \ 
         *   7   43 
         */
        root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(43);

        sol = new Solution();
        System.out.println("deepest subtree: " + sol.subtreeWithAllDeepest(root).val);
    }
}
