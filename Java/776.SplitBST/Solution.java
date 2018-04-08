/* Tree: Time:O(h), Space:O(h)
 * 1. Have a array to record {small tree, attach point of small tree, big tree, attach point of big tree}
 * 2. Have a recursive helper to split and attach tree
 * 3. If root.val <= V, attach record[1].right with root, update record[1] with root, and invoke helper(right, V, record);
 * 4. Otherwise, repeat step3 but replace left with right 
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
    private void helper(TreeNode root, int V, TreeNode[] record){
        if(root == null){
            return;
        }
        
        if(root.val <= V){
            TreeNode right = root.right;
            root.right = null;
            if(record[0] == null){
                record[0] = root;
            }
            else{
                record[1].right = root;
            }
            record[1] = root;
            helper(right, V, record);
        }
        else{
            TreeNode left = root.left;
            root.left = null;
            if(record[2] == null){
                record[2] = root;
            }
            else{
                record[3].left = root;
            }
            record[3] = root;
            helper(left, V, record);
        }
    }
    
    public TreeNode[] splitBST(TreeNode root, int V) {
        TreeNode[] record = new TreeNode[4]; // small tree, attach point of small tree, big tree, attach point of big tree
        helper(root, V, record);
        TreeNode[] ret = {record[0], record[2]};
        return ret;
    }

    public void preorder(TreeNode root){
        if(root == null){
            return;
        }
        
        System.out.print(root.val + ", ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *      4
         *    /   \
         *   2     6
         *  / \   / \
         * 1   3 5   7
         */
        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        int v = 2;

        sol = new Solution();
        TreeNode[] trees = sol.splitBST(root, v);
        System.out.println("v: " + v);
        System.out.println("small tree: ");
        sol.preorder(trees[0]);
        System.out.println("\nbig tree: ");
        sol.preorder(trees[1]);
    }
}
