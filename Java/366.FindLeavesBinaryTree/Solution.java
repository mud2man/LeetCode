/* Post order traveral: O(n)
 * 1. Traverse post order
 * 2. Update current height = max(height of left subtree, height of right subtree) + 1
 * 3. If there is no the group of leaves of height = current hight, add a new one
 * 4. If yes, add the leave to the corresponding group
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
    private int getHeightAndStore(List<List<Integer>> levels, TreeNode root){
        if(root == null){
            return 0;
        }
        
        int leftHeight = getHeightAndStore(levels, root.left);
        int rightHeight = getHeightAndStore(levels, root.right);
        int rootHeight = Math.max(leftHeight, rightHeight) + 1;
        
        if(levels.size() < rootHeight){
            levels.add(new ArrayList<Integer>());
        }
        levels.get(rootHeight - 1).add(root.val);
        
        return rootHeight;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        getHeightAndStore(levels, root);
        return levels;
    }

    public static void main(String[] args){
        List<List<Integer>> leavesGroup;
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \  /
         *   4   7 13 
         */
        root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(13);

        sol = new Solution();
        
        leavesGroup = sol.findLeaves(root);
        
        System.out.println("The group of leaves: ");
        for(List<Integer> leaves: leavesGroup){
            System.out.println(leaves);
        }
    }
}
