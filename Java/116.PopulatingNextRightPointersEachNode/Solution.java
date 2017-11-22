/* BFS: O(n)
 * 1. Call connect level by level
 * 2. Iterate and link all nodes on the same level
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeLinkNode  
{
	int val;
	TreeLinkNode left, right, next;
	TreeLinkNode(int x) { val = x; }
}

public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null){
            return;
        }   
        
        for(TreeLinkNode itr = root; itr != null; itr = itr.next){
            TreeLinkNode predecesor = null; 
            if(itr.left != null && itr.right != null){
                itr.left.next = itr.right;
                predecesor = itr.right;
            }
            
            TreeLinkNode succesor = (itr.next != null && itr.next.left != null)? itr.next.left: null;
            if(predecesor != null){
                predecesor.next = succesor;
            }
        }
        
        connect(root.left);
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

    public static void main(String[] args)
    {
        TreeLinkNode root;
        Solution sol;
 
        /* Generate a input tree
         *     1
         *    / \
         *   2   3
         *  / \  / \
         * 4  5 6   7
         */
        root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);
        root.right.left = new TreeLinkNode(6);
        root.right.right = new TreeLinkNode(7);

        sol = new Solution();

        sol.connect(root);
        sol.dumpByLevel(root);
	}
}
