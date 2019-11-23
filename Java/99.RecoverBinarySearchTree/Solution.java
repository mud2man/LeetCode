/* InOrder: Time:O(n), Space:O(n)
 * 1. Traverse tree inorder, and record the invalid pair
 * 2. If we found two pairs, we swap pair0[0] and pair1[1]
 * 3. Otherwise, we swap pair0[0] and pair0[1], since we can guarantee the sucessor is the one need to be swapped
 *
 * ex: input 1, 5, 3, 4, 2, 6 => pair0 = {5, 3}, pair1 = {4, 2}, we swap 5 and 2 to recover the tree
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
    private void inorder(TreeNode root, TreeNode[] prev, TreeNode[] pair0, TreeNode[] pair1){
        if(root == null){
            return;
        }
        inorder(root.left, prev, pair0, pair1);
        if(prev[0] != null && prev[0].val > root.val){
            if(pair0[0] == null){
                pair0[0] = prev[0];
                pair0[1] = root;
            }else{
                pair1[0] = prev[0];
                pair1[1] = root;
            }
        }
        prev[0] = root;
        inorder(root.right, prev, pair0, pair1);
    }
    
    public void recoverTree(TreeNode root) {
        TreeNode[] pair0 = new TreeNode[2];
        TreeNode[] pair1 = new TreeNode[2];
        TreeNode[] prev = new TreeNode[1];
        inorder(root, prev, pair0, pair1);
        
        if(pair1[0] == null){
            int temp = pair0[0].val;
            pair0[0].val = pair0[1].val;
            pair0[1].val = temp;
        }else{
            int temp = pair0[0].val;
            pair0[0].val = pair1[1].val;
            pair1[1].val = temp;
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
         *     1
         *    / 
         *   3  
         *    \ 
         *     2
         */
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
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
