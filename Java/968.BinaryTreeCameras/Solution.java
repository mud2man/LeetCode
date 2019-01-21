/* Dynamic Programming: Time:O(n), Space:O(n). LeetCode has greedy solution only consums O(h) space
 * 1. Traverse tree in post order, and keep a map to recorder the sub solution
 * 2. The map store pairs with key = node, value = {min camera# with camera on node covering subtree, min camera# without camera on node coovering subtree}
 * 3. For camera on node: we need to consider grandsons
 * 4. For camera not on node: we just need to consider child
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
    private void dfs(TreeNode root, Map<TreeNode, int[]> map){
        if(root == null){
            return;
        }
        
        dfs(root.left, map);
        dfs(root.right, map);
        map.put(root, new int[2]);
        //count if put camera on this node
        TreeNode leftLeft = (root.left != null)? root.left.left: null;
        TreeNode leftRight = (root.left != null)? root.left.right: null;
        TreeNode rightLeft = (root.right != null)? root.right.left: null;
        TreeNode rightRight = (root.right != null)? root.right.right: null;
        int leftCount = (root.left != null)? Math.min(map.get(root.left)[0], map.get(root.left)[1]): 0;
        int leftLeftCount = (leftLeft != null)? Math.min(map.get(leftLeft)[0], map.get(leftLeft)[1]): 0;
        int leftRightCount = (leftRight != null)? Math.min(map.get(leftRight)[0], map.get(leftRight)[1]): 0;
        leftCount = Math.min(leftCount, leftLeftCount + leftRightCount);
        int rightCount = (root.right != null)? Math.min(map.get(root.right)[0], map.get(root.right)[1]): 0;
        int rightLeftCount = (rightLeft != null)? Math.min(map.get(rightLeft)[0], map.get(rightLeft)[1]): 0;
        int rightRightCount = (rightRight != null)? Math.min(map.get(rightRight)[0], map.get(rightRight)[1]): 0;
        rightCount = Math.min(rightCount, rightLeftCount + rightRightCount);
        map.get(root)[0] = leftCount + rightCount + 1;
        
        //count if not put camera on this node
        if(root.left == null && root.right == null){
            map.get(root)[1] = 1;
        }
        else if(root.left == null){
            map.get(root)[1] = map.get(root.right)[0];
        }
        else if(root.right == null){
            map.get(root)[1] = map.get(root.left)[0];
        }
        else{
            TreeNode left = root.left;
            TreeNode right = root.right;
            map.get(root)[1] = Math.min(map.get(left)[0] + map.get(right)[1], map.get(left)[1] + map.get(right)[0]);
            map.get(root)[1] = Math.min(map.get(root)[1], map.get(left)[0] + map.get(right)[0]);
        }
    }
    
    public int minCameraCover(TreeNode root) {
        Map<TreeNode, int[]> map = new HashMap<>();
        dfs(root, map);
        return Math.min(map.get(root)[0], map.get(root)[1]);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        
        /* Generate a input tree
         *     4
         *    /
         *   2  
         *  / \   
         * 1   3  
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println("minimum camera#: " + sol.minCameraCover(root));
    }
}
