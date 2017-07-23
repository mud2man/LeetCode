/* Stack: O(k*logn)
 * 1. Find the closet node, and its next bigger node and next smaller node
 * 2. If the bigger node is closer target than smaller node, put the bigger node into closestValues and biggerNode = nextBigger()
 * 3. Otherwise, put the smaller node into closestValues and smallerNode = nextSmaller()
 * 4. Repeat step2 and step3 until the number of closestValues is equal to k
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
    private TreeNode nextBigger(TreeNode root, TreeNode node){
        LinkedList<TreeNode> stack = new LinkedList<TreeNode> ();
        
        TreeNode ptr = root;
        while(ptr != node){
            stack.add(ptr);
            ptr = (ptr.val > node.val)? ptr.left: ptr.right;
        }
        stack.add(ptr);
        
        TreeNode biggerChild = null;
        TreeNode biggerParent = null;
        
        if(node.right != null){
            biggerChild = node.right;
            while(biggerChild.left != null){
                biggerChild = biggerChild.left;
            }
        }
        
        for(int idx = stack.size() - 2; idx >= 0; idx--){
            if(stack.get(idx).left == stack.get(idx + 1)){
                biggerParent = stack.get(idx);
                break;
            }
        }
        
        if(biggerChild != null && biggerParent != null){
            return (biggerChild.val < biggerParent.val)? biggerChild: biggerParent;
        }
        else if(biggerChild == null && biggerParent != null){
            return biggerParent;
        }
        else if(biggerChild != null && biggerParent == null){
            return biggerChild;
        }
        else{
            return null;
        }
    }
    
    private TreeNode nextSmaller(TreeNode root, TreeNode node){
        LinkedList<TreeNode> stack = new LinkedList<TreeNode> ();
        
        TreeNode ptr = root;
        while(ptr != node){
            stack.add(ptr);
            ptr = (ptr.val > node.val)? ptr.left: ptr.right;
        }
        stack.add(ptr);
        
        TreeNode smallerChild = null;
        TreeNode smallerParent = null;
        
        if(node.left != null){
            smallerChild = node.left;
            while(smallerChild.right != null){
                smallerChild = smallerChild.right;
            }
        }
        
        for(int idx = stack.size() - 2; idx >= 0; idx--){
            if(stack.get(idx).right == stack.get(idx + 1)){
                smallerParent = stack.get(idx);
                break;
            }
        }
        
        if(smallerChild != null && smallerParent != null){
            return (smallerChild.val > smallerParent.val)? smallerChild: smallerParent;
        }
        else if(smallerChild == null && smallerParent != null){
            return smallerParent;
        }
        else if(smallerChild != null && smallerParent == null){
            return smallerChild;
        }
        else{
            return null;
        }
    }
    
    private TreeNode closestNode(TreeNode root, double target){
        double minDiff = Double.MAX_VALUE;
        TreeNode ptr = root;
        double currDiff;
        TreeNode closestNode = ptr;
        
        while(ptr != null && minDiff > 0){
            currDiff = Math.abs((double)ptr.val - target);
            if(currDiff < minDiff){
                minDiff = currDiff;
                closestNode = ptr;
            }
            ptr = ((double)ptr.val < target)? ptr.right: ptr.left;
        }
        return closestNode;
    }
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> closestValues = new ArrayList<Integer>();
        TreeNode closestNode = closestNode(root, target);
        TreeNode biggerNode = nextBigger(root, closestNode);
        TreeNode smallerNode = nextSmaller(root, closestNode);
        
        closestValues.add(closestNode.val);
        while(closestValues.size() < k){
            if(smallerNode == null){
                closestValues.add(biggerNode.val);
                biggerNode = nextBigger(root, biggerNode);   
            }
            else if(biggerNode == null){
                closestValues.add(smallerNode.val);
                smallerNode = nextSmaller(root, smallerNode); 
            }
            else{
                if(Math.abs((double)biggerNode.val - target) < Math.abs((double)smallerNode.val - target)){
                    closestValues.add(biggerNode.val);
                    biggerNode = nextBigger(root, biggerNode);
                }
                else{
                    closestValues.add(smallerNode.val);
                    smallerNode = nextSmaller(root, smallerNode);
                }
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
