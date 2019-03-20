/* Stack: Time:O(n), Space:O(h)
 * 1. Use stack the store nodes along the traversed path, and push its left child to it untill hitting null
 * 2. If top is the right child of the second top, we can put second top's val to "postorder", because it means we traversed left and right branch
 * 3. After repeating #2, we do push again
 * 4. Repeat step#2 and #3, until stack is empty
 *
 * ex:tree => 3
 *           / \
 *          9   20
 *              / \
 *             15  7
 * time[0]: stack={3, 9, null}, postOrder={9}
 * time[0.5]: stack={3, 20, 15, null}, postOrder={9}
 * time[1]: stack={3, 20}, postOrder={9, 15}
 * time[1.5]: stack={3, 20, 7, null}. postOrder={9, 15}
 * time[2]: stack={}, postOrder={9, 15, 7, 20, 3}
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
    private void push(Deque<TreeNode> stack, TreeNode node) {
        while(node != null){
            stack.add(node);
            node = node.left;
        }
        stack.add(null);
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        push(stack, root);
        while(!stack.isEmpty()){
            TreeNode top = stack.pollLast();
            while(!stack.isEmpty() && stack.peekLast().right == top){
                top = stack.pollLast();
                postorder.add(top.val);
            }
            if(!stack.isEmpty()){
                push(stack, stack.peekLast().right);
            }
        }
        return postorder;
    }
 
    public void inorder(TreeNode root){
        if(root == null){
            return;
        }
        else{
            inorder(root.left);
            System.out.print(root.val + ", ");
            inorder(root.right);
        }
    }
 
    public static void main(String[] args){
        List<Integer> postOrder = new ArrayList<Integer>();
        TreeNode root;
        Solution sol;
        
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
        
        System.out.println("inOrder of tree: ");
        sol.inorder(root);
        System.out.println("");
        
        System.out.println("postOrder of tree: ");
        System.out.println(sol.postorderTraversal(root));
    }
}
