/* Hash: Time:O(n), Space:O(n)
 * 1. Restore tree and store the seen values in set "values"
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
}

public class Solution {
    Set<Integer> values;
    private void dfs(TreeNode root, int val, Set<Integer> values){
        if(root == null){
            return;
        }
        values.add(val);
        root.val = val;
        dfs(root.left, 2 * val + 1, values);
        dfs(root.right, 2 * val + 2, values);
    }
    
    public Solution(TreeNode root) {
        values = new HashSet<>();
        dfs(root, 0, values);
    }
    
    public boolean find(int target) {
        return values.contains(target);
    }
 
    public static void main(String[] args){
        /* Generate a input tree
         *    -1
         *      \ 
         *      -1
         */
        TreeNode root = new TreeNode(-1);
        root.right = new TreeNode(-1);
        Solution sol = new Solution(root);
        int target = 1;
        System.out.println("find(" + target + "):" + sol.find(target));
        target = 2;
        System.out.println("find(" + target + "):" + sol.find(target));
    }
}
