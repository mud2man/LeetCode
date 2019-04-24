/* Recursive: Time:O(n^2), Space:O(h). We can use hashmap to store value-to-index like LeetCode#105 to reduce time complexity to O(n)
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
    public TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if(preStart > preEnd){
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int leftSize = 0;
        while(inorder[inStart + leftSize] != rootVal){
            leftSize++;
        }        
        root.left = helper(preorder, preStart + 1, preStart + leftSize, inorder, inStart, inStart + leftSize - 1);
        root.right = helper(preorder, preStart + leftSize + 1, preEnd, inorder, inStart + leftSize + 1, inEnd);
        return root;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = helper(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1);
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
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println("preorder:" + Arrays.toString(preorder));
        System.out.println("inorder:" + Arrays.toString(inorder));

        Solution sol = new Solution();
        System.out.println("after construct:");
        TreeNode root = sol.buildTree(preorder, inorder);
        sol.preOrder(root);
        System.out.println("");
    }
}
