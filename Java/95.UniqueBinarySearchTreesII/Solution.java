/* Recursive: Time:O(c(2n + 1, n), Space:O(c(2n + 1, n))
 * 1. Pick the root val i from {start, end}. The left tree contains {start, i - 1}, the right tree contains {i + 1, end}
 * 2. Append all the left trees with root.left, and the right ones with root.right
 *
 * Complexity: A tree has unique identy if we traverse preorderly and considering null. 
 *             So the number of identity is the number of trees with unique structure, which is c(2n + 1, n + 1), n + 1 is the number of #
 * e.g.,  2   => 2,1,#,#,3,#,#
 *       / \
 *      1   3
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
    private List<TreeNode> generateTrees(int start, int end){
        if(start > end){
            return Collections.singletonList(null);
        }else{
            List<TreeNode> trees = new ArrayList<>();
            for(int i = start; i <= end; ++i){
                List<TreeNode> leftTrees = generateTrees(start, i - 1);
                List<TreeNode> rightTrees = generateTrees(i + 1, end);
                for(TreeNode leftTree: leftTrees){
                    for(TreeNode rightTree: rightTrees){
                        TreeNode root = new TreeNode(i);
                        root.left = leftTree;
                        root.right = rightTree;
                        trees.add(root);
                    }
                }
            }
            return trees;
        }
    }
    
    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }
 
    private void traverse(TreeNode head){
        if(head == null){
            return;
        }else{
            System.out.print(head.val + "->");
            traverse(head.left);
            traverse(head.right);
        }
    }

    public static void main(String[] args){
        int n = 3;
        System.out.println("n: " + n);
        Solution sol = new Solution();
        List<TreeNode> trees = sol.generateTrees(n);
        for(TreeNode tree: trees){
            sol.traverse(tree);
            System.out.println("");
        }
    }
}
