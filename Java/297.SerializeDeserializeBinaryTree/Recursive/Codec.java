/* Use preorder: TimeO(n), SpaceO(n)
 * 1. Serialize with preorder traversal, and denote Null pointer with string "#"
 * 2. Deserize by taking the first element of datas as node's value
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
    private void preOrderEncode(StringBuilder path, TreeNode node){
        if(node == null){
            path.append("#_");
            return;
        }
        path.append(Integer.toString(node.val) + "_");
        preOrderEncode(path, node.left);
        preOrderEncode(path, node.right);
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder path = new StringBuilder("");
        preOrderEncode(path, root);
        path.deleteCharAt(path.length() - 1);
        return path.toString();
    }
    
    private TreeNode preOrderDecode(String[] datas, int[] idx){
        String val = datas[idx[0]++];
        if(val.equals("#")){
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = preOrderDecode(datas, idx);
        node.right = preOrderDecode(datas, idx);
        return node;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] datas = data.split("_");
        int[] idx = {0};
        return preOrderDecode(datas, idx);
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
