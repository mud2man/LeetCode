/* Stack: Time:O(n), Space:O(n)
 * 1. Have a stack to record the right most children path, because the next tree node will only be added among them
 * 2. Pop the stack if the value of top is less than current value
 * 3. If the stack is not empty stack.peek().right = currNode
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        for(int i = 0; i < nums.length; ++i){
            TreeNode currNode = new TreeNode(nums[i]);
            TreeNode top = null;
            while(!stack.empty() && stack.peek().val < currNode.val){
                top = stack.pop();
            }
            
            if(!stack.empty()){
                stack.peek().right = currNode;
            }
            
            currNode.left = top;
            stack.push(currNode);
        }
        
        TreeNode top = null;
        while(!stack.empty()){
            top = stack.pop();
        }
        return top;
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
