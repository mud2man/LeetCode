/* Revese list: Time:O(n), Space:O(1)
 * 1. Find the head of the second half list
 * 2. Reverse the second half
 * 3. Intersect the reversed second helf into the first half list
 * 
 * ex: {1, 2, 3, 4, 5}
 * time[0]: firstHalf = 1->2->3, secondHalf = 4->5
 * time[1]: firstHalf = 1->2->3, secondHalf = 5->4
 * time[2]: firstHalf = 1->5->2->4->3
 */

import java.util.*;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public void reorderList(ListNode head) {
        if(head == null){
            return;
        }
        
        // find the middle node
        int halfLen = 0;
        ListNode next = head;
        ListNode secondNext = head;
        while(secondNext.next != null && secondNext.next.next != null){
            next = next.next;
            secondNext = secondNext.next.next;
            halfLen++;
        }
        halfLen = (secondNext.next != null)? halfLen + 1: halfLen;
        
        // reverse the second half list
        ListNode curr = next;
        next = (curr != null)? curr.next: null;
        secondNext = (next != null)? next.next: null;
        while(next != null){
            next.next = curr;
            curr = next;
            next = secondNext;
            secondNext = (secondNext != null)? secondNext.next: null;
        }
        
        // intersect
        ListNode tail = curr;
        curr = head;
        for(int i = 0; i < halfLen; ++i){
            ListNode temp0 = curr.next;
            ListNode temp1 = tail.next;
            curr.next = tail;
            tail.next = temp0;
            curr = temp0;
            tail = temp1;
        }
        curr.next = null;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;
        int x;

        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        System.out.println("before reorder: ");
        node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        sol.reorderList(head);

        System.out.println("\nafter reorder: ");
        node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
