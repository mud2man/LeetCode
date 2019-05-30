/* Stack: O(n)
 */

import java.util.*; // Stack

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stackL1 = new LinkedList<Integer>();
        Deque<Integer> stackL2 = new LinkedList<Integer>();
        
        ListNode itr = l1;
        while(itr != null){
            stackL1.add(itr.val);
            itr = itr.next;
        }
        itr = l2;
        while(itr != null){
            stackL2.add(itr.val);
            itr = itr.next;
        }
        
        int carry = 0;
        ListNode head = null;
        while(!stackL1.isEmpty() || !stackL2.isEmpty()){
            int valueL1 = (stackL1.isEmpty())? 0: stackL1.pollLast();
            int valueL2 = (stackL2.isEmpty())? 0: stackL2.pollLast();
            int valueL3 = valueL1 + valueL2 + carry;
            carry = valueL3 / 10;
            valueL3 = valueL3 % 10;
            ListNode newHead = new ListNode(valueL3);
            newHead.next = head;
            head = newHead;
        }
        
        if(carry == 1){
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            head = newHead;
        }
        return head;
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
