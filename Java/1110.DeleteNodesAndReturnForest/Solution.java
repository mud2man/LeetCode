/* DFS: Time:O(n), Space:O(n)
 * 1. Use dfs to collects forest
 * 2. If the current node is need be deleted, the cut off the link between it and farther. Then set hasFarther as false to the next dfs
 * 3. Otherwise, add node to forests if the node has no farther
 */

import java.util.*;

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

}
public class Solution{
    private void dfs(TreeNode root, Set<Integer> toBeDeleted, List<TreeNode> forests, TreeNode farther, boolean hasFarther){
        if(root == null){
            return;
        }
        
        boolean noDeleted = true;
        if(toBeDeleted.contains(root.val)){
            if(farther.left == root){
                farther.left = null;
            }else{
                farther.right = null;
            }
            noDeleted = false;
        }else{
            if(!hasFarther){
                forests.add(root);
            }
        }
        dfs(root.left, toBeDeleted, forests, root, noDeleted);
        dfs(root.right, toBeDeleted, forests, root, noDeleted);
    }
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toBeDeleted = new HashSet<>();
        for(int candidate: to_delete){
            toBeDeleted.add(candidate);
        }
        List<TreeNode> forests = new ArrayList<>();
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        dfs(root, toBeDeleted, forests, dummy, false);
        return forests;
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     3
         *    / \
         *   9   20
         *       / \
         *      15  7
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int[] to_delete = {20};
        Solution sol = new Solution();
        System.out.println("to_delete: " + Arrays.toString(to_delete));
        List<TreeNode> forest = sol.delNodes(root, to_delete);
        for(TreeNode node: forest){
            System.out.println("root: " + node.val);
        }
    }
}
