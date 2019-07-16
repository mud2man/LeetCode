/* DFS: Time:O(n), Space:O(n)
 * 1. Transform input string to pairs of depthValue. i.e. 1-2--3--4-5--6--7 = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {1, 5}, {2, 6}, {2, 7}}
 * 2. Use helper "preorder" to construct tree in a preorder sequence
 * 3. Only if currentDepth = prevDepth + 1, we new a node. Otherwise, return null
 * 4. use "preorder" to get the left and right child respectively
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
    private TreeNode preorder(List<int[]> depthValuePairs, int[] idx, int prevDepth){
        if(idx[0] >= depthValuePairs.size() || prevDepth + 1 != depthValuePairs.get(idx[0])[0]){
            return null;
        }
        int[] depthValuePair = depthValuePairs.get(idx[0]++);
        TreeNode node = new TreeNode(depthValuePair[1]);
        node.left = preorder(depthValuePairs, idx, depthValuePair[0]);
        node.right = preorder(depthValuePairs, idx, depthValuePair[0]);
        return node;
    }
    
    public TreeNode recoverFromPreorder(String S) {
        List<int[]> depthValuePairs = new ArrayList<>();
        int i = 0;
        while(i < S.length()){
            int depth = 0;
            while(i < S.length() && S.charAt(i) == '-'){
                depth++;
                i++;
            }
            int value = 0;
            while(i < S.length() && S.charAt(i) != '-'){
                value = value * 10 + (S.charAt(i) - '0');
                i++;
            }
            depthValuePairs.add(new int[]{depth, value});
        }
        int[] idx = {0};
        return preorder(depthValuePairs, idx, -1);   
    }
  
    public void dump(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + "->");
        dump(root.left);
        dump(root.right);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "1-2--3--4-5--6--7";
        System.out.println("S: " + S);
        System.out.print("tree: ");
        sol.dump(sol.recoverFromPreorder(S));
        System.out.println("");
    }
}
