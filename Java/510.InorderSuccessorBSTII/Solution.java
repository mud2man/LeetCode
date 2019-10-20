/* Tree: Time:O(h), Space:O(1)
 * 1. Get the leftest node if x has right sub-tree
 * 2. Get the first paraent, which's left child is x
 */

import java.util.*; // Stack

/* Definition for binary tree */
class Node {
    int val;
    Node left;
    Node right;
    Node parent;
    Node(int x) { val = x; }
}

public class Solution {
    public Node inorderSuccessor(Node x) {
        if(x == null){
            return null;
        }else if(x.right != null){
            x = x.right;
            while(x.left != null){
                x = x.left;
            }
            return x;
        }else{
            while(x.parent != null && x.parent.left != x){
                x = x.parent;
            }
            return x.parent;
        }
    }

    public static void main(String[] args){
        /* Generate a input tree
         *    2
         *   / \
         *  1   3
         */
        Solution sol = new Solution();
        Node root = new Node(2);
        root.left = new Node(1);
        root.left.parent = root;
        root.right = new Node(3);
        root.right.parent = root;
        System.out.println("parent of 1: " + sol.inorderSuccessor(root.left).val);
    }
}
