/* Map + DFS: Time:O(nlogn), Space:O(n)
 * 1. Use "map" to store the position and its value
 * 2. Retrieve the list from map, and do sorting before adding to the list
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
    private void dfs(TreeNode root, int x, int y, Map<Integer, Map<Integer, List<Integer>>> map, int[] range){
        if(root == null){
            return;
        }
        map.putIfAbsent(x, new HashMap<>());
        map.get(x).putIfAbsent(y, new ArrayList<>());
        map.get(x).get(y).add(root.val);
        range[0] = (x < range[0])? x: range[0];
        range[1] = (x > range[1])? x: range[1];
        range[2] = (y > range[2])? y: range[2];
        dfs(root.left, x - 1, y + 1, map, range);
        dfs(root.right, x + 1, y + 1, map, range);
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, Map<Integer, List<Integer>>> map = new HashMap<>();
        int[] range = {0, 0, 0}; //left, right, depth
        dfs(root, 0, 0, map, range);
        List<List<Integer>> verticals = new ArrayList<>();
        for(int x = range[0]; x <= range[1]; ++x){
            Map<Integer, List<Integer>> subMap = map.get(x);
            List<Integer> vertical = new ArrayList<>();
            for(int y = 0; y <= range[2]; ++y){
                if(subMap.containsKey(y)){
                    List<Integer> temp = subMap.get(y);
                    Collections.sort(temp);
                    vertical.addAll(temp);
                }
            }
            verticals.add(vertical);
        }
        return verticals;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     4
         *    / \
         *   2   5
         *  / \   
         * 1   3  
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println("vertical: " + sol.verticalTraversal(root));
    }
}
