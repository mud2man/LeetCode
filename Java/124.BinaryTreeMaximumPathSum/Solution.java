/* post order traverse: Time:O(n), Space:O(h)
 * 1. Traverse all nodes using in post order, and return the max sum starting from the current node
 * 2. Update the max sum in each "postOrder" call
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
    private int postOrder(TreeNode root, int[] max){
        if(root == null){
            return 0;
        }
        
        int leftMax = postOrder(root.left, max);
        int rightMax = postOrder(root.right, max);
        int localMax = root.val;
        
        localMax += (leftMax > 0)? leftMax: 0;
        localMax += (rightMax > 0)? rightMax: 0;
        max[0] = Math.max(max[0], localMax);
        
        int childMax = Math.max(leftMax, rightMax);
        localMax = (childMax > 0)? (root.val + childMax): root.val;
        
        return localMax; 
    }
    
    public int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        postOrder(root, max);
        return max[0];
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        sol = new Solution();
        System.out.println("maximum path sum: " + sol.maxPathSum(root));
    }
}
