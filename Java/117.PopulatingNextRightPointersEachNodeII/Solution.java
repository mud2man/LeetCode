/* BFS: Time:O(n), Space:O(1)
 * 1. Connect next level by traversing current level, and move nextLevelPtr by 3 cases
 * 2. case0: the node has both left and right, link left and right, and update nextLevelPtr with ptr.right
 * 3. case1: the node has only left, update nextLevelPtr with ptr.left
 * 4. case2: the node has only right, update nextLevelPtr with ptr.right
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeLinkNode  {
	int val;
	TreeLinkNode left, right, next;
	TreeLinkNode(int x) { val = x; }
}

public class Solution {
    public Node connect(Node root) {
        Node front = root;
        while(front != null){
            Node nextLevelPtr = new Node();
            Node ptr = front;
            Node nextLevelFront = null;
            while(ptr != null){
                if(ptr.left != null || ptr.right != null){
                    if(ptr.left != null && ptr.right != null){
                        ptr.left.next = ptr.right;
                        nextLevelFront = (nextLevelFront == null)? ptr.left: nextLevelFront;
                        nextLevelPtr.next = ptr.left;
                        nextLevelPtr = ptr.right;
                    }
                    else if(ptr.left != null){
                        nextLevelFront = (nextLevelFront == null)? ptr.left: nextLevelFront;
                        nextLevelPtr.next = ptr.left;
                        nextLevelPtr = ptr.left;
                    }
                    else{
                        nextLevelFront = (nextLevelFront == null)? ptr.right: nextLevelFront;
                        nextLevelPtr.next = ptr.right;
                        nextLevelPtr = ptr.right;
                    }
                }
                ptr = ptr.next;
            }
            front = nextLevelFront;
        }
        return root;
    }

    public void dumpByLevel(TreeLinkNode root) {
        TreeLinkNode curr;
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

    public static void main(String[] args) {
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         *  / \   \
         * 4   5   7
         */
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);
        root.right.right = new TreeLinkNode(7);
        Solution sol = new Solution();
        sol.connect(root);
        sol.dumpByLevel(root);
	}
}
