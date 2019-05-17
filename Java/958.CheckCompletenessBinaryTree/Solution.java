/* BFS: Time:O(n), Space:O(n)
 * 1. Scan tree level by level, detect the last level by the flag "sawNull"
 * 2. In the last level, only allow "null" in the tail and we cannot see non-null child
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
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;
        boolean sawNull = false;
        while(!queue.isEmpty()){
            if(queue.size() != count){
                return false;
            }
            if(!sawNull){
                int size = queue.size();
                for(int i = 0; i < size; ++i){
                    TreeNode node = queue.pollFirst();
                    sawNull = (node.left == null)? true: sawNull;
                    queue.add(node.left);
                    sawNull = (node.right == null)? true: sawNull;
                    queue.add(node.right);
                }
            }else{
                boolean sawLeave = false;
                while(!queue.isEmpty() && queue.peekFirst() != null){
                    TreeNode node = queue.pollFirst();
                    sawLeave = (node.left != null || node.right != null)? true: sawLeave;
                }
                while(!queue.isEmpty() && queue.peekFirst() == null){
                    queue.pollFirst();
                }
                return (!sawLeave && queue.isEmpty());
            }
            count *= 2;
        }
        return false;
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     4
         *    / \
         *   2   5
         *  / \   
         * 1   3  
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        Solution sol = new Solution();
        System.out.println("is complete tree: " + sol.isCompleteTree(root));
    }
}
