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
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "#,";
        }
        
        String s = "";
        s += (Integer.toString(root.val) + ",");
        s += serialize(root.left);
        s += serialize(root.right);
        return s;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nums = data.split(",");
        System.out.println(data);
        System.out.println(Arrays.toString(nums));
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode top = new TreeNode(0);
        for(String num: nums){
            if(num.equals("#")){
                stack.add(top);
                top = null;
                if(!stack.isEmpty() && stack.peekLast() == null){
                    stack.pollLast();
                    stack.pollLast();
                }
            }
            else{
                TreeNode newNode = new TreeNode(Integer.valueOf(num));
                if(top == null){
                    top = stack.pollLast();
                    top.right = newNode;
                }
                else{
                    top.left = newNode;
                    stack.add(top);
                }
                top = newNode;
            }
        }
        return stack.peekLast().left;
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
