/* BFS: Time:O(n), Space:O(n)
 * 1. Traverse all nodes on the same row, and find the maimum
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
    public List<Integer> largestValues(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> maximums = new LinkedList<Integer>();
        
        if(root != null){
            queue.add(root);
        }
        
        while(!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; ++i){
                TreeNode node = queue.pollFirst();
                max = Math.max(max, node.val);
                if(node.left != null){ queue.add(node.left);}
                if(node.right != null){ queue.add(node.right);}
            }
            maximums.add(max);
        }
        
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
