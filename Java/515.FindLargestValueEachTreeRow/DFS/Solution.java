/* DFS: Time:O(n), Space:O(h)
 * 1. Use dfs traverse node given "level", and update maximums
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
    private void dfs(TreeNode root, int level, List<Integer> maximums){
        if(root == null){
            return;
        }
        
        if(maximums.size() == level){
            maximums.add(root.val);
        }
        else{
            maximums.set(level, Math.max(maximums.get(level), root.val));
        }
        dfs(root.left, level + 1, maximums);
        dfs(root.right, level + 1, maximums);
    }
    
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> maximums = new ArrayList<Integer>();
        dfs(root, 0, maximums);
        return maximums;
    }
  
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \  /
         *   4   7 13 
         */
        root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(13);

        sol = new Solution();
        List<Integer> largestValues = sol.largestValues(root);
        System.out.println("largestValues: " + sol.largestValues(root));
    }
}
