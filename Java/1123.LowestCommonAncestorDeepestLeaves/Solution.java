/* DFS: Time:O(n), Space:O(h)
 * 1. Have a global index "index" to record the current index of "preorder"
 * 2. Have lower bound "lb" and upper bound "hb" to filter out unreasonable value. Only construct new node, if the preorder[idx[0]] in range
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
    private class ReturnValue {
        int depth;
        TreeNode root;
        ReturnValue(int d, TreeNode r){depth = d; root = r;}
    }
    
    private ReturnValue dfs(TreeNode root){
        if(root == null) {
            return new ReturnValue(0, null);
        }else{
            ReturnValue leftReturnValue = dfs(root.left);
            ReturnValue rightReturnValue = dfs(root.right);
            if(leftReturnValue.depth > rightReturnValue.depth){
                return new ReturnValue(leftReturnValue.depth + 1, leftReturnValue.root);
            }else if(leftReturnValue.depth < rightReturnValue.depth){
                return new ReturnValue(rightReturnValue.depth + 1, rightReturnValue.root);
            }else{
                return new ReturnValue(leftReturnValue.depth + 1, root);
            }
        }
    }
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        ReturnValue returnValue = dfs(root);
        return returnValue.root;
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
         *     3
         *    / \
         *   9   20
         *       / \
         *      15  7
         */
        Solution sol = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("tree: ");
        sol.dump(root);
        System.out.println("");
        System.out.println("LCA: ");
        sol.dump(sol.lcaDeepestLeaves(root));
        System.out.println("");
    }
}
