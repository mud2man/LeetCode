/* BFS: O(n)
 * 1. Use BFS, and keep the first element of the queue as the lef tmost node
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
    public int findBottomLeftValue(TreeNode root) {
        TreeNode leftmost = null;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    
        queue.add(root);
        while(!queue.isEmpty()){
            leftmost = queue.peekFirst();
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                TreeNode node = queue.pollFirst();
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return leftmost.val;
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
        
        System.out.println("leftmost: " + sol.findBottomLeftValue(root));
    }
}
