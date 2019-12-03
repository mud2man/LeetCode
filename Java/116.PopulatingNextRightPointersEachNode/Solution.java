/* BFS: Time:O(n), Space:O(h)
 * 1. Call connect level by level
 * 2. Iterate and link all nodes on the same level
 */

import java.util.*; // Stack

/* Definition for binary tree */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class Solution {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }   
        
        for(Node itr = root; itr != null; itr = itr.next){
            Node predecesor = null; 
            if(itr.left != null && itr.right != null){
                itr.left.next = itr.right;
                predecesor = itr.right;
            }
            
            Node succesor = (itr.next != null && itr.next.left != null)? itr.next.left: null;
            if(predecesor != null){
                predecesor.next = succesor;
            }
        }
        connect(root.left);
        return root;
    }

     
    public void dumpByLevel(Node root) {
        Node curr;
        if(root == null){
            return;
        }
        
        curr = root;
        while(curr != null){
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println("");
        dumpByLevel(root.left);
    }

    public static void main(String[] args){
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         *  / \  / \
         * 4  5 6   7
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        Solution sol = new Solution();
        sol.connect(root);
        sol.dumpByLevel(root);
	}
}
