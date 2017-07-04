/* QuickSort partion: O(n)
 * 1. Keep the pointer of the last node which are smaller than x
 * 2. If currNode.val < x, cut of this node, and append to the last node with value smaller than x
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(0);
        ListNode tail, curr, next, prev;
        
        dummy.next = head;
        tail = dummy;
        curr = head;
        prev = dummy;
        
        while(curr != null){
            if(curr.val < x){
                if(tail.next != curr){
                    next = curr.next;
                    curr.next = tail.next;
                    tail.next = curr;
                    tail = tail.next;
                    curr = next;
                    prev.next = curr;
                }
                else{
                    tail = tail.next;
                    prev = curr;
                    curr = curr.next;
                }
            }
            else{
                prev = curr;
                curr = curr.next;
            }
        }
        
        if(prev != dummy){
            prev.next = null;
        }
        
        return dummy.next;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;
        int x;

        x = 3;
        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        
        System.out.println("before partition by " + x);
        node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        sol.partition(head, x);

        System.out.println("\nafter partition by " + x);
        node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
