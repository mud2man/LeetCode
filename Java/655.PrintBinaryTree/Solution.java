/* DFS: Time:O(h*2^h), Space:O(h*2^h). BFS is another solution
 * 1. Get the tree's height h
 * 2. Get the width w by 2^h - 1, 
 * 3. Use dfs to put the root's value on the mid = (lb + hb) / 2. Then call dfs recursively
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
    private int getHeight(TreeNode root){
        return (root == null)? 0: Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
    
    private void dfs(TreeNode root, int depth, int lb, int hb, List<List<String>> ret){
        if(root == null){
            return;
        }
        int mid = (lb + hb) / 2;
        ret.get(depth).set(mid, Integer.toString(root.val));
        dfs(root.left, depth + 1, lb, mid - 1, ret);
        dfs(root.right, depth + 1, mid + 1, hb, ret);
    }
    
    public List<List<String>> printTree(TreeNode root) {
        int h = getHeight(root);
        int w = (int)Math.pow(2, h) - 1;
        
        List<List<String>> ret = new ArrayList<>();
        for(int i = 0; i < h; ++i){
            ret.add(new ArrayList<>());
            for(int j = 0; j < w; ++j){
                ret.get(i).add("");
            }
        }
        dfs(root, 0, 0, w - 1, ret);
        return ret;
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        List<List<String>> list = sol.printTree(root);
        System.out.println("list: " + list);
    }
}
