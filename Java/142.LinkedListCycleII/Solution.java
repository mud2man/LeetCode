/* Math: O(n)
 *
 *               r 
 *            /    \|
 *   *---s---*-m-*--*
 *
 * 1. Assume fast pointer catch up slow pointer on s + m
 * 2. When fastPtr.next.next == slowPt, s + m + nr = 2s + 2m => nr - m = s
 * 3. Or when fastPtr.next == slowPtr, s + m - 1 + nr = 2s + 2m - 1 => nr - m = s
 * 3. Hence, the pointer starting from head take s  steps to meet the one starting from (s + m) which takes s steps
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
        ListNode slowPtr = (head != null)? head.next: null;
        ListNode fastPtr = (head != null && head.next != null)? head.next.next: null;
        ListNode intersectPtr = null;
        
        while(fastPtr != null){
            slowPtr = slowPtr.next;
            if(fastPtr != null && fastPtr.next != null){
                if(fastPtr.next == slowPtr || fastPtr.next.next == slowPtr){
                    intersectPtr = slowPtr;
                    break;
                }
            }
            fastPtr = (fastPtr != null && fastPtr.next != null)? fastPtr.next.next: null;
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
        Solution sol = new Solution();
        ListNode head;
        ListNode node;
        int m, n;

        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = head.next;
        
        System.out.print("list: ");
        node = head;
        for(int i = 0; i < 6; ++i){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");

        node = sol.detectCycle(head);
        System.out.println("the starting pinter: " + node.val);
    }
}
