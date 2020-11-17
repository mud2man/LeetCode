/* DFS: Time:O(n), Space:O(h)
 * 1. Use "getMatchCount" to check if p, q are existent in the tree
 * 2. If yes, call "getLCA" to get LCA
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
   private int getMatchCount(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return 0;
        }
        int count = 0;
        if(root == p || root == q){
            count++;
        }
        count += getMatchCount(root.left, p, q);
        count += getMatchCount(root.right, p, q);
        return count;
    }
    
    private TreeNode getLCA(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }else if(root == p || root == q){
            return root;
        }else{
            TreeNode left = getLCA(root.left, p, q);
            TreeNode right = getLCA(root.right, p, q);
            return (left != null && right != null)? root: (left != null)? left: right;
        }
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return (getMatchCount(root, p, q) == 2)? getLCA(root, p, q): null;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     5
         *    / \
         *   2   -5
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-5);

        TreeNode p = root.left;
        TreeNode q = root.right;
        System.out.println("LCA of " + p.val + " and " + q.val + " is:" + sol.lowestCommonAncestor(root, p, q).val );
    }
}
