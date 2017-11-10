/* DFS: Time:O(depth), SpaceO(1)
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
        int smallValue = Math.min(p.val, q.val);
        int bigValue = Math.max(p.val, q.val);
        TreeNode node = root;
        
        while(smallValue != node.val && bigValue != node.val){
            int valueNode = node.val;
            if(bigValue > valueNode && smallValue < valueNode){
                return node;
            }
            else if(bigValue < node.val){
                node = node.left;
            }
            else if(smallValue > node.val){
                node = node.right;
            }
            else{
                continue;
            }
        }
        return node;
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
