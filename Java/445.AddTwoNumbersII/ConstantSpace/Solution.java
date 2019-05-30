/* LinkedList: Time:O(n), Space:O(1)
 * 1. Get the length of l1 and l2
 * 2. Add digit of l1 and l2, and append the value to l3's tail with reversed oreder
 * 3. Reverse l3 and move carry forward
 */

import java.util.*; // Stack

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    private ListNode addTail(int num, ListNode tail){
        ListNode newTail = new ListNode(num);
        newTail.next = tail;
        return newTail;
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //get the length of l1 and l2
        int len1 = 0;
        int len2 = 0;
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        while(ptr1 != null || ptr2 != null){
            len1 += (ptr1 != null)? 1: 0;
            ptr1 = (ptr1 != null)? ptr1.next: ptr1;
            len2 += (ptr2 != null)? 1: 0;
            ptr2 = (ptr2 != null)? ptr2.next: ptr2;
        }
        
        //add digit of l1 and l2, and append the value to l3's tail with reversed oreder
        ListNode l3 = null;
        ptr1 = l1;
        ptr2 = l2;
        while(len1 > 0 && len2 > 0){
            int sum = 0;
            if(len1 >= len2){
                sum += ptr1.val;
                ptr1 = ptr1.next;
                len1--;
            }
            if(len1 < len2){
                sum += ptr2.val;
                ptr2 = ptr2.next;
                len2--;
            }
            l3 = addTail(sum, l3);
        }
        
        //reverse l3 and move carry forward
        int carry = 0;
        ListNode curr = l3;
        ListNode prev = null;
        while(curr != null){
            int sum = carry + curr.val;
            curr.val = sum % 10;
            carry = sum / 10;
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return (carry == 1)? addTail(1, prev): prev;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        
        System.out.print("l1: ");
        for(ListNode node = l1; node != null; node = node.next){
            System.out.print(node.val + "->");
        }
        System.out.println("");

        System.out.print("l2: ");
        for(ListNode node = l2; node != null; node = node.next){
            System.out.print(node.val + "->");
        }
        System.out.println("");

        ListNode l3 = sol.addTwoNumbers(l1, l2) ;
        
        System.out.print("l3: ");
        for(ListNode node = l3; node != null; node = node.next){
            System.out.print(node.val + "->");
        }
        System.out.println("");
    }
}
