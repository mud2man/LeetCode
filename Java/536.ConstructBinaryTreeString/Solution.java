/* Stack: Time:O(n), Space:O(n)
 * 1. Have variable "preOp"
 * 2. In the while loop, if current char is ')', pop stack
 * 3. Otherwise, it must be '(', then we get the next number and decide append top's left or top.s right to current node 
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
    public TreeNode str2tree(String s) {
        if(s.equals("")){
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode dummy = new TreeNode(0);
        stack.addLast(dummy);
        int preOp = 0; // 0:push, 1:pop
        int currOp = 0;
        int idx = 0;
        s = "(" + s;
        while(idx < s.length()){
            if(s.charAt(idx) == ')'){
                currOp = 1;
                stack.pollLast();
                idx++;
            }
            else{
                currOp = 0;
                idx++;
                StringBuilder num = new StringBuilder("");
                while(idx < s.length() && s.charAt(idx) != '(' && s.charAt(idx) != ')'){
                    num.append(s.charAt(idx++));
                }
                TreeNode node = new TreeNode(Integer.valueOf(num.toString()));
                if(preOp == 0){
                    stack.peekLast().left = node;
                    stack.addLast(node);
                }
                else{
                    stack.peekLast().right = node;
                    stack.addLast(node);
                }
            }
            preOp = currOp;
        }
        return dummy.left;
    }

    private void preOrder(TreeNode root){
        if(root == null){ 
            return;
        }
        System.out.print(root.val + "->");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "4(2(3)(1))(6(5))";
        TreeNode root = sol.str2tree(s);
        System.out.println("s: " + s);
        sol.preOrder(root); 
        System.out.println("");
    }
}
