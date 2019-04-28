/* Iteration: Time:O(n^2), Space:O(1).
 * 1. Traverse tree by taking right subtree as next step
 * 2. Attach left subtree to the rightest leave of right subtree
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
    public void flatten(TreeNode root) {
        TreeNode itr = root;
        while(itr != null){
            if(itr.left != null){
                TreeNode right = itr.right;
                TreeNode inerItr = itr.left;
                while(inerItr.right != null){
                    inerItr = inerItr.right;
                }
                inerItr.right = right;
                itr.right = itr.left;
                itr.left = null;
            }
            itr = itr.right;
        }
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
