/* Post order traveral: Time:O(n), Space:O(n)
 * 1. postOrder retunr an array lengths[]
 * 2. lengths[0] = max decending length starting from root
 * 3. lengths[1] = max ascending length starting from root
 * 4. Also considering child-Parent-child order path
 * 5. Get the maximum among lengths[0], lengths[1], (leftLengths[1] + rightLengths[0] + 1),and (leftLengths[0] + rightLengths[1] + 1) 
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
    private int[] postOrder(int[] maxLength, TreeNode root){
        //lengths[0] = max decending length starting from root
        //lengths[1] = max ascending length starting from root 
        int[] lengths = new int[2]; 
        
        if(root == null){
            return lengths;
        }
        
        lengths[0] = 1;
        lengths[1] = 1;
        int[] leftLengths = postOrder(maxLength, root.left);
        int[] rightLengths = postOrder(maxLength, root.right);
        
        if(root.left != null){
            lengths[0] = (root.val == (root.left.val + 1))? Math.max(lengths[0], leftLengths[0] + 1): lengths[0];
            lengths[1] = (root.val == (root.left.val - 1))? Math.max(lengths[1], leftLengths[1] + 1): lengths[1];
        }
        
        if(root.right != null){
            lengths[0] = (root.val == (root.right.val + 1))? Math.max(lengths[0], rightLengths[0] + 1): lengths[0];
            lengths[1] = (root.val == (root.right.val - 1))? Math.max(lengths[1], rightLengths[1] + 1): lengths[1];
        }
       
        maxLength[0] = Math.max(maxLength[0], lengths[0]);
        maxLength[0] = Math.max(maxLength[0], lengths[1]);
        
        if(root.left != null && root.right != null){
            if((root.left.val == root.val + 1) && (root.right.val == root.val - 1)){
                int length = leftLengths[1] + rightLengths[0] + 1;
                maxLength[0] = Math.max(maxLength[0], length);
            }
            
            if((root.left.val == root.val - 1) && (root.right.val == root.val + 1)){
                int length = leftLengths[0] + rightLengths[1] + 1;
                maxLength[0] = Math.max(maxLength[0], length);
            }
        }
        
        return lengths;
    }
    
    public int longestConsecutive(TreeNode root) {
        int[] maxLength = new int[]{0};
        postOrder(maxLength, root);
        return maxLength[0]; 
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     2
         *    / \
         *   1   3
         */
        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        sol = new Solution();
        
        System.out.println("maximum consecutive length: " + sol.longestConsecutive(root));
    }
}
