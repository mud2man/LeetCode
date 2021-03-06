/* Recursive: Time:O(n), Space:O(h). LeetCode has iteration solution
 * 1. Vist the tree in preOrder and recursively
 */

import java.util.*;

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode preOrder(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode leftTail = preOrder(root.left);
        TreeNode rightTail = preOrder(root.right);
        if(leftTail != null){
            leftTail.right = right;
            root.right = left;
        }
        TreeNode tail = (rightTail != null)? rightTail: (leftTail != null)? leftTail: root;
        root.left = null;
        return tail;
    }
    
    public void flatten(TreeNode root) {
        preOrder(root);
    }
 
    public void dump(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + "->");
        dump(root.left);
        dump(root.right);
    }

    public static void main(String[] args){
        /* Generate a input tree
         *       5
         *      / \
         *     4   8
         *    /   / \
         *   11  13  4
         *  /  \    / \
         * 7    2  5   1
         */
        
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        Solution sol = new Solution();
        
        System.out.println("before flatten:");    
        sol.dump(root);
        System.out.println("");    
 
        sol.flatten(root);

        System.out.println("after flatten:");    
        sol.dump(root);
        System.out.println("");    
    }
}
