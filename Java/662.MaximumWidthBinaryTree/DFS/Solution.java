/* DFS: Time:O(n), Space:O(n)
 * 1. Traverse tree with DFS, with node ID, and levels which record the id range
 * 2. Get the width by (level[1] - level[0] + 1), and update maximum width
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
    private void dfs(TreeNode root, double id, int level, List<double[]> levels){
        if(root == null){
            return;
        }
        if(levels.size() == level){
            levels.add(new double[]{-1.0, -1.0});
        }
        
        if(levels.get(level)[0] == -1.0){
            levels.get(level)[0] = id;
        }
        else{
            levels.get(level)[1] = id;
        }
        dfs(root.left, id * 2.0, level + 1, levels);
        dfs(root.right, id * 2.0 + 1.0, level + 1, levels);
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        List<double[]> levels = new ArrayList<>();
        dfs(root, 1, 0, levels);

        int maxWidth = 1;
        for(double[] level: levels){
            if(level[1] > level[0]){
                maxWidth = Math.max(maxWidth, (int)(level[1] - level[0] + 1.0));
            }
        }
        return maxWidth;
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     1
         *    / \
         *   3   2
         *  / \   \ 
         * 5   3   9
         */
        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        System.out.println("width: " + sol.widthOfBinaryTree(root));
    }
}
