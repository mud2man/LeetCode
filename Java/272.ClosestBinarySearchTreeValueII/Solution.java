/* Stack: O(k*logn)
 * 1. Have two stacks "successors" and "predecessors" 
 * 2. Initialize the two stack by visiting from root
 * 3. In every loop, get the closer node choosing from predecessors and successors 
 * 4. If predecessor is choosen, invoke addNext with root = predecessor.left, because we need to add farer elements
 * 5. If sucessor is choosen, invoke addNext with root = sucessor.right, because we need to add farer elements
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
    private void addNext(TreeNode root, double target, LinkedList<TreeNode> path, boolean isSucessor){
        if(root == null){
            return;
        }
        
        int val = root.val;
        if((double)val == target){
            if(!isSucessor){
                path.add(root);
            }
        }
        else if((double)val < target){
            if(!isSucessor){
                path.add(root);
            }
            addNext(root.right, target, path, isSucessor);
        }
        else{
            if(isSucessor){
                path.add(root);
            }
            addNext(root.left, target, path, isSucessor);
        }
    }
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<TreeNode> successors = new LinkedList<TreeNode>();
        LinkedList<TreeNode> predecessors = new LinkedList<TreeNode>();
        addNext(root, target, successors, true);
        addNext(root, target, predecessors, false);
        List<Integer> closestValues = new LinkedList<Integer>();
        
        while(closestValues.size() < k){
            double diffSucessor = successors.isEmpty()? Double.MAX_VALUE: (double)successors.peekLast().val - target;
            double diffPredecessor = predecessors.isEmpty()? Double.MAX_VALUE: target - (double)predecessors.peekLast().val;

            if(diffPredecessor < diffSucessor){
                TreeNode predecessor = predecessors.pollLast();
                closestValues.add(predecessor.val);
                if(diffPredecessor == 0.0){
                    addNext(predecessor.right, target, successors, true);
                }
                addNext(predecessor.left, target, predecessors, false);
            }
            else{
                TreeNode sucessor = successors.pollLast();
                closestValues.add(sucessor.val);
                addNext(sucessor.right, target, successors, true);
            }
        }
        
        return closestValues;
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
