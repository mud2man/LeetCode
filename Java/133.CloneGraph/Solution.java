/* DFS + HashMap: O(n)
 * 1. Use DFS to visit every node, and clone all the neighbors
 * 2. If the neighbor is in the HashMap "cloneTbl", which mean the neighbor was cloned. 
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
    void helper(UndirectedGraphNode node, UndirectedGraphNode cloneRoot, HashMap<Integer, UndirectedGraphNode> cloneTbl){
        UndirectedGraphNode cloneNeighbor;
        
        for(UndirectedGraphNode neighbor: node.neighbors){
            if(cloneTbl.containsKey(neighbor.label)){
                cloneRoot.neighbors.add(cloneTbl.get(neighbor.label)); 
            }
            else{
                cloneNeighbor = new UndirectedGraphNode(neighbor.label);
                cloneRoot.neighbors.add(cloneNeighbor);
                cloneTbl.put(cloneNeighbor.label, cloneNeighbor);
                helper(neighbor, cloneNeighbor, cloneTbl);
            }
        }  
    }
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<Integer, UndirectedGraphNode> cloneTbl;
        UndirectedGraphNode cloneRoot;
        
        if(node == null){
            return null;
        }
        
        cloneTbl = new HashMap<Integer, UndirectedGraphNode>();
        cloneRoot = new UndirectedGraphNode(node.label);
        cloneTbl.put(node.label, cloneRoot);
        
        helper(node, cloneRoot, cloneTbl);
        
        return cloneRoot;
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
