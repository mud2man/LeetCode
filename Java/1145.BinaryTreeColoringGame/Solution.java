/* DFS: Time:O(n), Space:O(n)
 * 1. Find the targeted node given x, and get the "leftSize", "rightSiz", and "otherSize"
 * 2. The blue size "blueSize" is max(leftSize, rightSize, otherSize)
 * 3. The red size "redSize" is n - blueSize
 * 4. Compare blueSize and redSize
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
    private TreeNode search(TreeNode root, int x){
        if(root == null){
            return null;
        }else if(root.val == x){
            return root;
        }else{
            TreeNode leftResult = search(root.left, x);
            if(leftResult != null){
                return leftResult;
            }else{
                return search(root.right, x);
            }
        }
    }
    
    private int getSize(TreeNode root){
        if(root == null){
            return 0;
        }else{
            return getSize(root.left) + getSize(root.right) + 1;
        }
    }
    
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode nodeX = search(root, x);
        int leftSize = getSize(nodeX.left);
        int rightSize = getSize(nodeX.right);
        int otherSize = n - leftSize - rightSize - 1;
        int blueSize = Math.max(Math.max(leftSize, rightSize), otherSize);
        int redSize = n - blueSize;
        return (blueSize > redSize);
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
        int n  = 5;
        int x = 5;
        System.out.println("n:" + n + ", x:" + x);
        System.out.println("can blue win:" + sol.btreeGameWinningMove(root, n, x));
    }
}
