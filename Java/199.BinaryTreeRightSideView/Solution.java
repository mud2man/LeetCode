/* Use BFS: O(n)
 * 1. Traverse all nodes using BFS, and get the last node in the BFS queue
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
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> answer = new ArrayList();
        
        if(root != null)
            queue.add(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            answer.add(queue.get(size - 1).val);
            
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
        return answer;
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        List<Integer> rightSide; 
        
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
        rightSide = sol.rightSideView(root);
        System.out.println("rightSide view: " + rightSide);
    }
}
