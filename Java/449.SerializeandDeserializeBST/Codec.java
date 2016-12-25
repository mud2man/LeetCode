/* Use preorder: O(nlgn) in average, but O(n^2) in worst case
 * 1. Use preorder to serialize
 * 2. Translate string to arrayList and deserialize
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

public class Codec {
    
    public String preOrderWalker(TreeNode root, String preOrder){
        if(root == null){
            return preOrder;
        }
        
        preOrder = preOrder.concat(String.valueOf(root.val));
        preOrder = preOrder.concat(",");
        preOrder = preOrderWalker(root.left, preOrder);
        preOrder = preOrderWalker(root.right, preOrder);
        
        return preOrder;
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String preOrder = "";
        
        if(root == null){
            return preOrder;
        }
        
        preOrder = preOrderWalker(root, preOrder);
        preOrder = preOrder.substring(0, preOrder.length() - 1);
        
        return preOrder;
    }
    
    public TreeNode treeGen(ArrayList<Integer> preOrder, int start, int end){
        TreeNode root;
        int leftStart;
        int rightStart;
        
        root = new TreeNode(preOrder.get(start));
        root.left = null;
        root.right = null;
        
        if(end > start){
            rightStart = start + 1;
            while((rightStart <= end) && (preOrder.get(rightStart) < root.val)){
                rightStart++;
            }
            
            if((rightStart - (start + 1)) > 0){
                root.left =  treeGen(preOrder, start + 1, rightStart - 1);
            }
            
            if(rightStart <= end){
                root.right =  treeGen(preOrder, rightStart, end);
            }
        }
        
        return root;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        TreeNode root;
        ArrayList<Integer> preOrder;
        
        root = null;
        preOrder = new ArrayList<Integer>();
        
        if(data.length() == 0){
            return root;
        }
        
        for (String retval: data.split(",")) {
            preOrder.add(Integer.parseInt(retval));
        }
        
        if(preOrder.size() > 0){
            root = treeGen(preOrder, 0, preOrder.size() - 1);
        }
        
        return root;
    }
    
    public static void main(String[] args)
    {
        TreeNode root;
		Codec codec;
        
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

		codec = new Codec();
		codec.deserialize(codec.serialize(root));	
	}
}
