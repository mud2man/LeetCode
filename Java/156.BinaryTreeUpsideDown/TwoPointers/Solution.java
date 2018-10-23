/* Two Pointers: O(n)
 * 1. Let ptr0 = root, ptr1 = root.left
 * 2. Reset the children to null for the previous root
 * 3. Concatenate the children of ptr1 from ptr0 and ptr0Right
 * 4. Going down and update ptr0Right, ptr0Left, ptr1Right and ptr1Left
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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode ptr0 = root;
        TreeNode ptr1 = (root != null)? root.left: null;
        
        TreeNode ptr0Right = (ptr0 != null)? ptr0.right: null;
        TreeNode ptr0Left = (ptr0 != null)? ptr0.left: null;
        TreeNode ptr1Right = (ptr1 != null)? ptr1.right: null;
        TreeNode ptr1Left = (ptr1 != null)? ptr1.left: null;
        
        // Reset the children to null for the previous root
        if(ptr0 != null){
            ptr0.right = null;
            ptr0.left = null;
        }
        
        while(ptr1 != null){
            // Concatenate the children of ptr1 
            ptr1.right = ptr0;
            ptr1.left = ptr0Right;
            
            // Going down and update ptr0Right, ptr0Left, ptr1Right and ptr1Left
            ptr0Right = ptr1Right;
            ptr0Left = ptr1Left;
            ptr0 = ptr1;
            ptr1 = ptr1Left; 
            ptr1Right = (ptr1 != null)? ptr1.right: null;
            ptr1Left = (ptr1 != null)? ptr1.left: null;
            
        }
        
        return ptr0;
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
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         *  / \ 
         * 4   5
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        sol = new Solution();

        System.out.println("Before upside down:");
        sol.preOrder(root); 
        System.out.println("");
        TreeNode newRoot = sol.upsideDownBinaryTree(root);
        System.out.println("After upside down:");
        sol.preOrder(newRoot); 
        System.out.println("");
    }
}
