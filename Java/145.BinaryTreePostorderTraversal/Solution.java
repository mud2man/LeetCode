/* Stack + HashSet: O(n), where n is the number of nodes
 * 1. Use stack the store nodes along the traversed path
 * 2. Only push the nodes which not in the hashset traversedTreeNodes
 * 3. After go down the tree, pop the top and put it in postOrder
 * 4. Repeat step#3, until stack is empty
 *
 * ex:tree => 3
 *           / \
 *          9   20
 *              / \
 *             15  7
 * time[0]: stack={3}, traversedTreeNodes={3}, postOrder={}
 * time[1]: stack={3, 20, 9}, traversedTreeNodes={3, 20, 9}, postOrder={} (goDown)
 * time[2]: stack={3, 20}, traversedTreeNodes={3, 20, 9}, postOrder={9} (pop)
 * time[3]: stack={3, 20, 7, 15}, traversedTreeNodes={3, 20, 9, 7, 15}, postOrder={} (goDown)
 * time[4]: stack={3, 20, 7}, traversedTreeNodes={3, 20, 9, 7, 15}, postOrder={9, 15} (pop)
 * time[5]: stack={3, 20}, traversedTreeNodes={3, 20, 9, 7, 15}, postOrder={9, 15, 7} (pop)
 * time[6]: stack={3}, traversedTreeNodes={3, 20, 9, 7, 15}, postOrder={9, 15, 7, 20} (pop)
 * time[7]: stack={}, traversedTreeNodes={3, 20, 9, 7, 15}, postOrder={9, 15, 7, 20, 3} (pop)
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode 
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public void goDown(Stack<TreeNode> stack, HashSet<TreeNode> traversedTreeNodes){
        TreeNode parent, left, right;
        
        do{
            parent = stack.peek();
            if(parent.right != null && !traversedTreeNodes.contains(parent.right)){
                right = parent.right;
                stack.push(right);
                traversedTreeNodes.add(right);
            }
            else{
                right = null;
            }
            
            if(parent.left != null && !traversedTreeNodes.contains(parent.left)){
                left = parent.left;
                stack.push(left);
                traversedTreeNodes.add(left);
            }
            else{
                left = null;
            }
        }while(left != null || right != null );
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrder = new ArrayList<Integer>();
        HashSet<TreeNode> traversedTreeNodes = new HashSet<TreeNode>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node;
        
        if(root != null){
            stack.push(root);
            traversedTreeNodes.add(root);
        }
        
        while(!stack.empty()){
            goDown(stack, traversedTreeNodes);
            node = stack.pop();
            postOrder.add(node.val);
        }
        
        return postOrder;
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
