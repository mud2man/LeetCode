/* DFS + HashMap: O(n)
 * 1. Use DFS to visit every node, and clone all the neighbors
 * 2. If the neighbor is in the HashMap "map", which mean the neighbor was cloned. 
 * 3. Then just add the object of the neighbor into the node's neighbor list
 */

import java.util.*;

/* Definition for binary tree */

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};

public class Solution {
    public UndirectedGraphNode helper(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode>map){
        if(map.containsKey(node)){
            return map.get(node);
        }
        
        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        map.put(node, cloneNode);
        for(UndirectedGraphNode neighbor: node.neighbors){
            cloneNode.neighbors.add(helper(neighbor, map)); 
        }
        
        return cloneNode;
    }
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node != null)
            return helper(node, new HashMap<UndirectedGraphNode, UndirectedGraphNode>());
        else
            return null;
    }

    public static void main(String[] args){
        UndirectedGraphNode node0, node1, node2;
        UndirectedGraphNode cloneRoot;
        Solution sol;

        /* Generate a graph
         *       1
         *      / \
         *     /   \
         *    0-----2
         *         / \
         *         \-/
         */
        
        node0 = new UndirectedGraphNode(0);
        node1 = new UndirectedGraphNode(1);
        node2 = new UndirectedGraphNode(2);
        node0.neighbors.add(node1);
        node0.neighbors.add(node2);
        node1.neighbors.add(node0);
        node1.neighbors.add(node2);
        node2.neighbors.add(node0);
        node2.neighbors.add(node1);
        node2.neighbors.add(node2);

        sol = new Solution();
        
        cloneRoot = sol.cloneGraph(node0);
        
        System.out.println("No usual debug message");
    }
}
