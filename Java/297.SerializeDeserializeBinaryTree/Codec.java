/* Use preorder: O(n), However Leetcode seems has beter solution, since this solution only beats 3%
 * 1. Replace the value in the original tree with the continuous integer as Id starting from 0, and have a map storing id-value 
 * 2. Serization has three components preOrder, inOrder, idValueMap
 * 3. Have a value-position map for inOrder array
 * 4. Deserialize the tree with inorder, preorder
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
            return;
        }
        preOrderStr.append(Integer.toString(root.val));
        preOrderStr.append(',');
        preOrder(root.left, preOrderStr);
        preOrder(root.right, preOrderStr);
    }
    
    private void inOrder(TreeNode root, StringBuilder inOrderStr){
        if(root == null){
            return;
        }
        inOrder(root.left, inOrderStr);
        inOrderStr.append(Integer.toString(root.val));
        inOrderStr.append(',');
        inOrder(root.right, inOrderStr);
    }
    
    private void buildMap(TreeNode root, HashMap<Integer, Integer> map, int[] count){
        if(root == null){
            return;
        }
        buildMap(root.left, map, count);
        int val = root.val;
        int id = count[0];
        map.put(id, val);
        count[0]++;
        root.val = id;
        buildMap(root.right, map, count);
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count[] = new int[1];
        buildMap(root, map, count);
        
        StringBuilder preOrderStr = new StringBuilder("");
        StringBuilder inOrderStr = new StringBuilder("");
        preOrder(root, preOrderStr);
        inOrder(root, inOrderStr);
        
        String mapStr = "";
        for(int i = 0; i < count[0]; ++i){
            int val = map.get(i);
            mapStr = mapStr + Integer.toString(val) + ",";
        }
        
        String data = preOrderStr.toString() + "#" + inOrderStr.toString() + "#" + mapStr;
        return data;
    }
    
    private int[] getIntegers(String[] strings){
        int[] integers = (strings[0].length() == 0)? new int[0]: new int[strings.length];
        for(int i = 0; i < integers.length; ++i){
            integers[i] = Integer.parseInt(strings[i]);
        }
        return integers;
    }
    
    private TreeNode treeBuilder(int[] preOrder, int preOrderStart, int preOrderEnd,
                                 int[] inOrder, int inOrderStart, int inOrderEnd, 
                                 int[] idValueMap, int[] positionMap){
        if(preOrderStart > preOrderEnd){
            return null;
        }
    
        int rootId = preOrder[preOrderStart];
        int rootVal = idValueMap[rootId];
        TreeNode root = new TreeNode(rootVal);
        
        // caculate the number of left sub-tree nodes
        int leftTreeNodeCount = positionMap[rootId] - inOrderStart;
        
        //build left sub-tree
        int lPreOrderStart = preOrderStart + 1;
        int lPreOrderEnd = lPreOrderStart + leftTreeNodeCount - 1;
        int lInOrderStart = inOrderStart;
        int lInOrderEnd = lInOrderStart + leftTreeNodeCount - 1;
        root.left = treeBuilder(preOrder, lPreOrderStart, lPreOrderEnd, inOrder, lInOrderStart, lInOrderEnd, idValueMap, positionMap);
        
        //build right sub-tree
        int rPreOrderStart = lPreOrderEnd + 1;
        int rPreOrderEnd = preOrderEnd;
        int rInOrderStart = lInOrderEnd + 2;
        int rInOrderEnd = inOrderEnd;
        root.right = treeBuilder(preOrder, rPreOrderStart, rPreOrderEnd, inOrder, rInOrderStart, rInOrderEnd, idValueMap, positionMap);
        
        return root;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        int seperatorIndex0 = data.indexOf('#');
        int seperatorIndex1 = data.indexOf('#', seperatorIndex0 + 1);
        String preOrderStr = data.substring(0, seperatorIndex0);
        String inOrderStr = data.substring(seperatorIndex0 + 1, seperatorIndex1);
        String mapStr = data.substring(seperatorIndex1 + 1, data.length());
        
        int[] preOrderIntegers = getIntegers(preOrderStr.split(","));
        int[] inOrderIntegers = getIntegers(inOrderStr.split(","));
        int[] idValueMap = getIntegers(mapStr.split(","));
        int size = preOrderIntegers.length;
        int[] positionMap = new int[size];
        for(int i = 0; i < size; ++i){
            positionMap[inOrderIntegers[i]] = i;
        }
        
        TreeNode root = treeBuilder(preOrderIntegers, 0, size - 1, inOrderIntegers, 0 , size - 1, idValueMap, positionMap);
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
