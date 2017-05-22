/* BFS: O(n)
 * 1. Call connect level by level, and classify the following cases
 * 2. case0: the node has both left and right, link left and right, and let "lowePrev.next"="curr.left", "lowePrev"="curr.right"
 * 3. case1: the node has only left, let "lowePrev.next"="curr.left", "lowePrev"="curr.left"
 * 2. case2: the node has only right, let "lowePrev.next"="curr.right", "lowePrev"="curr.right"
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
        TreeLinkNode curr, lowePrev, nextRoot;
        
        if(root == null){
            return;
        }
        
        curr = root;
        lowePrev = null;
        nextRoot = null;
        while(curr != null){
            // case 0: having left and right
            if(curr.left != null && curr.right != null){
                if(lowePrev != null){
                    lowePrev.next = curr.left;
                }
                
                curr.left.next = curr.right;
                lowePrev = curr.right;
                
                if(nextRoot == null){
                    nextRoot = curr.left;
                }
            }
            // case 1: only having left
            else if(curr.left != null && curr.right == null){
                if(lowePrev != null){
                    lowePrev.next = curr.left;
                }
                
                lowePrev = curr.left;
                
                if(nextRoot == null){
                    nextRoot = curr.left;
                }
            }
            // case 2: only having right
            else if(curr.left == null && curr.right != null){
                if(lowePrev != null){
                    lowePrev.next = curr.right;
                }
                
                lowePrev = curr.right;
                
                if(nextRoot == null){
                    nextRoot = curr.right;
                }
            }
            curr = curr.next;
        }
        connect(nextRoot);
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
         *  / \   \
         * 4   5   7
         */
        root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);
        root.right.right = new TreeLinkNode(7);

        sol = new Solution();

        sol.connect(root);
        sol.dumpByLevel(root);
	}
}
