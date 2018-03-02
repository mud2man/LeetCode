/* PostOrder: Time:O(n^2) Space:O(n), where n is the node number
 * 1. Because the postorder of a binary tree including null is unique, we can use it as an identity
 * 2. Use postorder traversal to create a count hashmap, and find the duplicates
 */

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class Solution{
    private String postOrder(TreeNode node, Map<String, Integer> countMap, List<TreeNode> duplicates){
        if(node == null){
            return "#";
        }
        
        String identity = postOrder(node.left, countMap, duplicates) + "," + postOrder(node.right, countMap, duplicates);
        identity += "," + Integer.toString(node.val);
        countMap.putIfAbsent(identity, 0);
        countMap.put(identity, countMap.get(identity) + 1);
        if(countMap.get(identity) == 2){
            duplicates.add(node);
        }
        return identity;
    }
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        List<TreeNode> duplicates = new ArrayList<TreeNode>();
        postOrder(root, countMap, duplicates);
        return duplicates;
    }
    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         *  /   / \
         * 4   2   4
         *    /
         *   4   
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);
        sol = new Solution();
        System.out.println("duplicates: " + sol.findDuplicateSubtrees(root));
    }
}
