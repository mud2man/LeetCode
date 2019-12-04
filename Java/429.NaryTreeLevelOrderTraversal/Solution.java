/* DFS: Time:O(n), Space:O(h)
 * 1. Visit tree use left-child first order, and put the root.val into "levels"
 */

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;
    public Node() {}
    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};

public class Solution {
    private void dfs(int level, Node root, List<List<Integer>> levels){
        if(root == null){
            return;
        }
        
        if(levels.size() == level){
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(root.val);
        for(Node child: root.children){
            dfs(level + 1, child, levels);
        }
    }
    
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        dfs(0, root, levels);
        return levels;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        Node root = new Node(1, new ArrayList<>()); 
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, new ArrayList<>()));
        root.children.add(new Node(4, new ArrayList<>()));
        System.out.println("levels: " + sol.levelOrder(root));
    }
}
