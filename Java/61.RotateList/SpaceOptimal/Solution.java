/* Space optimal: Time:O(n), Space:O(1)
 * 1. Find the length and old tail in the first shot
 * 2. Find the break point, and new tail. Then, do break and concatenate
 */

import java.util.*; // Stack

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        
        int len = 0;
        ListNode itr = head;
        ListNode oldTail = null;
        while(itr != null){
            oldTail = itr;
            itr = itr.next;
            len++;
        }
        
        if(k % len == 0){
            return head;
        }
        k = k % len;
        ListNode newHead = head;
        ListNode newTail = null;
        int step = len - k;
        for(int i = 0; i < step; ++i){
            newTail = newHead;
            newHead = newHead.next;
        }
        
        //break
        newTail.next = null;
        //concatenate
        oldTail.next = head;
        return newHead;
    }
 
    public static void main(String[] args){
        int k = 2;
        ListNode head;
        Solution sol = new Solution();
        
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
            
        System.out.println("k: " + k);
        System.out.println("before rotate");
        ListNode itr = head;
        while(itr != null){
            System.out.print(itr.val + "->");
            itr = itr.next;
        }
        System.out.println("");
        head = sol.rotateRight(head, k);
        itr = head;
        while(itr != null){
            System.out.print(itr.val + "->");
            itr = itr.next;
        }
        System.out.println("");
    }
}
