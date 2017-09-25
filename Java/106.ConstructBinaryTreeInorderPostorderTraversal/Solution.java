/* HashMap and Recursive: O(n) time + O(n) space
 * 1. Have a hashmap to record the position of elements if inOrder
 * 2. In build method, get the root value from the last element of postorder
 * 3. Get the number of nodes in left tree by position hashmap
 * 4. Get root.left and root.right from build as well
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private HashMap<Integer, Integer> positionMap;
    
    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
        if(inEnd < inStart){
            return null;
        }
        
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        
        int leftNodeCount = positionMap.get(rootVal) - inStart;
        
        root.left = build(inorder, inStart, inStart + leftNodeCount - 1, postorder, postStart, postStart + leftNodeCount - 1);
        root.right = build(inorder, inStart + leftNodeCount + 1, inEnd, postorder, postStart + leftNodeCount, postEnd - 1);
        
        return root;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int size = inorder.length;
        this.positionMap = new HashMap<Integer, Integer>();
        for(int index = 0; index < size; ++index){
            positionMap.put(inorder[index], index);
        }
        return build(inorder, 0, size - 1, postorder, 0, size - 1);
    }

    private void preOrder(TreeNode root){
        if(root == null){
            return;
        }

        System.out.print(root.val + ", ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args){
        Solution sol;
        int[] inorder = {4, 2, 5, 1, 3};
        int[] postorder = {4, 5, 2, 3, 1};
         
        System.out.println("inorder:" + Arrays.toString(inorder));
        System.out.println("postorder:" + Arrays.toString(postorder));

        sol = new Solution();
        System.out.println("after construct:");
        TreeNode root = sol.buildTree(inorder, postorder);
        sol.preOrder(root);
        System.out.println("");
    }
}
