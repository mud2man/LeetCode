/* Tree: Time:O(n^2), Space:O(n)
 * 1. Create a tree from right end of nums
 * 2. Use "getLessEqual" to update count, and "insert" to add new node
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    private class TreeNode{
        int val;
        int count;
        TreeNode left;
        TreeNode right;
        TreeNode(int v, int c){val = v; count = c;}
    }
    
    private int getLessEqual(TreeNode root, int target){
        if(root == null){
            return 0;
        }
        else if(root.val <= target){
            return root.count + getLessEqual(root.right, target);
        }
        else{
            return getLessEqual(root.left, target);
        }
    }
    
    private void insert(TreeNode farther, TreeNode curr, int val){
        if(curr == null){
            if(farther.val > val){
                farther.left = new TreeNode(val, 1);
            }
            else{
                farther.right = new TreeNode(val, 1);
            }
        }
        else if(curr.val == val){
            curr.count++;
        }
        else if(curr.val > val){
            curr.count++;
            insert(curr, curr.left, val);
        }
        else{
            insert(curr, curr.right, val);
        }
    }
    
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int count = 0;
        TreeNode root = new TreeNode(nums[nums.length - 1], 1);
        for(int i = nums.length - 2; i >= 0; --i){
            int target = (nums[i] >= 0)? (nums[i] + 1) / 2 - 1: nums[i] / 2 - 1;
            count += getLessEqual(root, target);
            insert(null, root, nums[i]);
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 3, 2, 3, 1}; 
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("reverse pairs count: " + sol.reversePairs(nums));
    }
}
