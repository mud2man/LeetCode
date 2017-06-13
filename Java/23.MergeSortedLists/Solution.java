/* Minimun head: O(n*logk), where n = node#, k = list#
 * 1. Create a minimun heap, and put every head of list into the heap
 * 2. Retrieve the node with minimun value from the minHeap, and put the next node into minHeap
 * 3. Repeat step#2 until the heap empty
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    private class NodeComparator implements Comparator<ListNode>{
        public int compare(ListNode x, ListNode y ){
            return x.val - y.val;
        }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode prevNode, currNode;
        ListNode dummy = new ListNode(0);
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(new NodeComparator());
        
        for(ListNode node: lists){
            if(node != null){
                minHeap.add(node);
            }
        }
        
        prevNode = dummy;
        while(!minHeap.isEmpty()){
            currNode = minHeap.poll();
            prevNode.next = currNode;
            prevNode = currNode;
            
            if(currNode.next != null){
                minHeap.add(currNode.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head0;
        ListNode head1;
        ListNode node;
        ListNode[] lists = {null, null};

        sol = new Solution();
        head0 = new ListNode(1);
        head0.next = new ListNode(4);
        head0.next.next = new ListNode(5);
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        lists[0] = head0;
        lists[1] = head1;
        
        System.out.println("list0: ");
        node = head0;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");

        System.out.println("list1: ");
        node = head1;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
        
        head0 = sol.mergeKLists(lists);

        System.out.println("after merged: ");
        node = head0;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
