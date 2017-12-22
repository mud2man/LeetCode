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
        if(root.left == null && root.right != null){
            boundary.add(root.val);
            return;
        }
        
        while(root.left != null || root.right != null){
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
        if(root.left != null && root.right == null){
            return;
        }
        
        root = root.right;
        List<Integer> tmp = new ArrayList<Integer>();
        while(root != null && (root.left != null || root.right != null)){
            tmp.add(root.val);
            root = (root.right != null)? root.right: root.left;
        }
        
        for(int i = tmp.size() - 1; i >= 0; --i){
            boundary.add(tmp.get(i));
        }
    }
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if(root == null){
          return new ArrayList<Integer>();  
        }
        
        List<Integer> boundary = new ArrayList<Integer>();
        getLeftBoundary(root, boundary);
        getBottomBoundary(root, boundary);
        getRightBoundary(root, boundary);
        return boundary;
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     1
         *      \
         *       2
         *      / \
         *     3   4
         */
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        sol = new Solution();
        List<Integer> boundary = sol.boundaryOfBinaryTree(root);
        System.out.println("boundary: " + boundary);
    }
}
