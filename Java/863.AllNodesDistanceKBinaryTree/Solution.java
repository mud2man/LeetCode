/* DFS + BFS: Time:O(n), Space:O(n)
 * 1. Have a map "parent" to store all the parent of all nodes
 * 2. Apply BFS starting from target, to find all the K-distance nodes
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
    private void dfs(TreeNode root, Map<TreeNode, TreeNode> parent){
        if(root.left != null){
            parent.put(root.left, root);
            dfs(root.left, parent);
        }
        if(root.right != null){
            parent.put(root.right, root);
            dfs(root.right, parent);
        }
    }
    
    private void bfs(TreeNode target, int K, Map<TreeNode, TreeNode> parent, List<Integer> ret){
        int dis = 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(target);
        Set<TreeNode> visted = new HashSet<TreeNode>();
        while(!queue.isEmpty()){
            int size = queue.size();
            if(dis == K){
                for(TreeNode n: queue){
                    ret.add(n.val);
                }
                return;
            }
            for(int i = 0; i < size; ++i){
                TreeNode curr = queue.poll();
                visted.add(curr);
                if(parent.containsKey(curr) && !visted.contains(parent.get(curr))){
                    queue.add(parent.get(curr));
                }
                if(curr.left != null && !visted.contains(curr.left)){
                    queue.add(curr.left);
                }
                if(curr.right != null && !visted.contains(curr.right)){
                    queue.add(curr.right);
                }
            }
            dis++;
        }
    }
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>();
        dfs(root, parent);
        List<Integer> ret = new ArrayList<Integer>();
        bfs(target, K, parent, ret);
        return ret;
    }
 
    public static void main(String[] args){
        TreeNode root;
        
        /* Generate a input tree
         *     3
         *    / \
         *   5   1
         *  / \   \
         * 6   2   8
         *    / \  
         *   7   4  
         */
        root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        Solution sol = new Solution();
        int K = 2;
        System.out.println("K: " + K);
        System.out.println("target: " + root.left.val);
        System.out.println("nodes distance 2: " + sol.distanceK(root, root.left, K));
    }
}
