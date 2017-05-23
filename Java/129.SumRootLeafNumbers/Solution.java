/* DFS: O(n)
 * 1. Keep record the sum of the path "pathSum[0] = pathSum[0] * 10 + root.val"
 * 2. If this node is a leaf, accumulate the total sum with the path sum
 * 3. Keep visit its children
 * 4. When return, restore the path sum by "pathSum[0] = pathSum[0] / 10"
 */

import java.util.*;

/* Definition for binary tree */
class TreeNode 
{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class Solution {
    public void dfs(TreeNode root, int[] totalSum, int[] pathSum){
        
        //push
        pathSum[0] = pathSum[0] * 10 + root.val;
        
        //leaf found, accumulate total sum
        if(root.left == null && root.right == null){
            totalSum[0] += pathSum[0];
        }
        
        if(root.left != null){
            dfs(root.left, totalSum, pathSum);
        }
        
        if(root.right != null){
            dfs(root.right, totalSum, pathSum);
        }
        
        //pop
        pathSum[0] = pathSum[0] / 10;
    }
    
    public int sumNumbers(TreeNode root) {
        int[] totalSum, pathSum;
        
        totalSum = new int[1];
        pathSum = new int[1];
        
        if(root == null){
            return 0;
        }
        
        dfs(root, totalSum, pathSum);
        
        return totalSum[0];
    }    
 
    public static void main(String[] args)
    {
        TreeNode root;
        Solution sol;
        int sum;

        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        sol = new Solution(); 
		
        sum = sol.sumNumbers(root);
        System.out.println("sum: " + sum);
	}
}
