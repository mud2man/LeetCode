/* Recursive: O(lgn*lgn)
 * 1. If left height == right height, return (1 << leftHeight) - 1
 * 2. Otherwise, return 1 + countNodes(root.left) + countNodes(root.right)
 * 3. One of root.left root.right must be complete tree. Therefore, the complexity is O(lgn*lgn)
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
    private int getLeftHeight(TreeNode root){
        return (root == null)? 0: 1 + getLeftHeight(root.left);
    }
    
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        int count = 0;
        TreeNode node = root;
        while(node != null && node.left != null){
            int leftHeight = getLeftHeight(node.left);
            int rightHeight = getLeftHeight(node.right);
            if(leftHeight == rightHeight){
                count += (1 << leftHeight);
                node = node.right;
            }
            else{
                count += (1 << rightHeight);
                node = node.left;
            }
        }
        return count + 1;
    }
 
    public static void main(String[] args){
        TreeNode root;
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   
         * 1   6
         */
        root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        Solution sol = new Solution();
        System.out.println("node#: " + sol.countNodes(root));
    }
}
