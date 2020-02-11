/* DSF: Time:O(n), Space:O(h) 
 */

import java.util.*;
import java.util.concurrent.*;

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution{
    private long getSum(TreeNode node){
        if(node == null){
            return 0;
        }
        return (long)node.val + getSum(node.left) + getSum(node.right);
    }
    
    private long updateProduct(TreeNode node, long sum, long[] maxProduct){
        if(node == null){
            return 0;
        }
        long subSum = updateProduct(node.left, sum, maxProduct) + updateProduct(node.right, sum, maxProduct) + (long)node.val;
        maxProduct[0] = Math.max(maxProduct[0], subSum * (sum - subSum));
        return subSum;
    }
    
    public int maxProduct(TreeNode root) {
        long sum = getSum(root);
        long[] maxProduct = new long[1];
        updateProduct(root, sum, maxProduct);
        return (int)(maxProduct[0] % 1_000_000_007);
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     5
         *    / \
         *   2   1
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(1);
        Solution sol = new Solution();
        System.out.println("max product:" + sol.maxProduct(root));
    }
}
