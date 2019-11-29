/* Hash + PreOrder: Time:O(n^2), Space:O(n), since we do at most c(n, 2) comparisome
 * 1. Have a map to store the result
 * 2. Compare (root1.left/root2.left, root1.right/root2.right) and (root1.left/root2.right, root1.right/root2.left). Return true either of them is true
 */

import java.util.*; // Stack

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private boolean helper(TreeNode root1, TreeNode root2, Map<TreeNode, Map<TreeNode, Boolean>> map){
        if(root1 == null && root2 == null){
            return true;
        }else if(root1 == null){
            return false;
        }else if(root2 == null){
            return false;
        }else{
            if(map.containsKey(root1) && map.get(root1).containsKey(root2)){
                return map.get(root1).get(root2);
            }
            boolean isEqual = false;
            if(root1.val == root2.val && flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)){
                isEqual = true;
            }
            if(root1.val == root2.val && flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left)){
                isEqual = true;
            }
            map.computeIfAbsent(root1, key -> new HashMap<>()).put(root2, isEqual);
            map.computeIfAbsent(root2, key -> new HashMap<>()).put(root1, isEqual);
            return isEqual;
        }
    }
    
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return helper(root1, root2, new HashMap<>());
    }
  
    public static void main(String[] args){
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(2);
        Solution sol = new Solution();
        System.out.println("is flip equal:" + sol.flipEquiv(root1, root2));
    }
}
