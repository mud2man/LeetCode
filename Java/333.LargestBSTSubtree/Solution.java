/* Recursive bottom-up traversal: O(n)
 * 1. If left subtree and right subtree is BST, root.val > left.max and root.val < right.min 
 * 2. Then, the current subtree is BST
 * 3. Accumulate the number of nodes and return
 * 3. Else, return the number of nodes = 0
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
    private int max;
    
    private class Ret{
        boolean isBst;
        int nodeNum;
        int min;
        int max;
    }
    
    public Ret helper(TreeNode root){
        Ret rootRet;
        Ret leftRet;
        Ret rightRet;
        
        rootRet = new Ret();
        
        if(root == null){
            rootRet.isBst = true;
            rootRet.nodeNum = 0;
            rootRet.min = Integer.MAX_VALUE;
            rootRet.max = Integer.MIN_VALUE;
            return rootRet;
        }
        
        leftRet = helper(root.left);
        rightRet = helper(root.right);
        
        rootRet.isBst = false;
        rootRet.nodeNum = 0;
        rootRet.min = Integer.MAX_VALUE;
        rootRet.max = Integer.MIN_VALUE;
        
        if((leftRet.isBst == true) && (rightRet.isBst == true) && (root.val > leftRet.max) && (root.val < rightRet.min)){
            rootRet.nodeNum = leftRet.nodeNum + rightRet.nodeNum + 1;
            rootRet.isBst = true;
            rootRet.min = Math.min(root.val, leftRet.min);
            rootRet.max = Math.max(root.val, rightRet.max);
        }
        
        this.max = Math.max(this.max, rootRet.nodeNum);
        
        return rootRet;
    }
    
    public int largestBSTSubtree(TreeNode root) {
        
        this.max = 0;
        helper(root);
        
        return this.max;
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
