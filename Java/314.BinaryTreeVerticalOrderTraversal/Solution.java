/* BFS and doubly linked list: O(n)
 * 1. Use BFS traverse all nodes
 * 2. Serialize all the columns in doubly linked list
 * 3. The column of left child = the left node of the list node, and the column of right child = the right node of the list node
 * ex: 
 *     3
 *    / \
 *   9   20
 *       / \
 *      15  7
 *
 * 1st: [3]
 * 2nd: [9]<->[3]<->[20]
 * 2nd: [9]<->[3, 15]<->[20]<->[7]
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
    
       private class ListNode{
        List<Integer> col;
        ListNode left;
        ListNode right;
        ListNode(List<Integer> c, ListNode l, ListNode r){
            col = c;
            left = l; 
            right = r;}
    }
    
    private class WrapNode{
        ListNode listNode;
        TreeNode treeNode;
        WrapNode(ListNode l, TreeNode t){
            listNode = l;
            treeNode = t;
        }
    }
    
    public ListNode bfs(TreeNode root){
        ListNode head;
        ListNode listNode;
        ListNode leftListNode;
        ListNode rightListNode;
        TreeNode treeNode;
        List<Integer> col;
        WrapNode wrapNode;
        WrapNode tmp;
        LinkedList<WrapNode> queue;
        int size;
        int i;
        
        if(root == null){
            return null;
        }
        
        queue = new LinkedList<WrapNode>();
        head = new ListNode(new ArrayList<Integer>(), null, null);
        head.col.add(root.val);
        wrapNode = new WrapNode(head, root);
        queue.add(wrapNode);
        
        while(!queue.isEmpty()){
            size = queue.size();
            for(i = 0; i < size; ++i){
                wrapNode = queue.poll();
                treeNode = wrapNode.treeNode;
                listNode = wrapNode.listNode;
                
                if(treeNode.left != null){
                    if(listNode.left == null){
                        leftListNode = new ListNode(new ArrayList<Integer>(), null, listNode);
                    }
                    else{
                        leftListNode = listNode.left;
                    }
                    listNode.left = leftListNode;
                    leftListNode.col.add(treeNode.left.val);
                    queue.add(new WrapNode(leftListNode, treeNode.left));
                }
                
                if(treeNode.right != null){
                    if(listNode.right == null){
                        rightListNode = new ListNode(new ArrayList<Integer>(), listNode, null);
                    }
                    else{
                        rightListNode = listNode.right;
                    }
                    listNode.right = rightListNode;
                    rightListNode.col.add(treeNode.right.val);
                    queue.add(new WrapNode(rightListNode, treeNode.right));
                }
            }
        }
        
        while(head.left != null){
            head = head.left;
        }
        
        return head;
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> list;
        ListNode head;
        ListNode listNode;
        
        list = new ArrayList<List<Integer>>();
        
        head = bfs(root);
        
        for(listNode = head; listNode != null; listNode = listNode.right){
            list.add(listNode.col);
        }
        
        return list;
    }
 
    public static void main(String[] args)
    {
        List<List<Integer>> list;
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
        list = sol.verticalOrder(root);

        System.out.println("vertical order traversal: ");
        for(List<Integer> col: list){
            System.out.println(col);
        }
    }
}
