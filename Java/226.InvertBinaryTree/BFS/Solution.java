/* BFS: Time:O(n), Space:O(n)
 * 1. Swap left and right of the node in the BFS queue
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
    public TreeNode invertTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                TreeNode head = queue.pollFirst();
                if(head != null){
                    TreeNode tmp = head.left;
                    head.left = head.right;
                    head.right = tmp;
                    queue.add(head.left);
                    queue.add(head.right);
                }
            }
        }
        return root;
    }
 
    public void dump(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + "->");
        dump(root.left);
        dump(root.right);
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     1
         *    / \ 
         *   3   4
         */
        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        
        System.out.println("before invert: ");
        sol.dump(root);
        System.out.println("");

        sol.invertTree(root);

        System.out.println("after invert: ");
        sol.dump(root);
        System.out.println("");
    }
}
