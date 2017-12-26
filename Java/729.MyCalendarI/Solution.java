/* Interval tree: Time:O(logn), Space:O(n), We can use TreeMap instead. It's simpler
 * 1. Construct the interval tree, and return true if insert successfully
 */

import java.util.*;

public class Solution{
    private class TreeNode{
        TreeNode left;
        TreeNode right;
        int[] interval;
        boolean isInterval;
        TreeNode(){left = null; right = null; isInterval = false; interval = new int[2];}
    }
    TreeNode root;
    
    public Solution() {
        root = new TreeNode();
        root.interval[0] = Integer.MIN_VALUE;
        root.interval[1] = Integer.MAX_VALUE;
    }
    
    private boolean insert(TreeNode parent, int start, int end){
        if(parent.isInterval || parent.interval[0] >= start || parent.interval[1] <= end){
            return false;
        }
    
        int[] leftInterval = (parent.left != null)? parent.left.interval: new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        int[] rightInterval = (parent.right != null)? parent.right.interval: new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        if(start >= leftInterval[1] && end <= rightInterval[0]){
            TreeNode node = new TreeNode();
            node.interval[0] = start;
            node.interval[1] = end;
            node.isInterval = true;
            TreeNode interNode = new TreeNode();
            interNode.interval[0] = leftInterval[0];
            interNode.interval[1] = end;
            interNode.right = node;
            interNode.left = parent.left;
            parent.left = interNode;
            return true;
        }
        else if(end <= rightInterval[0]){
            return insert(parent.left, start, end);
        }
        else{
            return insert(parent.right, start, end);
        }
    }
    
    public boolean book(int start, int end) {
        return insert(root, start, end);
    }
    
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] intervals = {{10, 20}, {15, 25}, {20, 30}};
        
        for(int[] interval: intervals){
            System.out.println(Arrays.toString(interval));
        	System.out.println("insert success: " + sol.book(interval[0], interval[1]));
        }
    }
}
