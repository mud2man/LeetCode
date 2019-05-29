/* Floyd's Tortoise: Time:O(n), Space:O(1)
 *
 *               r 
 *            /    \
 *   *---s---*-m-*--*
 *
 * 1. We can prove that fast pointer always catch up slow pointer, assume it happens on s + m
 * 2. When (fastPtr == slowPt), s + m + nr = 2s + 2m => nr - m = s
 * 3. Hence, the pointer starting from head take s steps to meet the one starting from (s + m) which takes (nr - m) = s steps
 * 4. And the meeting point is the starting pointer of the cycle
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode detectCycle(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        ListNode intersectPtr = null;
        
        while(fastPtr != null){
            slowPtr = slowPtr.next;
            fastPtr = (fastPtr != null && fastPtr.next != null)? fastPtr.next.next: null;
            if(fastPtr == slowPtr){
                intersectPtr = fastPtr;
                break;
            }
        }
        
        if(intersectPtr == null){
            return null;
        }
        else{
            ListNode ptr1 = head;
            ListNode ptr2 = intersectPtr;
            while(ptr1 != ptr2){
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            }
            return ptr1;
        }
    }
  
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = head.next;
        
        System.out.print("list: ");
        ListNode node = head;
        for(int i = 0; i < 6; ++i){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");

        Solution sol = new Solution();
        node = sol.detectCycle(head);
        System.out.println("the starting pointer: " + node.val);
    }
}
