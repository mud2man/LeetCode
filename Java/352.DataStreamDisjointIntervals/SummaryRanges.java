/* BST: Time:O(logn), Space:O(n). LeetCode has a shorter solution
 * 1. Have a object "treeNode" with pointer "farther", and the BST is indexed by start
 * 2. Since all interval are not overlapped, we don't use interval tree
 * 3. We use "insert" method to insert new value to the BST, and do merging if necessary, then return the node need be deleted
 * 4. In "delete", if the current's right is null, concatenate the left subtree
 * 5. Otherwise, get the smallest node in right subtree, and swap it with the current node. The invoke delete() recursively
 */

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class SummaryRanges {
    private class TreeNode{
        int start;
        int end;
        TreeNode left;
        TreeNode right;
        TreeNode farther;
        TreeNode(int s, int e, TreeNode f){start = s; end = e; farther = f;}
    }

    TreeNode root;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        root = null;
    }
    
    private TreeNode insert(TreeNode farther, TreeNode node, int val){
        if(node == null){
            TreeNode newNode = new TreeNode(val, val, farther);
            if(farther.end < val){
                farther.right = newNode;
            }
            else{
                farther.left = newNode;
            }
            return null;
        }
        
        if(node.end + 1 < val){
            return insert(node, node.right, val);
        }
        else if(node.start - 1 > val){
            return insert(node, node.left, val);
        }
        else{
            if(node.end >= val && node.start <= val){
                return null;
            }
            else if(node.end + 1 == val){
                node.end++;
                TreeNode next = node.right;
                while(next != null && next.left != null){
                    next = next.left;
                }
                
                if(next != null && next.start == val + 1){
                    node.end = next.end;
                    return next;
                }
                else{
                    return null;
                }
            }
            else{
                node.start--;
                TreeNode prev = node.left;
                while(prev != null && prev.right != null){
                    prev = prev.right;
                }
                if(prev != null && prev.end == val - 1){
                    node.start = prev.start;
                    return prev;
                }
                else{
                    return null;
                }
            }
        }
    }
    
    private void delete(TreeNode node){
        if(node == null){
            return;
        }
        
        if(node.right == null){
            if(node.left != null){
                node.left.farther = node.farther;
            }
            if(node == node.farther.left){
                node.farther.left = node.left;
            }
            else{
                node.farther.right = node.left;
            }
        }
        else{
            TreeNode next = node.right;
            while(next.left != null){
                next = next.left;
            }
            node.start = next.start;
            node.end = next.end;
            delete(next);
        }
    }
    
    public void addNum(int val) {
        if(root == null){
            root = new TreeNode(val, val, null);
        }
        else{
            TreeNode deleteNode = insert(null, root, val);
            delete(deleteNode);
        }
    }
    
    private void inOrder(List<Interval> intervals, TreeNode root){
        if(root == null){
            return;
        }
        inOrder(intervals, root.left);
        intervals.add(new Interval(root.start, root.end));
        inOrder(intervals, root.right);
    }
    
    public List<Interval> getIntervals() {
        List<Interval> intervals = new ArrayList<>();
        inOrder(intervals, root);
        return intervals;
    }

    private void dump(List<Interval> intervals){
        for(Interval i: intervals){
            System.out.print("[" + i.start + ", " + i.end + "], ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        SummaryRanges sol = new SummaryRanges();
        int num;

        num = 1;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());

        num = 3;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());
        
        num = 7;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());
        
        num = 2;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());
        
        num = 6;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());
    }
}
