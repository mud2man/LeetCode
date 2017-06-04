/* Revese list: O(n)
 * 1. Create a dummy node s.t. summy.next = head
 * 2. Find the starting node of reversed list, and do reversing
 * 3. Reconnect the reversed sub-list with the original list
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode tail, rHead, dummy, curr, next, secNext;
        
        if(n <= m){
            return head;
        }
        
        dummy = new ListNode(0);
        dummy.next = head;
        
        //find the starting node
        tail = dummy;
        rHead = head;
        for(int i = 1; i < m; i++){
            tail = rHead;
            rHead = rHead.next;
        }
        
        //reverse
        curr = rHead;
        next = rHead.next;
        secNext = rHead.next.next;
        for(int i = 0; i < (n - m); ++i){
            next.next = curr;
            curr = next;
            next = secNext;
            secNext = (secNext != null)? secNext.next: null;
        }
        
        //reconnect the reversed sub-list with the original list
        rHead.next = next;
        tail.next = curr;
        
        return dummy.next;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;
        int m, n;

        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        System.out.println("before reverse: ");
        node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        m = 2;
        n = 4;
        head = sol.reverseBetween(head, m, n);

        System.out.println("\nafter reorder between " + m + " and " + n);
        node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
