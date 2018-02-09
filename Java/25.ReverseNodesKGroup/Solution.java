/* Time:O(n) Space:O(1)
 * 1. Find the head and tail pointers for every k-group
 * 2. Reverse the members in the k-group, and find the next group
 * 3. Repeat step 2 until iterator = null or iterator.next != null
 */

import java.util.*;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    private void reverse(ListNode head, ListNode tail){
        if(head == tail){
            return;
        }

        ListNode[] ptrs = {head, head.next, head.next.next};
        while(ptrs[0] != tail){
            ptrs[1].next = ptrs[0];
            ptrs[0] = ptrs[1];
            ptrs[1] = ptrs[2];
            ptrs[2] = (ptrs[2] != null)? ptrs[2].next: null;
        }
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode iterator = dummy;
        
        while(iterator != null && iterator.next != null){
            ListNode previousTail = iterator;
            head = iterator.next;
            int i = 0;
            for(i = 0; (i < k) && (iterator.next != null); ++i){
                iterator = iterator.next;
            }
            
            if(i < k){
                break;
            }
            else{
                ListNode tail = iterator;
                ListNode nextHead = tail.next;
                reverse(head, tail);
                previousTail.next = tail;
                head.next = nextHead;
                iterator = head;
            }
        }
        
        return dummy.next;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;
        int k = 2;

        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        System.out.println("k: " + k);
        System.out.println("before reverse: ");
        node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        head = sol.reverseKGroup(head, k);
        System.out.println("\nafter reverse: ");
        node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
