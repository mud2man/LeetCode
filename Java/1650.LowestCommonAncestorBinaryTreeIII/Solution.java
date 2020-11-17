/* Two pointers: Time:O(h), Space:O(1)
 * 1. Use the same logic of LeetCode#160, since the the distance of reaching LCA on second time is the same
 */

import java.util.*; // Stack

/* Definition for binary tree */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
    public Node(int v){val = v;}
}

public class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node ptr0 = p;
        Node ptr1 = q;
        while(ptr0 != ptr1) {
            ptr0 =(ptr0.parent == null)? q: ptr0.parent;
            ptr1 =(ptr1.parent == null)? p: ptr1.parent;
        }
        return ptr0;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     5
         *    / \
         *   2   -5
         */
        Node root = new Node(5);
        root.left = new Node(2);
        root.left.parent = root;
        root.right = new Node(-5);
        root.right.parent = root;

        Node p = root.left;
        Node q = root.right;
        System.out.println("LCA of " + p.val + " and " + q.val + " is:" + sol.lowestCommonAncestor(p, q).val );
    }
}
