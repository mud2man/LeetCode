/* Stack: O(h)
 * 1. Have a stack to track the visted node
 * 2. If p has right, return the leftest node of the sub-tree has root p
 * 3. Otherwise, return the first farther has the left child equals to the top of the stack
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
    private void find(TreeNode root, TreeNode p, LinkedList<TreeNode> stack){
        if(root == null || root.val == p.val){
            return;
        }
        
        stack.add(root);
        if(root.val > p.val){
            find(root.left, p, stack);
        }
        else{
            find(root.right, p, stack);
        }
    }
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        find(root, p, stack);
        
        if(p.right != null){
            TreeNode itr = p.right;
            while(itr.left != null){itr = itr.left;}
            return itr;
        }
        else{
            TreeNode son = p;
            TreeNode farther = (!stack.isEmpty())? stack.pollLast(): null;
            while(farther != null && farther.left != son){
                son = farther;
                farther = (!stack.isEmpty())? stack.pollLast(): null;
            }
            
            if(farther != null){
                return farther;
            }
        }
        
        return null;
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     4
         *    / \
         *   2   5
         *  / \ 
         * 1   3
         */
        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeNode p = root.left.right;
        sol = new Solution();

        System.out.println("p:" + p.val);
        System.out.println("The successor of p:" + sol.inorderSuccessor(root, p).val);
    }
}
