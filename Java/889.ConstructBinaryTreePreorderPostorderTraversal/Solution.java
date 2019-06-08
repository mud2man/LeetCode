/* DFS: Time:O(n), Space:O(n)
 * 1. Use dfs to construct a tree
 * 2. In dfs, if preStart > preEnd, retnrn null.
 * 3. If preStart == preEnd, return the single node
 * 4. Otherwise, find the length of left sub-tree, and append root.left/root.right by calling dfs with the left range nad right range
 * 5. Step4 works only all number are distinct
 *
 * ex: pre={1, 2, 4, 5, 3, 6, 7}, post={4, 5, 2, 6, 7, 3, 1}
 *             <  L  >  <  R  >         <  L  >  <  R  > 
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
    private TreeNode dfs(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd, Map<Integer, Integer> num2Index){
        if(preStart > preEnd){
            return null;
        }else if(preStart == preEnd){
            return new TreeNode(pre[preStart]);
        }
        
        TreeNode root = new TreeNode(pre[preStart]);
        int preLeftTreeRootVal = pre[preStart + 1];
        int postLeftTreeLastIndex = num2Index.get(preLeftTreeRootVal);
        int leftTreeLen = postLeftTreeLastIndex - postStart + 1;
        root.left = dfs(pre, preStart + 1, preStart + leftTreeLen, post, postStart, postStart + leftTreeLen - 1, num2Index);
        root.right = dfs(pre, preStart + leftTreeLen + 1, preEnd, post, postStart + leftTreeLen, postEnd, num2Index);
        return root;
    }
    
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> num2Index = new HashMap<>();
        for(int i = 0; i < post.length; ++i){
            num2Index.put(post[i], i);
        }
        return dfs(pre, 0, pre.length - 1, post, 0, post.length - 1, num2Index);
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
