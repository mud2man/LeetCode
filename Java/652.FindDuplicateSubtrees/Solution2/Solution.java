/* PostOrder and UnionFind: Time:O(n) Space:O(n), where n is the node number
 * 2. Have map "leftRightMiddleTable" to record the subtree has the same left'identifier, right's identifier and middle's val
 * 3. If two subtrees have the same left'identifier, right's identifier and middle's val, we can claim they have same structures
 * 4. Have map "root2Count" to record the number of subtree with the same identifier
 * 5. Return the subtree with count > 1
 */

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class Solution{
    private TreeNode postOrder(TreeNode root, Map<TreeNode, Map<TreeNode, Map<Integer, TreeNode>>> leftRightMiddleTable, Map<TreeNode, Integer> root2Count){
        if(root == null){
            return null;
        }
        TreeNode left = postOrder(root.left, leftRightMiddleTable, root2Count);
        TreeNode right = postOrder(root.right, leftRightMiddleTable, root2Count);
        if(leftRightMiddleTable.containsKey(left) && leftRightMiddleTable.get(left).containsKey(right) 
           && leftRightMiddleTable.get(left).get(right).containsKey(root.val)){
            root = leftRightMiddleTable.get(left).get(right).get(root.val);
        }else{
            leftRightMiddleTable.putIfAbsent(left, new HashMap<>());
            leftRightMiddleTable.get(left).computeIfAbsent(right, key -> new HashMap<>()).put(root.val, root);
        }
        root2Count.putIfAbsent(root, 0);
        root2Count.put(root, root2Count.get(root) + 1);
        return root;
    }
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<TreeNode, Map<TreeNode, Map<Integer, TreeNode>>> leftRightMiddleTable = new HashMap<>();
        Map<TreeNode, Integer> root2Count = new HashMap<>();
        postOrder(root, leftRightMiddleTable, root2Count);
        List<TreeNode> duplicates = new ArrayList<>();
        for(Map.Entry<TreeNode, Integer> entry: root2Count.entrySet()){
            if(entry.getValue() > 1){
                duplicates.add(entry.getKey());
            }
        }
        return duplicates;
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         *  /   / \
         * 4   2   4
         *    /
         *   4   
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);
        Solution sol = new Solution();
        System.out.println("duplicates: " + sol.findDuplicateSubtrees(root));
    }
}
