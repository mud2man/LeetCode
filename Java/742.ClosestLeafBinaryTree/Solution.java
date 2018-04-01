/* DFS + BFS: Time:O(n), Space:O(h)
 * 1. Traverse all nodes using DFS to find the target node and record the path
 * 2. Retrieve the node from the bottom of the path, and find its own nearest leaf and update the global nearest leaf
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
    private boolean find(LinkedList<TreeNode> path, TreeNode root, int target){
        if(root == null){
            return false;
        }
        
        path.add(root);
        if(root.val == target){
            return true;
        }
        
        if(find(path, root.left, target) == true){
            return true;
        }
        else if(find(path, root.right, target) == true){
            return true;
        }
        else{
            path.pollLast();
            return false;
        }
    }
    
    private int[] bfs(TreeNode root){
        int depth = 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        if(root != null){
            queue.add(root);  
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                TreeNode node = queue.pollFirst();
                if(node.left == null && node.right == null){
                    return new int[]{depth, node.val};
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return new int[]{Integer.MAX_VALUE, 0};
    }
    
    public int findClosestLeaf(TreeNode root, int k) {
        LinkedList<TreeNode> path = new LinkedList<TreeNode>();
        find(path, root, k);
        TreeNode node = path.pollLast();
        int[] nearestLeaf = bfs(node);
        TreeNode previousNode = node;
        int base = 2;
        while(!path.isEmpty()){
            node = path.pollLast();
            int[] leaf = (node.left == previousNode)? bfs(node.right): bfs(node.left);
            leaf[0] = (leaf[0] == Integer.MAX_VALUE)? leaf[0]: leaf[0] + base;
            base += 2;
            previousNode = node;
            if(nearestLeaf[0] > leaf[0]){
                nearestLeaf[0] = leaf[0];
                nearestLeaf[1] = leaf[1];
            }
        }
        return nearestLeaf[1];
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *         1
         *        / \
         *       2   3
         *      /
         *     4
         *    /
         *   5
         *  /
         * 6
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.left.left.left.left = new TreeNode(6);
        sol = new Solution();
        System.out.println("nearset leaf of 2:" + sol.findClosestLeaf(root, 2));
    }
}
