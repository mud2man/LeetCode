/* BFS: Time:O(n), Space:O(n), where the maximum number of nodes in a level is n/2
 * 1. Use BFS to find the insertion level, and insert the new nodes
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
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d == 1){
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for(int i = 0; i < d - 2; ++i){
            int size = queue.size();
            for(int j = 0; j < size; ++j){
                TreeNode head = queue.pollFirst();
                if(head.left != null){
                    queue.add(head.left);
                }
                if(head.right != null){
                    queue.add(head.right);
                }
            }
        }
        
        //insert
        for(TreeNode node: queue){
            TreeNode left = node.left;
            node.left = new TreeNode(v);
            node.left.left = left;
            TreeNode right = node.right;
            node.right = new TreeNode(v);
            node.right.right = right;
        }
        return root;
    }

    private void traverse(TreeNode head){
        if(head == null){
            return;
        }
        System.out.print(head.val + "->");
        traverse(head.left);
        traverse(head.right);
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        int v = 1;
        int d = 2;
        
        /* Generate a input tree
         *     4
         *    / \
         *   2   6
         *  / \   
         * 3   1  
         */
        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        System.out.println("v: " + v);
        System.out.println("d: " + d);
        System.out.println("before insert: ");
        sol.traverse(root);
        System.out.println("");
        System.out.println("after insert: ");
        sol.traverse(sol.addOneRow(root, v, d));
        System.out.println("");
    }
}
