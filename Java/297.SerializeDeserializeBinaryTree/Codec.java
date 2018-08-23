/* Preorder + stack: Time:O(n), Space:O(n)
 * 1. Serialize with preorder traversal, and denote Null pointer with string "null"
 * 2. Have a variable" top" to store the actual top node, and the virtual stack is (stack + top)
 * 2. Retrieve tree node by visiting "data" from the 0-th position
 * 3. push null or newNode into virtual stack (stack + top)
 * 4. pop stack if the top 2 element of virtual stack are null
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
        
        TreeNode root = new TreeNode(Integer.parseInt(preOrderValues[0]));
        TreeNode top = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int index = 1;
        while(index < preOrderValues.length){
            String nodeString = preOrderValues[index++];
            //push null or newNode into virtual stack (stack + top)
            if(nodeString.equals("null")){
                stack.push(top);
                top = null;
            }
            else{
                TreeNode newNode = new TreeNode(Integer.parseInt(nodeString));
                if(top == null){
                    stack.peek().right = newNode;
                }
                else{
                    top.left = newNode;
                }
                stack.push(top);
                top = newNode;
            }
            
            //pop stack if the top 2 element of virtual stack are null
            while(top == null && stack.size() > 1 && stack.peek() == null){
                stack.pop();
                stack.pop();
            }
        }
        
        return root;
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
