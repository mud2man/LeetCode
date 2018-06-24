/* DFS: Time:O(n), Space:O(n)
 * 1. Traslate the list to array list "nums"
 * 2. Take the median as root, and make left half of nums as left subtree, and right half as right subtreee
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    private TreeNode bst(List<Integer> nums, int start, int end){
        if(start > end){
            return null;
        }
        
        int leftSize = (end - start + 1) / 2;
        int rootIdx = start + leftSize;
        TreeNode root = new TreeNode(nums.get(rootIdx));
        root.left = bst(nums, start, rootIdx - 1);
        root.right = bst(nums, rootIdx + 1, end);
        return root;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> nums = new ArrayList<Integer>();
        for(ListNode node = head; node != null; node = node.next){
            nums.add(node.val);
        }
        return bst(nums, 0 , nums.size() - 1);
    }

    private void inOrder(TreeNode tree){
        if(tree == null){
            return;
        }

        inOrder(tree.left);
        System.out.print(tree.val + "->");
        inOrder(tree.right);
    }

    public static void main(String[] args){
        ListNode list;
        Solution sol = new Solution();
        
        list = new ListNode(-10);
        list.next = new ListNode(-3);
        list.next.next = new ListNode(0);
        list.next.next.next = new ListNode(5);
        list.next.next.next.next = new ListNode(9);
        TreeNode tree = sol.sortedListToBST(list);
        sol.inOrder(tree);
        System.out.println("");
    }
}
