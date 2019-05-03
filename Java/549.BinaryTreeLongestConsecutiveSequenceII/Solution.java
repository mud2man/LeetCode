/* Post order traveral: Time:O(n), Space:O(n)
 * 1. postOrder retunr an array lengths[]
 * 2. lengths[0] = max decending length starting from root
 * 3. lengths[1] = max ascending length starting from root
 * 4. Also considering child-Parent-child order path
 * 5. Get the maximum among lengths[0], lengths[1], (leftLengths[1] + rightLengths[0] + 1),and (leftLengths[0] + rightLengths[1] + 1) 
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
   private int[] postOrder(int[] maxLength, TreeNode root){
        //lengths[0] = max decending length starting from root
        //lengths[1] = max ascending length starting from root 
        int[] lengths = new int[2]; 
        if(root == null){
            return lengths;
        }

        int[] leftLength = postOrder(maxLength, root.left);
        leftLength[0] = (root.left != null && root.val == root.left.val + 1)? leftLength[0]: 0;
        leftLength[1] = (root.left != null && root.val == root.left.val - 1)? leftLength[1]: 0;
        int[] rightLength = postOrder(maxLength, root.right);
        rightLength[0] = (root.right != null && root.val == root.right.val + 1)? rightLength[0]: 0;
        rightLength[1] = (root.right != null && root.val == root.right.val - 1)? rightLength[1]: 0;
        lengths[0] = Math.max(leftLength[0] + 1, rightLength[0] + 1);
        lengths[1] = Math.max(leftLength[1] + 1, rightLength[1] + 1);
        maxLength[0] = Math.max(maxLength[0], (leftLength[0] + rightLength[1] + 1));
        maxLength[0] = Math.max(maxLength[0], (rightLength[0] + leftLength[1] + 1));
        return lengths;
    }
    
    public int longestConsecutive(TreeNode root) {
        int[] maxLength = new int[1];
        postOrder(maxLength, root);
        return maxLength[0];
    }
 
    public static void main(String[] args){
        /* Generate a input tree
         *     2
         *    / \
         *   1   3
         */
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        Solution sol = new Solution();
        System.out.println("maximum consecutive length: " + sol.longestConsecutive(root));
    }
}
