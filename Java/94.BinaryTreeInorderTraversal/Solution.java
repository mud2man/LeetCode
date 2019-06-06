/* Stack: Time:O(1), Space:O(n)
 * 1. Use stack to store visited nodes, and put the value in "inorder" list and do pushLeft
 */

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution{
    public void pushLeft(TreeNode root, Stack<TreeNode> stack){
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<Integer>(); 
        Stack<TreeNode> stack = new Stack<TreeNode> ();
        TreeNode right = root;
        
        pushLeft(root, stack);
        while(!stack.empty()){
            TreeNode top = stack.pop();
            inorder.add(top.val);
            pushLeft(top.right, stack);
        }
        return inorder;
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     4
         *    / \
         *   2   5
         *  / \   
         * 1   3  
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        Solution sol = new Solution();
        System.out.println("inorder: " + sol.inorderTraversal(root));
    }
}
