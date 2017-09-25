/* O(n^2), but leetcode has O(n) solution
 * 1. Traverse nums from left to right
 * 2. Insert the new node into the current tree
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
    private void insert(TreeNode root, TreeNode target){
        while(root.right != null && root.right.val > target.val){
            root = root.right;
        }
        
        if(root.right == null){
            root.right = target;
        }
        else{
            TreeNode tmp = root.right;
            root.right = target;
            target.left = tmp;
        }
    }
    
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        
        TreeNode root = new TreeNode(nums[0]);
        
        for(int i = 1; i < nums.length; ++i){
            int num = nums[i];
            if(num > root.val){
                TreeNode newRoot = new TreeNode(num);
                newRoot.left = root;
                root = newRoot;
            }
            else{
                insert(root, new TreeNode(num));
            }
        }
        
        return root;
    }

    private void preOrder(TreeNode root){
        if(root == null){
            return;
        }

        System.out.print(root.val + ", ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {3, 2, 1, 6, 0, 5};
         
        System.out.println("nums:" + Arrays.toString(nums));

        sol = new Solution();
        System.out.println("after construct:");
        TreeNode root = sol.constructMaximumBinaryTree(nums);
        sol.preOrder(root);
        System.out.println("");
    }
}
