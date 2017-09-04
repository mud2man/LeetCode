/* BFS: O(n)
 * 1. Get the left range and right range
 * 1. Use BFS traverse all nodes with the index of verticalOrders
 * ex: 
 *     3
 *    / \
 *   9   20
 *       / \
 *      15  7
 *
 * 1st: queue: [1, 3]
 * 2nd: queue: [0, 9] -> [2, 20]
 * 3th: queue: [1, 15] -> [3, 17]
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
    private class QueueNode{
        int idx;
        TreeNode treeNode;
        QueueNode(int i, TreeNode t){idx = i; treeNode = t;}
    }
    
    private void getRange(TreeNode root, int idx, int[] range){
        if(root == null){
            return;
        }
        range[0] = Math.min(range[0] , idx);
        range[1] = Math.max(range[1] , idx);
        getRange(root.left, idx - 1, range);
        getRange(root.right, idx + 1, range);
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        //[left, right]  
        int[] range = new int[2];   
        int rootWidth = (root == null)? 0: 1;
        List<List<Integer>> verticalOrders = new ArrayList<List<Integer>>();
        
        getRange(root, 0, range);
        range[0] = -range[0];
        
        for(int i = 0; i < (range[0] + range[1] + rootWidth); ++i){
            verticalOrders.add(new ArrayList<Integer>());
        }
        
        LinkedList<QueueNode> queue= new LinkedList<QueueNode>();
        
        // BFS
        if(root != null){
            queue.add(new QueueNode(range[0], root));
        }
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                QueueNode queueNode = queue.pollFirst();
                verticalOrders.get(queueNode.idx).add(queueNode.treeNode.val);
                if(queueNode.treeNode.left != null){
                    queue.add(new QueueNode(queueNode.idx - 1, queueNode.treeNode.left));
                }
                
                if(queueNode.treeNode.right != null){
                    queue.add(new QueueNode(queueNode.idx + 1, queueNode.treeNode.right));
                }
            }
        }

        return verticalOrders;
    }

    public static void main(String[] args){
        List<List<Integer>> list;
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *     3
         *    / \
         *   9   20
         *       / \
         *      15  7
         */
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        sol = new Solution();
        list = sol.verticalOrder(root);

        System.out.println("vertical order traversal: ");
        for(List<Integer> col: list){
            System.out.println(col);
        }
    }
}
