/* Recursive: O(n)
 * 1. When enode, we put the next level to treeNode's left, and put its siblings to its right
 * 2. When decode, we keep "parent" pointer. We change the "parent" to newNode if we go to left, otherwise keep "parent" unchange since "right" is sibling
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Node {
    public int val;
    public List<Node> children;
    public Node() {}
    public Node(int _val,List<Node> _children) {val = _val; children = _children;}
};


public class Codec {
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if(root == null){
            return null;
        }
        TreeNode newNode = new TreeNode(root.val);
        TreeNode parent = newNode;
        List<Node> children = root.children;
        for(Node node: children){
            TreeNode subTree = encode(node);
            if(parent == newNode){
                parent.left = subTree;
            }else{
                parent.right = subTree;
            }
            parent = subTree;
        }
        return newNode;
    }
    
    private Node decodeHelper(TreeNode root, Node parent){
        if(root == null){
            return null;
        }
        Node newNode = new Node(root.val, new ArrayList<>());
        parent.children.add(newNode);
        decodeHelper(root.left, newNode);
        decodeHelper(root.right, parent);
        return newNode;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        Node dummy = new Node(0, new ArrayList<>());
        return decodeHelper(root, dummy);
    }
 
    private void preOrder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.val + ", ");
        for(Node child: root.children){
            preOrder(child);
        }
    }

    public static void main(String[] args){
        /* Generate a input tree
         *     1----
         *    / \   \
         *   3   2   4
         *  / \ 
         * 5   6
         */
        Node root = new Node(1, new ArrayList<>());
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, new ArrayList<>()));
        root.children.add(new Node(4, new ArrayList<>()));
        root.children.get(0).children.add(new Node(5, new ArrayList<>()));
        root.children.get(0).children.add(new Node(6, new ArrayList<>()));
        Codec sol = new Codec();

        System.out.println("Before encode:");
        sol.preOrder(root); 
        System.out.println("");
        TreeNode encodeRoot = sol.encode(root);
        System.out.println("After encode and decode:");
        sol.preOrder(sol.decode(encodeRoot)); 
        System.out.println("");
    }
}
