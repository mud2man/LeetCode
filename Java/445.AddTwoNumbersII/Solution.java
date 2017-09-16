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
            Stack<Integer> stackL1 = new Stack<Integer>();
        Stack<Integer> stackL2 = new Stack<Integer>();
        
        ListNode itr = l1;
        while(itr != null){
            stackL1.push(itr.val);
            itr = itr.next;
        }
        itr = l2;
        while(itr != null){
            stackL2.push(itr.val);
            itr = itr.next;
        }
        
        int carry = 0;
        ListNode head = null;
        while(!stackL1.empty() || !stackL2.empty()){
            int valueL1 = (stackL1.empty())? 0: stackL1.pop();
            int valueL2 = (stackL2.empty())? 0: stackL2.pop();
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
        Solution sol;
        ListNode l1, l2, l3, node;

        sol = new Solution();
        l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);
        l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        
        System.out.print("l1: ");
        for(node = l1; node != null; node = node.next){
            System.out.print(node.val + "->");
        }
        System.out.println("");

        System.out.print("l2: ");
        for(node = l2; node != null; node = node.next){
            System.out.print(node.val + "->");
        }
        System.out.println("");

        l3 = sol.addTwoNumbers(l1, l2) ;
        
        System.out.print("l3: ");
        for(node = l3; node != null; node = node.next){
            System.out.print(node.val + "->");
        }
        System.out.println("");
    }
}
