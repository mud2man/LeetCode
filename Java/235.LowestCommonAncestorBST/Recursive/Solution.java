/* DFS: Time:O(depth), SpaceO(depth)
 * 1. Traverse the BST as long as smallValue and bigValue are in the same subtree
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int val = root.val;
        
        if(val <= Math.max(p.val, q.val) && val >= Math.min(p.val, q.val)){
            return root;
        }
        else if(val < Math.min(p.val, q.val)){
            return lowestCommonAncestor(root.right, p, q);
        }
        else{
            return lowestCommonAncestor(root.left, p, q);
        }
    }

    public static void main(String[] args){
        List<List<Integer>> list;
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     3
         *    / \
         *   1   20
         *       / \
         *      15  70
         */
        root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(70);

        sol = new Solution();   
        TreeNode p = root.right.left;
        TreeNode q = root.right.right;

        System.out.println("p: " + p.val);
        System.out.println("q: " + q.val);
        System.out.println("LCS: " + sol.lowestCommonAncestor(root, p, q).val);
    }
}
