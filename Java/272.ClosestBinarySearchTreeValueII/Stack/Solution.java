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
    private void search(TreeNode root, Deque<TreeNode> list, double target, boolean isSmall){
        if(root == null){
            return;
        }
        else if((double)root.val == target){
            if(isSmall){
                list.add(root);
            }
        }
        else{
            if((double)root.val < target){
                if(isSmall){
                    list.add(root);
                }
                search(root.right, list, target, isSmall);
            }
            else{
                if(!isSmall){
                    list.add(root);
                }
                search(root.left, list, target, isSmall);
            }
        }
    }
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<TreeNode> small = new LinkedList<>();
        Deque<TreeNode> big = new LinkedList<>();
        search(root, small, target, true);
        search(root, big, target, false);
        
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < k; ++i){
            TreeNode smallNode = small.peekLast();
            TreeNode bigNode = big.peekLast();
            double smallDiff = (smallNode == null)? Double.MAX_VALUE: target - (double)smallNode.val;
            double bigDiff = (bigNode == null)? Double.MAX_VALUE: (double)bigNode.val - target;
            if(smallDiff < bigDiff){
                ret.add(smallNode.val);
                small.pollLast();
                if(smallDiff == 0.0){
                    search(smallNode.right, big, target, false);
                }
                search(smallNode.left, small, target, true);
            }
            else{
                ret.add(bigNode.val);
                big.pollLast();
                search(bigNode.right, big, target, false);
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
