/* Two pointers: Time:O(n), Space:O(n)
 * 1. Shift pointer "end" n steps, and assign "prev" with dummy
 * 2. Shift "end" until hitting null, and remove "prev.next"
 */

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution{
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode end = head;
        for(int i = 0; i < n; ++i){
            end = end.next;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while(end != null){
            prev = prev.next;
            end = end.next;
        }
        prev.next = prev.next.next;
        return dummy.next;
    }
    
    private String dump(ListNode head){
        String ret = "";
        for(ListNode itr = head; itr != null; itr = itr.next){
            ret += (Integer.toString(itr.val) + "->");
        }
        return ret;
    }
 
    public static void main(String[] args){     
        int n = 2;
        Solution sol = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        System.out.println("n: " + n);
        System.out.println("before remove: " + sol.dump(head));
        head = sol.removeNthFromEnd(head, n);
        System.out.println("after remove: " + sol.dump(head));
	}
}
