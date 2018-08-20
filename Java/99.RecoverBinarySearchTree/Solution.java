/* InOrder: Time:O(n), Space:O(n)
 * 1. Traverse tree inorder, and record the maximum and minimum pair among decreasing pair (prev, curr)
 * 2. Swap the value between the pair
 *
 * ex: input 1, 5, 3, 4, 2, 6 => (min,max) will be selected from (5, 3), (4, 2) => (min, max) = (2, 5)
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
    private void inorder(TreeNode root, TreeNode[] prev, TreeNode[] pair){
        if(root == null){
            return;
        }
        
        inorder(root.left, prev, pair);
        if(prev[0].val > root.val){
            pair[0] = (root.val < pair[0].val)? root: pair[0];
            pair[1] = (prev[0].val > pair[1].val)? prev[0]: pair[1]; 
        }
        prev[0] = root;
        inorder(root.right, prev, pair);
    }
    
    public void recoverTree(TreeNode root) {
        TreeNode[] pair = {new TreeNode(Integer.MAX_VALUE), new TreeNode(Integer.MIN_VALUE)};
        TreeNode[] prev = {new TreeNode(Integer.MIN_VALUE)};
        inorder(root, prev, pair);
        int temp = pair[0].val;
        pair[0].val = pair[1].val;
        pair[1].val = temp;
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
        TreeNode root;
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     1
         *    / 
         *   3  
         *    \ 
         *     2
         */
        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        
        System.out.println("before swap: ");
        sol.dump(root);
        System.out.println("");

        sol.recoverTree(root);

        System.out.println("after swap: ");
        sol.dump(root);
        System.out.println("");
    }
}
