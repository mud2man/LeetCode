/* Quick sort: O(nlogn)
 * 1. Picj the first element as pivot, and do quick sort to split list as first half and second half
 * 2. Swap the value of head and firstHalfLastNode, and do quick sort on both halves
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    private void quickSort(ListNode head, int len){
        if(head == null || len <= 1){
            return;
        }
        
        int pivot = head.val;
        ListNode firstHalfLastNode = head;
        ListNode itr = head.next;
        int firstHalfLen = 1;
        for(int i = 0; i < (len - 1); ++i){
            if(itr.val <= pivot){
                firstHalfLen++;
                int temp = firstHalfLastNode.next.val;
                firstHalfLastNode.next.val = itr.val;
                itr.val = temp;
                firstHalfLastNode = firstHalfLastNode.next;
            }
            itr = itr.next;
        }
        int temp = firstHalfLastNode.val;
        firstHalfLastNode.val = pivot;
        head.val = temp;
        quickSort(head, firstHalfLen - 1);
        quickSort(firstHalfLastNode.next, len - firstHalfLen);
    }
    
    public ListNode sortList(ListNode head) {
        int len = 0;
        for(ListNode itr = head; itr != null; itr = itr.next){
            len++;
        }
        quickSort(head, len);
        return head;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        
        System.out.println("before sort ");
        for(ListNode node = head; node != null; node = node.next){
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        head = sol.sortList(head);

        System.out.println("\nafter sort");
        for(ListNode node = head; node != null; node = node.next){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
