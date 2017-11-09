/* BFS: O(n)
 * 1. Have two queue leftQueue, and rightQueue
 * 2. Compare each element from leftQueue and rightQueue
 * 3. Store left then right for leftQueue, store right then left for rightQueue
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
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        
        LinkedList<TreeNode> leftQueue = new LinkedList<TreeNode>();
        leftQueue.add(root.left);
        LinkedList<TreeNode> rightQueue = new LinkedList<TreeNode>();
        rightQueue.add(root.right);
        
        while(!leftQueue.isEmpty() || !rightQueue.isEmpty()){
            if(leftQueue.size() != rightQueue.size()){
                return false;
            }
            
            for(int i = 0; i < leftQueue.size(); ++i){
                TreeNode leftNode = leftQueue.pollFirst();
                TreeNode rightNode = rightQueue.pollFirst();
                if(leftNode == null && rightNode == null){
                    continue;
                }
                else if(leftNode != null && rightNode != null){
                    if(leftNode.val == rightNode.val){
                        leftQueue.add(leftNode.left);
                        leftQueue.add(leftNode.right);
                        rightQueue.add(rightNode.right);
                        rightQueue.add(rightNode.left);
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
        }
        
        return (leftQueue.isEmpty() && rightQueue.isEmpty()); 
    }

    public static void main(String[] args){
        List<List<Integer>> list;
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     3
         *    / \
         *   9   9
         *  /     \
         * 7       7
         */
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(7);
        root.right.right = new TreeNode(7);

        sol = new Solution();
        System.out.println("isSymmetric? " + sol.isSymmetric(root));
    }
}
