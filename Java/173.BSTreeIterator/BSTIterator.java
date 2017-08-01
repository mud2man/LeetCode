/* Stack: O(1) 
 * 1. Use root as an input, and push all the elements along the left offspring to "stack"
 * 2. If stack is not empty, pop the top elemnet as the next smallest number
 * 3. After poping out, use the right child as an input, and push all the left childs again
 * 4. If the stack is empty, there is no more next smallest number
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BSTIterator 
{
    /* Store the elements along left offsprings */
    Stack<TreeNode> stack = new Stack<TreeNode>();
    
    private void pushLeft(TreeNode root){
        if(root != null){
            stack.push(root);
            pushLeft(root.left);
        }
    }

    BSTIterator(TreeNode root){
        pushLeft(root);
    }

    /* Utilities function */
    public void pushLefts(TreeNode root){
        if(root != null){
            stack.push(root);
            pushLefts(root.left);
        }
    }

    /* Return whether we have a next smallest number */
    public boolean hasNext(){
        return !stack.empty();
    }

    /* Return the next smallest number */
    public int next(){
        TreeNode top = stack.pop();
        pushLeft(top.right);
        return top.val;
    }
    
    public static void main(String[] args){
        TreeNode root;
        BSTIterator itr;
        
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \  /
         *   4   7 13 
         */
        root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(13);
        
        itr = new BSTIterator(root);
        
        while (itr.hasNext())
        {
            System.out.println("Next smallest number: " + itr.next());
        }
    }
}
