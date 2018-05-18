/* Preorder + stack: O(n)
 * 1. Serialize with preorder traversal, and denote Null pointer with string "null"
 * 2. Retrieve tree node by visiting "data" from the 0-th position, and use stack to record the path
 * 3. Have a variable" temp" to store the latest poped node
 * 4. If temp != null, append the "newNode" to its right. Otherwise, append it to stack.peek().left, then reset temp
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Codec {
    private void preOrder(TreeNode root, StringBuilder preOrderStr){
        if(root == null){
            preOrderStr.append("null");
            preOrderStr.append(',');
            return;
        }
        preOrderStr.append(Integer.toString(root.val));
        preOrderStr.append(',');
        preOrder(root.left, preOrderStr);
        preOrder(root.right, preOrderStr);
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder preOrderStr = new StringBuilder("");
        preOrder(root, preOrderStr);
        return preOrderStr.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] preOrderValues = data.split(",");

        if(preOrderValues[0].equals("null")){
            return null;
        }
        
        TreeNode dummy = new TreeNode(0);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(dummy);
        int index = 0;
        TreeNode temp = null;
        while(index < preOrderValues.length){
            String nodeString = preOrderValues[index++];
            if(nodeString.equals("null")){
                temp = stack.pop();
            }
            else{
                TreeNode newNode = new TreeNode(Integer.parseInt(nodeString));
                if(temp != null){
                    temp.right = newNode;
                }
                else{
                    stack.peek().left = newNode;
                }
                temp = null;
                stack.push(newNode);
            }
        }
        
        return dummy.left;
    }

    public static void main(String[] args){
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
