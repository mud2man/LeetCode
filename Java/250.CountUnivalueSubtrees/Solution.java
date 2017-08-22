/* Use postorder traversal: O(n)
 * 1. Postorder traverse all nodes
 * 2. Accumulate the number of univalue subtress if left and right subtress are univalue subtree, 
 *    and the their value is equal to the value of the current node
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
   private class Info{
        boolean isUniSubTree;
        int uniVal;
        int uniSubTreeNum;
        Info(boolean a, int b, int c){isUniSubTree = a; uniVal = b; uniSubTreeNum = c;}
    }
    
    public Info postOrderTraverse(TreeNode root){
        Info rootInfo;
        Info leftInfo;
        Info rightInfo;
        
        rootInfo = new Info(false, root.val, 0);
        
        if((root.left == null) && (root.right == null)){
            rootInfo.isUniSubTree = true;
            rootInfo.uniVal = root.val;
            rootInfo.uniSubTreeNum = 1;
            return rootInfo;
        }
        
        //traverse left 
        if(root.left != null){
            leftInfo = postOrderTraverse(root.left);
        }
        else{
            leftInfo = new Info(true, root.val, 0);
        }
        
        //traverse right
        if(root.right != null){
            rightInfo = postOrderTraverse(root.right);
        }
        else{
            rightInfo = new Info(true, root.val, 0);
        }
        
        if((leftInfo.isUniSubTree == true) && (rightInfo.isUniSubTree == true) && 
           (leftInfo.uniVal == rightInfo.uniVal) && (leftInfo.uniVal == rootInfo.uniVal)){
            rootInfo.isUniSubTree = true;
            rootInfo.uniVal = root.val;
            rootInfo.uniSubTreeNum = leftInfo.uniSubTreeNum + rightInfo.uniSubTreeNum + 1;
        }
        else{
           rootInfo.isUniSubTree = false;
           rootInfo.uniVal = root.val;
           rootInfo.uniSubTreeNum = leftInfo.uniSubTreeNum + rightInfo.uniSubTreeNum;
        }
        
        return rootInfo;
    }
    
    public int countUnivalSubtrees(TreeNode root) {
        Info info;
        
        if(root != null){
            info = postOrderTraverse(root);
            return info.uniSubTreeNum;
        }
        else{
            return 0;
        }
    }
 
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     5
         *    / \
         *   1   5
         *  / \   \
         * 5   5   5
         */
        root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);

        sol = new Solution();
        
        System.out.println("number of univalue subtrees: " + sol.countUnivalSubtrees(root));
    }
}
