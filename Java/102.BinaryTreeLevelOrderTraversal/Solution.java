/* BFS: O(n)
 * 1. Visist every level and store their children into the queue
 * 2. Repeat step 1 until the queue is empty
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode 
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new LinkedList<List<Integer>>();
        List<Integer> level;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int size;
        TreeNode farther;
        
        if(root != null){
            queue.add(root);
        }
        
        while(!queue.isEmpty()){
            size = queue.size();
            level = new LinkedList<Integer>();
            for(int i = 0; i < size; ++i){
                farther = queue.pollFirst();
                level.add(farther.val);
                if(farther.left != null)
                    queue.add(farther.left);
                if(farther.right != null)
                    queue.add(farther.right);
            }
            levels.add(level);
        }
        return levels;
    }
  
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        List<List<Integer>> levels;

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
        levels = sol.levelOrder(root);
        
        System.out.println("levels:");
        for(List<Integer> level: levels){
            System.out.println(level);
        }
    }
}
