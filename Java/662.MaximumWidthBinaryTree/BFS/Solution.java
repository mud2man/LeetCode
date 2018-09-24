/* BFS: Time:O(n), Space:O(n)
 * 1. Traverse tree with BFS, and trim the left-end and right-end null
 * 2. Update the with, and construct the next-level queue
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
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean hasNext = true;
        int maxWidth = 1;
        while(hasNext){
            while(!queue.isEmpty() && queue.peekLast() == null){
                queue.pollLast();
            }
            while(!queue.isEmpty() && queue.peekFirst() == null){
                queue.pollFirst();
            }
            maxWidth = Math.max(maxWidth, queue.size());
            
            int len = queue.size();
            hasNext = false;
            for(int i = 0; i < len; ++i){
                TreeNode front = queue.pollFirst();
                if(front == null){
                    queue.add(null);
                    queue.add(null);
                }
                else{
                    queue.add(front.left);
                    queue.add(front.right);
                    hasNext = true;
                }
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
