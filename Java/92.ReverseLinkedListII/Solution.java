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
        if(n == m){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // find the first part
        ListNode firstTail = dummy;
        ListNode secondTail = head;
        for(int i = 1; i < m ; ++i){
            firstTail = secondTail;
            secondTail = secondTail.next;
        }
        
        //reverse the second part
        ListNode curr = secondTail;
        ListNode secondHead = (curr != null)? curr.next: null; 
        ListNode thirdHead = (secondHead != null)? secondHead.next: null;
        secondHead.next = curr;
        for(int j = 1; j < n - m; ++j){
            curr = secondHead;
            secondHead = thirdHead;
            thirdHead = (thirdHead != null)? thirdHead.next: null;
            secondHead.next = curr;
        }

        // link the three parts
        firstTail.next = secondHead;
        secondTail.next = thirdHead;
        return dummy.next;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        System.out.println("before reverse: ");
        ListNode node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        int m = 2;
        int n = 4;
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
