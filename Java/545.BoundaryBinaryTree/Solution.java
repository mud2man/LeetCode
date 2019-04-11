/* Time:O(n), Space:O(1)
 * 1. Have a list boundary
 * 2. Invoke getLeftBoundary, getBottomBoundary and getRightBoundary sequently
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
    private void getLeftBoundary(TreeNode root, List<Integer> boundary){
        if(root!= null && root.left == null && root.right != null){
            boundary.add(root.val);
            return;
        }
        while(root!= null && (root.left != null || root.right != null)){
            boundary.add(root.val);
            root = (root.left != null)? root.left: root.right;
        }
    }
    
    private void getBottomBoundary(TreeNode root, List<Integer> boundary){
        if(root == null){
            return;
        }
        else if(root.left == null && root.right == null){
            boundary.add(root.val);
        }
        else{
            getBottomBoundary(root.left, boundary);
            getBottomBoundary(root.right, boundary);
        }
    }
    
    private void getRightBoundary(TreeNode root, List<Integer> boundary){
        if(root!= null && root.left != null && root.right == null){
            return;
        }
        root = (root != null)? root.right: null;
        Deque<Integer> stack = new LinkedList<Integer>();
        while(root != null && (root.left != null || root.right != null)){
            stack.add(root.val);
            root = (root.right != null)? root.right: root.left;
        }
        while(!stack.isEmpty()){
            boundary.add(stack.pollLast());
        }
    }
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> boundary = new ArrayList<Integer>();
        getLeftBoundary(root, boundary);
        getBottomBoundary(root, boundary);
        getRightBoundary(root, boundary);
        return boundary;
    }
 
    public static void main(String[] args){
        /* Generate a input tree
         *     1
         *      \
         *       2
         *      / \
         *     3   4
         */
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        Solution sol = new Solution();
        List<Integer> boundary = sol.boundaryOfBinaryTree(root);
        System.out.println("boundary: " + boundary);
    }
}
