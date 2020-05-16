/* DFS: Time:O(n), Space:O(n)
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
    private int dfs(TreeNode maxAncesstor, TreeNode root){
        if(root == null){
            return 0;
        }
        int count = 0;
        if(maxAncesstor == null || root.val >= maxAncesstor.val){
            count++;
            maxAncesstor = root;
        }
        return count + dfs(maxAncesstor, root.left) + dfs(maxAncesstor, root.right);
    }
    
    public int goodNodes(TreeNode root) {
        return dfs(null, root);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println("Good nodes#:" + sol.goodNodes(root));
    }
}
