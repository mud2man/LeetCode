/* Hashmap: Time:O(n), Space:O(n)
 */

import java.util.*; // Stack

class Node {
    int val;
    Node left;
    Node right;
    Node random;
    Node() {}
    Node(int val) { this.val = val; }
    Node(int val, Node left, Node right, Node random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
}

class NodeCopy {
    int val;
    NodeCopy left;
    NodeCopy right;
    NodeCopy random;
    NodeCopy() {}
    NodeCopy(int val) { this.val = val; }
}

public class Solution {
    private NodeCopy copy(Node node, Map<Node, NodeCopy> original2Copy, Map<NodeCopy, Node> copy2Original){
        if(node == null){
            return null;
        }
        NodeCopy nodeCopy = new NodeCopy(node.val);
        nodeCopy.left = copy(node.left, original2Copy, copy2Original);
        nodeCopy.right = copy(node.right, original2Copy, copy2Original);
        copy2Original.put(nodeCopy, node);
        original2Copy.put(node, nodeCopy);
        return nodeCopy;
    }
    
    private void assignRand(NodeCopy nodeCopy, Map<Node, NodeCopy> original2Copy, Map<NodeCopy, Node> copy2Original){
        if(nodeCopy == null){
            return;
        }
        nodeCopy.random = original2Copy.get(copy2Original.get(nodeCopy).random);
        assignRand(nodeCopy.left, original2Copy, copy2Original);
        assignRand(nodeCopy.right, original2Copy, copy2Original);
    }
    
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> original2Copy = new HashMap<>();
        Map<NodeCopy, Node> copy2Original = new HashMap<>();
        NodeCopy rootCopy = copy(root, original2Copy, copy2Original);
        assignRand(rootCopy, original2Copy, copy2Original);
        return rootCopy;
    }
  
    public static void main(String[] args){
        System.out.println("no example");
    }
}
