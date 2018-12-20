/* BFS: Time:O(n), Space:O(n)
 * 1. 
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
        
        while(!queue.isEmpty()){
            if(queue.peekFirst() == null){
                while(!queue.isEmpty()){
                    if(queue.pollFirst() != null){
                        return false;
                    }
                }
                return true;
            }
            
            if(count != queue.size()){    
                return false;
            }
            boolean seeNull = false;
            for(int i = 0; i < count; ++i){
                TreeNode node = queue.pollFirst();
                if(node == null){
                    seeNull = true;
                }
                else{
                    if(seeNull){
                        return false;
                    }
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            count = count * 2;
        }
        return true;
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     4
         *    / \
         *   2   5
         *  / \   
         * 1   3  
         */
        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println("is complete tree: " + sol.isCompleteTree(root));
    }
}
