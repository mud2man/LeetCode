/* Recursive: O(ln2*lgn)
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
     private int getHeight(TreeNode root, int h, boolean isLeft){
        if(root == null){
            return h;
        }
        else{
            TreeNode nextNode = (isLeft)? root.left: root.right;
            return getHeight(nextNode, h + 1, isLeft);
        }
    }
    
    public int countNodes(TreeNode root) {
        int leftHeight = getHeight(root, 0, true);
        int rightHeight = getHeight(root, 0, false);
        
        if(leftHeight < 0){
            return 0;
        }
        
        if(leftHeight == rightHeight){
            return (1 << leftHeight) - 1;
        }
        else{
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
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
        sol = new Solution();
        System.out.println("node#: " + sol.countNodes(root));
    }
}
