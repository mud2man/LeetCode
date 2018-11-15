/* Use preorder: TimeO(n), SpaceO(n)
 * 1. Serialize with preorder traversal, and denote Null pointer with string "#"
 * 2. Deserize by taking the first element of datas as node's value
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
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null){
            return 0;
        }
        
        int sum = (root.val < L || root.val > R)? 0: root.val;
        if(root.val < L){
            sum += rangeSumBST(root.right, L, R);
        }
        else if(root.val > R){
            sum += rangeSumBST(root.left, L, R);
        }
        else{
            sum += rangeSumBST(root.left, L, R);
            sum += rangeSumBST(root.right, L, R);
        }
        return sum;
    }

    public static void main(String[] args){
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \  /
         *   4   7 13 
         */
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(13);

        Solution sol = new Solution();
        int L = 13;
        int R = 15;
        System.out.println("L:" + L + ", R:" + R);
        System.out.println("range sum:" + sol.rangeSumBST(root, L, R));
    }
}
