/* DFS: O(n)
 * 1. Search p first, and store its ancestors into a stack
 * 2. Traverse ancestor of p from the last, and dfsSearch q given root as the ancestor of p
 */

import java.util.*;

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private boolean dfsSearch(TreeNode root, TreeNode p, LinkedList<TreeNode> stack){
        if(root == null){
            return false;
        }
    
        if(root == p){
            return true;
        }
        
        if(stack != null){
            stack.add(root);
        }
        
        if(dfsSearch(root.left, p, stack)){
            return true;
        }
        
        if(dfsSearch(root.right, p, stack)){
            return true;
        }
        
        if(stack != null){
            stack.pollLast();
        }
        
        return false;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> stackP = new LinkedList<TreeNode>();
        dfsSearch(root, p, stackP);
        stackP.add(p);
            
        for(int i = (stackP.size() - 1); i >= 0 ; --i){
            TreeNode ancestorP = stackP.get(i);
            if(dfsSearch(ancestorP, q, null)){
                return ancestorP;
            }
        }
        
        return null;
    }
 
    public static void main(String[] args) {
        TreeNode root;
        TreeNode lca;
        Solution sol;
        
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \ 
         *   4   7 
         */
        root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);

        sol = new Solution();
        lca = sol.lowestCommonAncestor(root, root.left.left, root.left.right.right);

        System.out.println(lca.val);
        
        sol = null;
    }
}
