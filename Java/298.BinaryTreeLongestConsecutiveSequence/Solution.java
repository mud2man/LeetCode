/* DFS: O(n)
 * 1. Keep the current consecutive number
 * 2. Visit left and right child, if the value of child = the value of parent + 1.
 * 3. Pass the parameter with current length + 1. Otherwise, pass with 1
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
    private void dfs(TreeNode root, int currLen, int[] longestConsecutiveLength){
        if(root == null){
            return;
        }    
        
        longestConsecutiveLength[0] = Math.max(currLen, longestConsecutiveLength[0]);
        
        if(root.left != null && root.left.val == (root.val + 1)){
            dfs(root.left, currLen + 1, longestConsecutiveLength);
        }
        else{
            dfs(root.left, 1, longestConsecutiveLength);
        }
        
        if(root.right != null && root.right.val == (root.val + 1)){
            dfs(root.right, currLen + 1, longestConsecutiveLength);
        }
        else{
            dfs(root.right, 1, longestConsecutiveLength);
        }
    }
    
    public int longestConsecutive(TreeNode root) {
        int[] longestConsecutiveLength = new int[1];
        longestConsecutiveLength[0] = 0;
        dfs(root, 1, longestConsecutiveLength);
        return longestConsecutiveLength[0]; 
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \  /
         *   4   7 13 
         */
        root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);

        sol = new Solution();
        
        System.out.println("longestConsecutiveLength: " + sol.longestConsecutive(root));
    }
}
