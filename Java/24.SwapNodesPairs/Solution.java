/* O(n)
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
        ListNode dummy, w, x, y, z;
        
        dummy = new ListNode(0);
        dummy.next = head;
        
        w = dummy;
        x = head;
        y = (x != null)? x.next: null;
        z = (y != null)? y.next: null;
        while(y != null){
            y.next = x;
            w.next = y;
            x.next = z;
            
            w = x;
            x = z;
            y = (z != null)? z.next: null;
            z = (y != null)? y.next: null;
        }
        
        return dummy.next;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;

        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        System.out.println("before swap: ");
        node = head;
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
