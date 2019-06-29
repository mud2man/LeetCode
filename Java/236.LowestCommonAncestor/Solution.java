/* Recursive: Time:O(n), Space:O(h), where h is the hight of the tree
 * 1. Search p or q, return non-null p/q if p or q hiited
 * 2. If left and right is non-null, means the current node is LCA, return the current root
 * 3. Else, return either p or q, which is non-null
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        else if(root == p || root == q){
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if(left != null && right != null){
            return root;
        }
        else if(left == null){
            return right;
        }
        else{
            return left;
        }
    }
 
    public static void main(String[] args) {
        TreeNode root;
        TreeNode lca;
        Solution sol;
        
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \ 
         *   4   7 
         */
        root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);

        sol = new Solution();
        lca = sol.lowestCommonAncestor(root, root.left.left, root.left.right.right);

        System.out.println(lca.val);
        
        sol = null;
    }
}
