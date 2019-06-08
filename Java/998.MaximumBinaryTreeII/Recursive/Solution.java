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
    private void insert(TreeNode prev, TreeNode curr, int val){
        if(curr == null){
            prev.right = new TreeNode(val);
        }else if(val > curr.val){
            TreeNode newNode = new TreeNode(val);
            prev.right = newNode;
            newNode.left = curr;
        }else{
            insert(curr, curr.right, val);
        }
    }
    
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
        dummy.right = root;
        insert(dummy, root, val);
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
        Solution sol = new Solution();
        TreeNode root = new TreeNode(4);
        root.left =  new TreeNode(1);
        root.right =  new TreeNode(3);
        root.right.left =  new TreeNode(2);
        int val = 5;

        System.out.println("val: " + val);
        System.out.println("before insert: ");
        sol.preOrder(root);
        System.out.println("");
        System.out.println("after insert: ");
        sol.preOrder(sol.insertIntoMaxTree(root, val));
        System.out.println("");
    }
}
