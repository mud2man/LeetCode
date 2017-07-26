/* Stack: O(n)
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode 
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> preOrder = new ArrayList<Integer>();
        
        if(root == null){
            return preOrder;
        }
        
        stack.push(root);
        while(!stack.empty()){
            TreeNode subRoot = stack.pop();
            preOrder.add(subRoot.val);
            while(subRoot.left != null){
                preOrder.add(subRoot.left.val);
                if(subRoot.right != null){
                    stack.push(subRoot.right);
                }
                subRoot = subRoot.left;
            }
            
            if(subRoot.right != null){
                stack.push(subRoot.right);
            }
        }
        return preOrder;
    }

    public static void main(String[] args){
        List<Integer> preOrder;
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     3
         *    / \
         *   9   20
         *       / \
         *      15  7
         */
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        sol = new Solution();
        
        System.out.println("postOrder of tree: ");
        System.out.println(sol.preorderTraversal(root));
    }
}
