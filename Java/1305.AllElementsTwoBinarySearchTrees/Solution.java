/* DFS: Time:O(n), Space:O(h)
 * 1. Have a stack to store the current position of a tree in inorder base
 * 2. Retrieve the smaller top between stack1 and stack2 until both stacks are empty
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
    private void getNext(TreeNode root, Deque<TreeNode> stack){
        while(root != null){
            stack.add(root);
            root = root.left;
        }
    }
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack1 = new LinkedList<>();
        getNext(root1, stack1);
        Deque<TreeNode> stack2 = new LinkedList<>();
        getNext(root2, stack2);
        List<Integer> queue3 = new ArrayList<>();
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            TreeNode top = null;
            if(stack1.isEmpty()){
                top = stack2.pollLast();
                getNext(top.right, stack2);
            }else if(stack2.isEmpty()){
                top = stack1.pollLast();
                getNext(top.right, stack1);
            }else{
                if(stack1.peekLast().val <= stack2.peekLast().val){
                    top = stack1.pollLast();
                    getNext(top.right, stack1);
                }else{
                    top = stack2.pollLast();
                    getNext(top.right, stack2);
                }
            }
            queue3.add(top.val);
        }
        return queue3;
    }
  
    public static void main(String[] args){
        /* Generate a input tree
         *     2       1
         *    / \     / \
         *   1   4   0   3
         */
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);
        Solution sol = new Solution();
        System.out.println("sorted queue:" + sol.getAllElements(root1, root2));
    }
}
