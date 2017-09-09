/* List: O(n)
 * 1. Have a dummy node dummy, where dummy.next = head
 * 2. Fine the last node lessThanNine which is less than 9
 * 3. Let lessThanNine.val = (lessThanNine.val + 1) % 10, and traverse to left most node
 * 4. If dummy.val > 0, return dummy, otherwise return head
 */

import java.util.*;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode itr = dummy;
        ListNode lessThanNine = dummy;
        while(itr != null){
            if(itr.val < 9){
                lessThanNine = itr;
            }
            itr = itr.next;
        }
        
        while(lessThanNine != null){
            lessThanNine.val = (lessThanNine.val + 1) % 10;
            lessThanNine = lessThanNine.next;
        }

        if(dummy.val > 0){
            return dummy;
        }
        else{
            return head;
        }
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;

        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        
        System.out.println("before add one: ");
        node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        head = sol.plusOne(head);

        System.out.println("\nafter add one: ");
        node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
