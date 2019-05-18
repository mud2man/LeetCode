/* Use preorder: TimeO(n), SpaceO(n)
 * 1. Serialize with preorder traversal, and denote Null pointer with string "#"
 * 2. Have a variable" top" to store the actual top node, and the virtual stack is (stack + top)
 * 3. Retrieve tree node by visiting "data" from the 0-th position
 * 4. push null or newNode into virtual stack (stack + top)
 * 5. pop stack if the top 2 element of virtual stack are null
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
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder preOrderStr = new StringBuilder("");
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode dummy = new TreeNode(0);
        dummy.right = root;
        stack.add(dummy);
        while(!stack.isEmpty()){
            TreeNode node = stack.pollLast();
            if(node.right == null){
                preOrderStr.append("#_");
            }else{
                node = node.right;
                while(node != null){
                    stack.add(node);
                    preOrderStr.append(Integer.toString(node.val) + "_");
                    node = node.left;
                }
                preOrderStr.append("#_");
            }
        }
        return preOrderStr.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] preOrderValues = data.split("_");
        TreeNode root = preOrderValues[0].equals("#")? null: new TreeNode(Integer.parseInt(preOrderValues[0]));
        TreeNode top = root;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        for(int index = 1; index < preOrderValues.length; ++index){
            String nodeString = preOrderValues[index];
            //push null or newNode into virtual stack (stack + top)
            if(nodeString.equals("#")){
                if(top == null){
                    stack.pollLast();
                }else{
                    stack.add(top);
                    top = null;
                }
            }
            else{
                TreeNode newNode = new TreeNode(Integer.parseInt(nodeString));
                if(top == null){
                    stack.pollLast().right = newNode;
                    top = newNode;
                }else{
                    top.left = newNode;
                    stack.add(top);
                    top = newNode;
                }
            }
        }
        return root;
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     8
         *    / \
         *   3   10
         *  / \   \
         * 1   6   14
         *    / \  /
         *   4   7 13 
         */
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(13);
        Codec codec = new Codec();
        codec.deserialize(codec.serialize(root));    
    }
}
