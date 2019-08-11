/* In oredr: Time:O(n), Space:O(n)
 * 1. Traverse BST inorderly, and transfom it in place
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
    private void inOredr(TreeNode root, TreeNode[] head){
        if(root == null){
            return;
        }
        
        inOredr(root.left, head);
        TreeNode right = root.right;
        if(head[0] == null){
            head[0] = root;
            head[0].left = root;
            head[0].right = root;
        }else{
            root.right = head[0];
            root.left = head[0].left;
            head[0].left.right = root;
            head[0].left = root;   
        }
        inOredr(right, head);
    }
    
    public TreeNode treeToDoublyList(TreeNode root) {
        TreeNode[] head = new TreeNode[1];
        inOredr(root, head);
        return head[0];
    }

    private void traverse(TreeNode head){
        Set<TreeNode> visited = new HashSet<>();
        while(visited.add(head)){
            System.out.print(head.val + "->");
            head = head.right;
        }
        System.out.println("");
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
        System.out.println("After transform: ");
        Solution sol = new Solution();
        TreeNode head = sol.treeToDoublyList(root);
        sol.traverse(head); 
    }
}
