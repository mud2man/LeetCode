/* Recursive: Time:O(n), Space:O(n).
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
    public TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> num2IndexInorder){
        if(preStart > preEnd){
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int leftSize = num2IndexInorder.get(rootVal) - inStart;      
        root.left = helper(preorder, preStart + 1, preStart + leftSize, inorder, inStart, inStart + leftSize - 1, num2IndexInorder);
        root.right = helper(preorder, preStart + leftSize + 1, preEnd, inorder, inStart + leftSize + 1, inEnd,num2IndexInorder);
        return root;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> num2IndexInorder = new HashMap<>();
        for(int i = 0; i < inorder.length; ++i){
            num2IndexInorder.put(inorder[i], i);
        }
        TreeNode root = helper(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1, num2IndexInorder);
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
