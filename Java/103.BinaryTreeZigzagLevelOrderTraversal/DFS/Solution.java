/* In oredr: Time:O(n), Space:O(h), where h is hight
 * 1. Traverse tree in preorder
 * 2. Add val to the end of list, if level# is even
 * 3. Add val to the start of list, if level# is odd
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
    private void dfs(TreeNode node, int level, List<List<Integer>> levels){
        if(node == null){
            return;
        }
        
        if(levels.size() == level){
            levels.add(new LinkedList<>());    
        }
        
        if(level % 2 == 0){
            levels.get(level).add(node.val);
        }
        else{
            levels.get(level).add(0, node.val);
        }
        dfs(node.left, level + 1, levels);
        dfs(node.right, level + 1, levels);
    }
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        dfs(root, 0, levels);
        return levels;
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     3
         *    / \
         *   9  20
         *      / \ 
         *     15  7  
         */
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> zigzag = sol.zigzagLevelOrder(root);
        System.out.println("zigzag: " + zigzag);
    }
}
