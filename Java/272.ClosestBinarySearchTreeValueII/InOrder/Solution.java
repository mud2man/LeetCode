/* Inorder + MergeSort + Binary Search: O(n)
 * 1. Do inorder traversal to insert number to a list
 * 2. Use binary search to find the first pointer "ptr0", and set "ptr1" as ptr0
 * 3. Use merge sort to put the closer neighbors to "ret"
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
    private void inoredr(TreeNode root, List<Double> nums){
        if(root == null){
            return;
        } 
        inoredr(root.left, nums);
        nums.add((double)root.val);
        inoredr(root.right, nums);
    }
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Double> nums = new ArrayList<Double>();
        inoredr(root, nums);
        int ptr0 = Collections.binarySearch(nums, target);
        if(ptr0 < 0){
            ptr0 = -(ptr0 + 1) - 1;
        }
        int ptr1 = ptr0 + 1;
        
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < k; ++i){
            double diffPtr0 = (ptr0 >= 0)? target - nums.get(ptr0): Double.MAX_VALUE;
            double diffPtr1 = (ptr1 < nums.size())? nums.get(ptr1) - target: Double.MAX_VALUE;
            if(diffPtr0 < diffPtr1){
                ret.add((int)Math.round(nums.get(ptr0--)));
            }
            else{
                ret.add((int)Math.round(nums.get(ptr1++)));
            }
        }
        return ret;
    }

    public static void main(String[] args){
        List<Integer> closestValues;
        TreeNode root;
        Solution sol;
        double target = 16.0;
        int k = 2;
        
        /* Generate a input tree
         *     3
         *    / \
         *   9   20
         *       / \
         *      15  7
         */
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        sol = new Solution();
        
        System.out.println("target: " + target);
        System.out.println("k: " + k);
        closestValues = sol.closestKValues(root, target, k);
        System.out.println("closestValues: " + closestValues);
    }
}
