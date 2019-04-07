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
    private TreeNode dfs(int[] preorder, int[] idx, TreeNode lb, TreeNode ub){
        if(idx[0] >= preorder.length || (lb != null && lb.val > preorder[idx[0]]) || (ub != null && ub.val < preorder[idx[0]])){
            return null;
        }
        int val = preorder[idx[0]++];
        TreeNode root = new TreeNode(val);
        root.left = dfs(preorder, idx, lb, root);
        root.right = dfs(preorder, idx, root, ub);
        return root;
    }
    
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] idx = {0};
        return dfs(preorder, idx, null, null);
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
        int[] preorder = {8, 5, 1, 7, 10, 12};
        Solution sol = new Solution();
        System.out.println("preorder: " + Arrays.toString(preorder));
        sol.dump(sol.bstFromPreorder(preorder));
        System.out.println("");
    }
}
