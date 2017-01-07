/* Use post order traveral: O(n)
 * 1. Traverse post order
 * 2. Update current height = max(height of left subtree, height of right subtree) + 1
 * 3. If there is no the group of leaves of height = current hight, add a new one
 * 4. If yes, add the leave to the corresponding group
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
    public int postOrderTrav(List<List<Integer>> leavesGroup, TreeNode root){
        int leftHeight;
        int rightHeight;
        int rootHeight;
        List<Integer> leaves;
        
        if(root == null){
            return -1;
        }
        
        leftHeight = postOrderTrav(leavesGroup, root.left);
        rightHeight = postOrderTrav(leavesGroup, root.right);
        rootHeight = Math.max(leftHeight, rightHeight) + 1;
        
        if(rootHeight == leavesGroup.size()){
            leaves = new ArrayList<Integer>();
            leaves.add(root.val);
            leavesGroup.add(leaves);
        }
        else{
            leaves = leavesGroup.get(rootHeight);
            leaves.add(root.val);
        }
        
        return rootHeight;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> leavesGroup;
        
        leavesGroup = new ArrayList<List<Integer>>();
        
        postOrderTrav(leavesGroup, root);
        
        return leavesGroup;
    }

    public static void main(String[] args){
		List<List<Integer>> leavesGroup;
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
		
		leavesGroup = sol.findLeaves(root);
    	
		System.out.println("The group of leaves: ");
		for(List<Integer> leaves: leavesGroup){
			System.out.println(leaves);
		}
	}
}
