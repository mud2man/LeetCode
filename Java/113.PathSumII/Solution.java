/* DFS: O(n)
 * 1. Use DFS to visit every node, and use a list "path" to record the visted nodes
 * 2. If residure == 0, and the current is a leaf. Put the path into the path list "pathList"
 */

import java.util.*;

/* Definition for binary tree */
class TreeNode 
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public void dfs(TreeNode root, int residure, LinkedList<Integer> path,  LinkedList<List<Integer>> pathList){
        if(root == null){
            return;
        }

        path.add(root.val);
        residure = residure - root.val;
        
        if(root.left == null && root.right == null){
            if(residure == 0){
                pathList.add(new LinkedList<Integer>(path));
                path.pollLast();
                return;
            }
        }
        
        dfs(root.left, residure, path, pathList);
        dfs(root.right, residure, path, pathList);
        path.pollLast();
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        LinkedList<Integer> path;
        LinkedList<List<Integer>> pathList;
        
        path = new LinkedList<Integer>();
        pathList = new LinkedList<List<Integer>>();
        
        dfs(root, sum, path, pathList);
        
        return pathList;
    }

    public static void main(String[] args)
    {
        List<List<Integer>> list;
        TreeNode root;
        Solution sol;
        int sum;

        /* Generate a input tree
         *       5
         *      / \
         *     4   8
         *    /   / \
         *   11  13  4
         *  /  \    / \
         * 7    2  5   1
         */
        
        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        sum = 22;
        sol = new Solution();
        
        list = sol.pathSum(root, sum);
        
        System.out.println("list of path sum = " + sum);
        for(List<Integer> path: list){
            System.out.println(path);
        }
    }
}
