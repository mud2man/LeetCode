/* DFS: Time:O(n^2), Space:O(n)
 * 1. Use dfs to construct a tree
 * 2. In dfs, if preStart > preEnd, retnrn null.
 * 3. If preStart == preEnd, return the single node
 * 4. Otherwise, find the length of left sub-tree, and append root.left/root.right by calling dfs with the left range nad right range
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
    private TreeNode dfs(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd){
        if(preStart > preEnd){
            return null;
        }
        else if(preStart == preEnd){
            return new TreeNode(pre[preStart]);
        }
        
        TreeNode root = new TreeNode(pre[preStart]);
        int leftLen = 0;
        for(int i = postStart; i <= postEnd; ++i){
            if(post[i] == pre[preStart + 1]){
                break;
            }
            else{
                leftLen++;
            }
        }
        root.left = dfs(pre, preStart + 1, preStart + leftLen + 1, post, postStart, postStart + leftLen);
        root.right = dfs(pre, preStart + leftLen + 2, preEnd, post, postStart + leftLen + 1, postEnd);
        return root;
    }
    
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return dfs(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }
 
    private void preorder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + "->");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] post = {4, 5, 2, 6, 7, 3, 1};
        System.out.println("pre: " + Arrays.toString(pre));
        System.out.println("post: " + Arrays.toString(post));
        TreeNode root = sol.constructFromPrePost(pre, post);
        sol.preorder(root);
        System.out.println(""); 
    }
}
