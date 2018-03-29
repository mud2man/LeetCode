/* PostOrder: Time:O(n) Space:O(h), where h is the height of tree. LeetCode has a hashmap solution can traverse tree once
 * 1. Use postOrder to get the sum of the tree
 * 2. Use postOrderCheck to check if any subtree has the sum totalSum / 2
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
    private int postOrder(TreeNode root){
        return (root == null)? 0: (postOrder(root.left) + postOrder(root.right) + root.val);
    }
    
    private int postOrderCheck(TreeNode root, int target, boolean[] ret){
        if(root == null || ret[0] == true){
            return 0;
        }
        
        int leftSum = postOrderCheck(root.left, target, ret);
        int rightSum = postOrderCheck(root.right, target, ret);
        int sum = leftSum + rightSum + root.val;
        if(sum == target){
            ret[0] = true;
        }
        return sum;
    }
    
    public boolean checkEqualTree(TreeNode root) {
        int totalSum = postOrder(root);
        if(totalSum % 2 != 0){
            return false;
        }
        else{
            boolean[] ret = new boolean[1];
            postOrderCheck(root.left, totalSum / 2, ret);
            postOrderCheck(root.right, totalSum / 2, ret);
            return ret[0];
        }
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     5
         *    / \
         *   10 10
         *      / \
         *     2   3
         */
        root = new TreeNode(5);
        root.left = new TreeNode(10);
        root.right = new TreeNode(10);
        root.right.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        sol = new Solution();
        System.out.println("isEqual: " + sol.checkEqualTree(root));
    }
}
