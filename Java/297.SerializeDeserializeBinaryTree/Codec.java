/* Preorder + stack: O(n)
 * 1. Serialize with preorder traversal, and denote Null pointer with string "null"
 * 2. Retrieve tree node by visiting "data" from the 0-th position, and use stack to record the path
 * 3. Append node by if top.left is null, and if the current visited node is null
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
        while(index < preOrderValues.length){
            String nodeString = preOrderValues[index++];
            if(!nodeString.equals("null")){
                TreeNode top = stack.peek();
                //if top.left == null, append its top's left. Otherwise append its right
                if(top.left == null){
                    top.left = new TreeNode(Integer.parseInt(nodeString));
                    stack.push(top.left);
                }
                else{
                    top.right = new TreeNode(Integer.parseInt(nodeString));
                    stack.pop();
                    stack.push(top.right);
                }
            }
            else{
                //if top's left != null, which means the current visiting "null" is its right. So skip this step
                if(stack.peek().left != null){
                    stack.pop();
                    continue;
                }
                
                //check if the top's right is null by visit the next element
                nodeString = preOrderValues[index++];
                if(!nodeString.equals("null")){
                    TreeNode top = stack.pop();
                    top.right = new TreeNode(Integer.parseInt(nodeString));
                    stack.push(top.right);
                }
                else{
                    stack.pop();
                }
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
