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
        TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.add(dummy);
        
        for(int num: nums){
            TreeNode currNode = new TreeNode(num);
            TreeNode top = null;
            while(stack.peekLast().val < currNode.val){
                top = stack.pollLast();
            }
            stack.peekLast().right = currNode;
            currNode.left = top;
            stack.add(currNode);
        }
        return dummy.right;
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
        int[] nums = {3, 2, 1, 6, 0, 5};
        System.out.println("nums:" + Arrays.toString(nums));
        Solution sol = new Solution();
        System.out.println("after construct:");
        TreeNode root = sol.constructMaximumBinaryTree(nums);
        sol.preOrder(root);
        System.out.println("");
    }
}
