/* DFS: Time:O(n), Space:O(n)
 * 1. Have a "levelSums" to record the sum of levels
 * 2. Update maxSum and maxSumLevel while iterating from level#0
 */

import java.util.*; // Stack

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private void dfs(TreeNode root, int level, List<Integer> levelSums){
        if(root == null){
            return;
        }
        
        if(levelSums.size() == level){
            levelSums.add(0);
        }
        levelSums.set(level, levelSums.get(level) + root.val);
        dfs(root.left, level + 1, levelSums);
        dfs(root.right, level + 1, levelSums);
    }
    
    public int maxLevelSum(TreeNode root) {
        List<Integer> levelSums = new ArrayList<>();
        dfs(root, 0, levelSums);
        int maxSum = root.val;
        int maxSumLevel = 0;
        for(int i = 1; i < levelSums.size(); ++i){
            if(levelSums.get(i) > maxSum){
                maxSumLevel = i;
                maxSum = levelSums.get(i);
            }
        }
        return maxSumLevel + 1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        /* Generate a input tree
         *       1
         *      / \
         *     7   0
         *    / \
         *   7  -8
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-8);
        System.out.println("maximum level:" + sol.maxLevelSum(root));
    }
}
