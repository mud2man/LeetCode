/* BFS: Time:O(n), Space:O(n)
 * 1. Have a map "index2Column" to store index-to-column pair
 * 2. Use BFS traverse all nodes with the index of verticalOrders, and visit left child first. 
 * 3. Therefore, we can guarantee the order which follows top > bottom and left > right
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
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private class IndexAndNode{
        int index;
        TreeNode node;
        IndexAndNode(int i, TreeNode n){index = i; node = n;}
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<List<Integer>>();
        }
        
        int[] range = new int[2];
        Map<Integer, List<Integer>> index2Column = new HashMap<>();
        Deque<IndexAndNode> queue = new LinkedList<IndexAndNode>();
        queue.add(new IndexAndNode(0, root));
        while(!queue.isEmpty()){
            IndexAndNode currNode =  queue.pollFirst();
            index2Column.computeIfAbsent(currNode.index, key -> new ArrayList<>()).add(currNode.node.val);
            range[0] = Math.min(range[0], currNode.index);
            range[1] = Math.max(range[1], currNode.index);
            if(currNode.node.left != null){
                queue.add(new IndexAndNode(currNode.index - 1, currNode.node.left));
            }
            if(currNode.node.right != null){
                queue.add(new IndexAndNode(currNode.index + 1, currNode.node.right));
            }
        }
        
        List<List<Integer>> columns = new ArrayList<>();
        for(int i = range[0]; i <= range[1]; ++i){
            columns.add(index2Column.get(i));
        }
        return columns;
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
        Solution sol = new Solution();
        System.out.println("vertical order traversal:" + sol.verticalOrder(root));
    }
}
