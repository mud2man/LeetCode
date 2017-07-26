/* Math: O(n)
 *
 *               r 
 *            /    \|
 *   *---s---*-m-*--*
 *
 * 1. Assume fast pointer catch up slow pointer on s + m
 * 2. Therefore, s + m + nr = 2s + 2m => nr - m = s
 * 3. Hence, the pointer starting from head take s or (s + 1) steps to meet the one starting from (s + m) which takes s steps
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
        ListNode slow, fast;
        
        slow = head;
        fast = head;
        do{
            slow = (slow != null)? slow.next: null;
            fast = (fast != null && fast.next != null)? fast.next.next: null;
        }while(fast != slow && fast != null);
        
        if(fast == null){
            return null;
        }
        
        slow = head;
        while(slow != fast && slow.next != fast){
            slow = slow.next;
            fast = fast.next;
        }
        
        return fast;
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
