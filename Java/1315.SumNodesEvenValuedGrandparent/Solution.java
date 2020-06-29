/* Math: Time:O(n), Space:O(h)
 * 1. Accumulate the sum if parent's val is even
 */     

import java.util.*; // Stack

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private int dfs(TreeNode root, TreeNode parent, TreeNode grandParent){
        if(root == null){
            return 0;
        }
        int sum =(grandParent != null && grandParent.val % 2 == 0)? root.val: 0;
        return sum + dfs(root.left, root, parent) + dfs(root.right, root, parent);
    }
    
    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root, null, null);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(5);
        System.out.println("sum with even-valued grandparent:" + sol.sumEvenGrandparent(root));
    }
}
