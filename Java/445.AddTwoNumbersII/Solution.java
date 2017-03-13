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
        ListNode l1Node, l2Node, l3Node, l3;
        Stack<ListNode> l1Stack, l2Stack;
        int l1Val, l2Val, l3Val, carry;
        
        l1Stack = new Stack<ListNode>();
        l2Stack = new Stack<ListNode>();
        
        l1Node = l1;
        while( l1Node != null){
           l1Stack.push(l1Node);
           l1Node = l1Node.next;
        }
        
        l2Node = l2;
        while( l2Node != null){
           l2Stack.push(l2Node); 
           l2Node = l2Node.next;
        }
        
        l3 = null;
        carry = 0;
        do{
            l1Val = (!l1Stack.isEmpty())? l1Stack.pop().val: 0;
            l2Val = (!l2Stack.isEmpty())? l2Stack.pop().val: 0;
            l3Val = (l1Val + l2Val + carry) % 10;
            carry = (l1Val + l2Val + carry) / 10;
            l3Node = new ListNode(l3Val);
            l3Node.next = l3;
            l3 = l3Node;
        }while(!l1Stack.isEmpty() || !l2Stack.isEmpty() || carry != 0);
        
        return l3;
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
