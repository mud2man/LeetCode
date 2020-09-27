/* Stack and queue: Time:O(h), Space:O(n)
 * 1. Use "inOrderList" to sotre the pop nodes
 * 2. Return the val from inOrderList as long as 0 < idxInOrder < inOrderList.size() - 1, and then shift idxInOrder
 */     

import java.util.*; // Stack

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BSTIterator {
    List<TreeNode> inOrderList;
    Deque<TreeNode> stack;
    int idxInOrder;
    
    private void insertLefts(TreeNode root){
        while(root != null){
            stack.add(root);
            root = root.left;
        }
    }
    
    public BSTIterator(TreeNode root) {
        inOrderList = new ArrayList<>();
        stack = new LinkedList<>();
        idxInOrder = -1;
        insertLefts(root);
    }
    
    public boolean hasNext() {
        return !(stack.isEmpty() && idxInOrder == inOrderList.size() - 1);
    }
    
    public int next() {
        if(idxInOrder < inOrderList.size() - 1){
            return inOrderList.get(++idxInOrder).val;
        }else{
            TreeNode top = stack.pollLast();
            insertLefts(top.right);
            inOrderList.add(top);
            ++idxInOrder;
            return top.val;
        }
    }
    
    public boolean hasPrev() {
        return (idxInOrder > 0);
    }
    
    public int prev() {
        return inOrderList.get(--idxInOrder).val;
    }
  
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BSTIterator sol = new BSTIterator(root);
        System.out.println("next():" + sol.next());
        System.out.println("next():" + sol.next());
        System.out.println("prev():" + sol.prev());
        System.out.println("next():" + sol.next());
        System.out.println("hasNext():" + sol.hasNext());
        System.out.println("next():" + sol.next());
        System.out.println("next():" + sol.next());
        System.out.println("next():" + sol.next());
        System.out.println("hasNext():" + sol.hasNext());
        System.out.println("hasPrev():" + sol.hasPrev());
        System.out.println("prev():" + sol.prev());
        System.out.println("prev():" + sol.prev());
    }
}
