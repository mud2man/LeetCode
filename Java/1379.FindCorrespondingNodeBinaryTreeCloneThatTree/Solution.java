/* DFS: Time:O(n), Space:O(h).
 * 1. Use DFS to find the cloned node
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
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original == null){
            return null;
        }else if(original == target){
            return cloned;
        }else{
            TreeNode leftResult = getTargetCopy(original.left, cloned.left, target);
            return (leftResult != null)? leftResult: getTargetCopy(original.right, cloned.right, target);
        }
    }
 
    public static void main(String[] args){
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         */
        TreeNode original = new TreeNode(1);
        original.left = new TreeNode(2);
        original.right = new TreeNode(3);
        
        TreeNode cloned = new TreeNode(1);
        cloned.left = new TreeNode(2);
        cloned.right = new TreeNode(3);

        Solution sol = new Solution();
        TreeNode target = original.left;
        System.out.println("target: " + target.val);;
        System.out.println("cloned target: " + sol.getTargetCopy(original, cloned, target).val);
    }
}
