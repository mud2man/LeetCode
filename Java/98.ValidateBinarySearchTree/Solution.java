/* Use DFS: O(n)
 * 1. Traverse all nodes using DFS with lower and upper bound
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode 
{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class Solution {
    public boolean dfs(TreeNode root, long lb, long ub){
        boolean isValid;
        
        if(root == null){
            return true;
        }
        
        isValid = true;
        
        if(root.left != null){
            if(((long)root.left.val >= (long)root.val) || ((long)root.left.val <= lb)){
                return false;
            }
            else{
                isValid = dfs(root.left, lb, (long)root.val);
            }
        }
        
        if(isValid == false){
            return false;
        }
        
        if(root.right != null){
            if(((long)root.right.val <= (long)root.val) || ((long)root.right.val >= ub)){
                return false;
            }
            else{
                isValid = dfs(root.right, (long)root.val, ub);
            }
        }
        return isValid;
    }
    
    public boolean isValidBST(TreeNode root) {
        
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public static void main(String[] args)
    {
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
		root = new TreeNode(8);
		root.left = new TreeNode(3);
		root.right = new TreeNode(10);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(6);
		root.right.right = new TreeNode(14);
		root.left.right.left = new TreeNode(4);
		root.left.right.right = new TreeNode(7);
		root.right.right.left = new TreeNode(13);

		sol = new Solution();
        
        System.out.println("isValid: " + sol.isValidBST(root));
	}
}
