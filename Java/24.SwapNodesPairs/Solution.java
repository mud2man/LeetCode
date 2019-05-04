/* Time:O(n), Space:O(1
 * 1. Use 4 pointers to do pointer exchange
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode[] window = new ListNode[4];
        window[0] = dummy;
        window[1] = head;
        window[2] = (window[1] != null)? window[1].next: null;
        window[3] = (window[2] != null)? window[2].next: null;
        while(window[2] != null){
            window[2].next = window[1];
            window[0].next = window[2];
            window[1].next = window[3];
            
            window[0] = window[1];
            window[1] = window[3];
            window[2] = (window[1] != null)? window[1].next: null;
            window[3] = (window[2] != null)? window[2].next: null;
        }
        return dummy.next;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        System.out.println("before swap: ");
        ListNode node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
        
        head = sol.swapPairs(head);

        System.out.println("after swap: ");
        node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
