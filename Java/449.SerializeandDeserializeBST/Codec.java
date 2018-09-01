/* Use preorder: O(nlgn) in average, but O(n^2) in worst case. You can use the solution of LC#297 to get an O(n) solution
 * 1. Use preorder to serialize
 * 2. Translate string to array of integer and deserialize
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
    private void preOrderEncode(StringBuilder preOrderString, TreeNode root){
        if(root == null){
            return;
        }
        preOrderString.append(",");
        preOrderString.append(root.val);
        preOrderEncode(preOrderString, root.left);
        preOrderEncode(preOrderString, root.right);
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder data = new StringBuilder("");
        preOrderEncode(data, root);
        return (data.length() > 0)? data.toString().substring(1, data.length()): "";
    }

    private TreeNode preOrderDecode(int[] nodeNums, int startIdx, int endIdx){
        if(startIdx > endIdx){
            return null;
        }
        
        int rootVal = nodeNums[startIdx];
        TreeNode root = new TreeNode(rootVal);
        int rightRootIdx = startIdx + 1;
        while(rightRootIdx <= endIdx && nodeNums[rightRootIdx] < rootVal){
            rightRootIdx++;
        }
        
        root.left = preOrderDecode(nodeNums, startIdx + 1, rightRootIdx - 1);
        root.right = preOrderDecode(nodeNums, rightRootIdx, endIdx);
        
        return root;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0){
            return null;
        }
        
        String[] nodeStrings =  data.split(",");
        int[] nodeNums = new int[nodeStrings.length];
        for(int i = 0; i < nodeStrings.length; ++i){
            nodeNums[i] = Integer.parseInt(nodeStrings[i]);
        }
        TreeNode root = preOrderDecode(nodeNums, 0, nodeNums.length - 1);
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
