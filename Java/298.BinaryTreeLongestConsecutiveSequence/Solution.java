/* DFS: Time:O(n), Space:O(n)
 * 1. return the current consecutive number, either from left or right child
 * 2. Update longestConsecutiveLength
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
    private int dfs(TreeNode root, int[] longestConsecutiveLength){
        if(root == null){
            return 0;
        }    
        int leftLength = dfs(root.left, longestConsecutiveLength);
        int rightLength = dfs(root.right, longestConsecutiveLength);
        int currentLength = 1;
        leftLength = (root.left != null && root.left.val == root.val + 1)?  (1 + leftLength): 0;
        rightLength = (root.right != null && root.right.val == root.val + 1)?  (1 + rightLength): 0;
        currentLength = Math.max(currentLength, leftLength);
        currentLength = Math.max(currentLength, rightLength);
        longestConsecutiveLength[0] = Math.max(longestConsecutiveLength[0], currentLength);
        return currentLength;
    }
    
    public int longestConsecutive(TreeNode root) {
        int[] longestConsecutiveLength = new int[1];
        dfs(root, longestConsecutiveLength);
        return longestConsecutiveLength[0]; 
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \  /
         *   4   7 13 
         */
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        Solution sol = new Solution();
        System.out.println("longestConsecutiveLength: " + sol.longestConsecutive(root));
    }
}
