/* Two Pointers: Time:O(n), Space:O(1)
 * 1. Have pointers "fast" and "slow", and check if "fast" can reach "slow"
 * 2. Reconnect the top and bottom half, given the reduced k
 */

import java.util.*; // Stack

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = (head != null)? head.next: null;
        while(slow != null && fast != null){
            slow = slow.next;
            fast = fast.next;
            if(slow == fast){
                return true;
            }
            
            fast = (fast != null)? fast.next: null;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        ListNode head;
        Solution sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next;
        System.out.println("has cycle: " + sol.hasCycle(head));
    }
}
