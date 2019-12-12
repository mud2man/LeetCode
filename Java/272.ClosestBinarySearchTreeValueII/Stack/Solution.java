/* Stack: O(k*logn)
 * 1. Have two queues "small" and "big" 
 * 2. Initialize the two queues by visiting from root
 * 3. In every loop, get the closer node choosing from small and big
 * 4. If small is choosen, invoke "search" with root = smallNode.left, because we need to add farer elements
 * 5. If big is choosen, invoke "search" with root = bigNode.right, because we need to add farer elements
 * 6. However, if predecessor = target, we need add both left and right
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
    private void insert(TreeNode root, double target, boolean isLessOrEqual, Deque<TreeNode> stack){
        if(root == null){
            return;
        }
        if((double)root.val <= target){
            if(isLessOrEqual){
                stack.add(root);
            }
            if((double)root.val == target){
                return;
            }else{
                insert(root.right, target, isLessOrEqual, stack);
            }
        }else{
            if(!isLessOrEqual){
                stack.add(root);
            }
            insert(root.left, target, isLessOrEqual, stack);
        }
    }
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<TreeNode> smalls = new LinkedList<>();
        Deque<TreeNode> bigs = new LinkedList<>();
        insert(root, target, true, smalls);
        insert(root, target, false, bigs);
        List<Integer> closeK = new ArrayList<>();
        while(closeK.size() < k){
            double small2TargetDiff = smalls.isEmpty()? Double.MAX_VALUE: target - (double)smalls.peekLast().val;
            double big2TargetDiff = bigs.isEmpty()? Double.MAX_VALUE: (double)bigs.peekLast().val - target;
            if(small2TargetDiff == 0.0){
                TreeNode node = smalls.pollLast();
                closeK.add(node.val);
                insert(node.left, target, true, smalls);
                insert(node.right, target, false, bigs);
            }else if(small2TargetDiff <= big2TargetDiff){
                TreeNode node = smalls.pollLast();
                closeK.add(node.val);
                insert(node.left, target, true, smalls);
            }else{
                TreeNode node = bigs.pollLast();
                closeK.add(node.val);
                insert(node.right, target, false, bigs);
            }
        }
        return closeK;
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     3
         *    / \
         *   9   20
         *       / \
         *      15  7
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        double target = 16.0;
        int k = 2;
        Solution sol = new Solution();
        System.out.println("target: " + target);
        System.out.println("k: " + k);
        System.out.println("closestValues: " + sol.closestKValues(root, target, k));
    }
}
