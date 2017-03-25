/* O(hight)
 * 1. Search the node with key value
 * 2. Replace the value of the deleted node with its sucessor
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode 
{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode[] search(TreeNode[] nodes, int key){
        if(nodes[1] == null || key == nodes[1].val){
            return nodes;
        }
        else if(key < nodes[1].val){
            nodes[0] = nodes[1];
            nodes[1] = nodes[0].left;
            return search(nodes, key);
        }
        else{
            nodes[0] = nodes[1];
            nodes[1] = nodes[0].right;
            return search(nodes, key);
        }
    }
    
    public void swap(TreeNode[] nodes){
        if(nodes[1].left == null && nodes[1].right == null){
            if(nodes[0] != null){
                nodes[0].left = (nodes[0].left == nodes[1])? null: nodes[0].left;
                nodes[0].right = (nodes[0].right == nodes[1])? null: nodes[0].right;
                return;
            }
            else{
                nodes[1] = null;
                return;
            }
        }
        else if(nodes[1].right != null){
            if(nodes[1].right.left != null){
                nodes[1].val = nodes[1].right.left.val;
                nodes[0] = nodes[1].right;
                nodes[1] = nodes[1].right.left;
                swap(nodes);
            }
            else{
                nodes[1].val = nodes[1].right.val;
                nodes[0] = nodes[1];
                nodes[1] = nodes[1].right;
                swap(nodes);
            }
        }
        else{
            if(nodes[1].left.right != null){
                nodes[1].val = nodes[1].left.right.val;
                nodes[0] = nodes[1].left;
                nodes[1] = nodes[1].left.right;
                swap(nodes);
            }
            else{
                nodes[1].val = nodes[1].left.val;
                nodes[0] = nodes[1];
                nodes[1] = nodes[1].left;
                swap(nodes);
            }
        }
    }
    
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode[] nodes;
        
        nodes = new TreeNode[2];
        nodes[0] = null;
        nodes[1] = root;
        /* search */
        nodes = search(nodes, key);
        
        /* key not found, no need to change */
        if(nodes[1] == null){
            return root;
        }
        
        /* swap */
        swap(nodes);
        
        if(nodes[1] == null){
            return null;
        }
        
        return root; 
    }
    
    public void inorder(TreeNode root){
        if(root == null){
            return;
        }
        else{
            inorder(root.left);
            System.out.print(root.val + ", ");
            inorder(root.right);
        }
    }
 
    public static void main(String[] args)
    {
        List<List<Integer>> list;
        TreeNode root;
		Solution sol;
		int key;
        
		/* Generate a input tree
		 *     3
		 *    / \
		 *   9   20
		 *       / \
		 *      15  7
		 */
		root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		key = 20;
		sol = new Solution();
        
        System.out.println("before delete " + key);
        sol.inorder(root);
        System.out.println("");
		
        root = sol.deleteNode(root, key);
        
        System.out.println("after delete " + key);
        sol.inorder(root);
        System.out.println("");
	}
}
