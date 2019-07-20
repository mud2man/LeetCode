/* Tree: Time:O(h), Space:O(h)
 * 1. If p has right, return getSmallestNode(p.right)
 * 2. Otherwise, find the first farther has left son in the path
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
    private void search(TreeNode root, TreeNode p, Deque<TreeNode> path){
        path.add(root);
        if(root == p){
            return;
        }else if(root.val > p.val){
            search(root.left, p, path);
        }else{
            search(root.right, p, path);
        }
    }
    
    private TreeNode getSmallestNode(TreeNode root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p.right != null){
            return p;
        }else{
            Deque<TreeNode> path = new LinkedList<>();
            search(root, p, path);
            while(path.size() >= 2){
                TreeNode tail = path.pollLast();
                if(tail == path.peekLast().left){
                    return path.peekLast();
                }
            }
            return null;
        }
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
        TreeNode p = root.left.right;

        Solution sol = new Solution();
        System.out.println("p:" + p.val);
        System.out.println("The successor of p:" + sol.inorderSuccessor(root, p).val);
    }
}
