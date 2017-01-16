/* List: O(n)
 * 1. Traverse the linked list and store all the element into the array list
 * 2. Add 1 from the last element of the array list, and ripple carry from it
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
        List<ListNode> numList;
        ListNode node;
        int carry;
        int i;
        
        numList = new ArrayList<ListNode>();
        for(node = head; node != null; node = node.next){
            numList.add(node);
        }
        
        carry = 1;
        for(i = numList.size() - 1; (i >= 0) && (carry == 1); --i){
            numList.get(i).val = numList.get(i).val + carry;
            carry = numList.get(i).val / 10;
            numList.get(i).val = numList.get(i).val % 10;
        }
        
        if(carry == 1){
           node = new ListNode(1);
           node.next = head;
           head = node;
        }
        
        return head;
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
