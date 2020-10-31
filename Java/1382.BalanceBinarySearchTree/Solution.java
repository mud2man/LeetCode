/* BST: Time:O(n), Space:O(n)
 * 1. Put tree nodes into sortedList
 * 2. Insert the val into the new tree by selecting the mid value of sortedList
 */     

import java.util.*; // Stack

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    private void insertToList(TreeNode root, List<TreeNode> sortedList){
        if(root == null){
            return;
        }
        insertToList(root.left, sortedList);
        sortedList.add(root);
        insertToList(root.right, sortedList);
    }
    
    private TreeNode helper(int lb, int hb, List<TreeNode> sortedList){
        if(lb > hb){
            return null;
        }
        int mid = (lb + hb) / 2;
        TreeNode root = new TreeNode(sortedList.get(mid).val);
        root.left = helper(lb, mid - 1, sortedList);
        root.right = helper(mid + 1, hb, sortedList);
        return root;
    }
    
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> sortedList = new ArrayList<>();
        insertToList(root, sortedList);
        int midIdx = (0 + sortedList.size()) / 2;
        return helper(0, sortedList.size() - 1, sortedList);
    }
     
    public static void main(String[] args){
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        TreeNode newRoot = sol.balanceBST(root);
        System.out.println("No example");
    }
}
