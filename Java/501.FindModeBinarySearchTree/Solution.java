/* Inorder: O(n)
 * 1. Traverse all nodes using inorder traversal
 * 2. Keep remembering the previous node, and update the current frequency and maximum frequency
 * 3. Update modes, when current frequency is bigger than maximum frequency
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
    private TreeNode previousNode = null;
    private int maxFrequency = 0;
    private int currFrequency = 0;
    
    private void inorder(TreeNode root, List<Integer> modes){
        if(root == null){
            return;
        }
        
        inorder(root.left, modes); 
        if(previousNode == null){
            currFrequency = 1;
            maxFrequency = 1;
        }
        else if(previousNode.val == root.val){
            currFrequency++;
        }
        else{
            currFrequency = 1;
        }
        
        if(currFrequency > maxFrequency){
            modes.clear();
            modes.add(root.val);
            maxFrequency = currFrequency;
        }
        else if(currFrequency == maxFrequency){
            modes.add(root.val);
        }
        previousNode = root;
        inorder(root.right, modes);
    }
    
    public int[] findMode(TreeNode root) {
        List<Integer> modes = new ArrayList<Integer>();
        inorder(root, modes);
        int[] ret = new int[modes.size()];
        for(int i = 0; i < ret.length; ++i){ ret[i] = modes.get(i);}
        return ret;
    }

    public static void main(String[] args){
        TreeNode root;
        Solution sol;
        
        /* Generate a input tree
         *  1
         *   \
         *    2
         *     \
         *      2
         */
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(2);
        sol = new Solution();
        System.out.println("modes: " + Arrays.toString(sol.findMode(root)));
    }
}
