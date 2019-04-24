/* DFS: O(n)
 * 1. Use DFS to visit every node, and use a list "path" to record the visted nodes
 * 2. If residure == 0, and the current is a leaf. Put the path into the path list "pathList"
 */

import java.util.*;

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public void helper(TreeNode root, int remain, LinkedList<Integer> path, List<List<Integer>> paths){
        if(root == null){
            return;
        }
        
        path.add(root.val);
        if(root.left == null && root.right == null && remain == root.val){
            paths.add(new LinkedList<Integer>(path));
        }
        helper(root.left, remain - root.val, path, paths);
        helper(root.right, remain - root.val, path, paths);
        path.pollLast();
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new LinkedList<List<Integer>>();
        LinkedList<Integer> path = new LinkedList<Integer>();
        helper(root, sum, path, paths);
        return paths;
    }

    public static void main(String[] args){
        /* Generate a input tree
         *       5
         *      / \
         *     4   8
         *    /   / \
         *   11  13  4
         *  /  \    / \
         * 7    2  5   1
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        int sum = 22;
        Solution sol = new Solution();
        System.out.println("sum:" + sum);
        System.out.println("path:" + sol.pathSum(root, sum));
    }
}
