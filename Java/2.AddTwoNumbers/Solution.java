/* Math: Time:O(n), Space:O(1)
 */         

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode leftNode = l1;
        ListNode rightNode = l2;
        ListNode resultNode = dummy;
        int carry = 0;
        while(leftNode != null || rightNode != null){
            int left = (leftNode != null)? leftNode.val: 0;
            int right = (rightNode != null)? rightNode.val: 0;
            int digit = (left + right + carry) % 10;
            carry = (left + right + carry) / 10;
            resultNode.next = new ListNode(digit);
            resultNode = resultNode.next;
            leftNode = (leftNode != null)? leftNode.next: null;
            rightNode = (rightNode != null)? rightNode.next: null;
        }
    
        if(carry > 0){
            resultNode.next = new ListNode(carry);
        }
        return dummy.next;
    }

    private void dump(ListNode l){
        ListNode itr = l;
        while(itr != null){
            System.out.print(Integer.toString(itr.val) + ", ");
            itr = itr.next;
        }
        System.out.println("");
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(8);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println("l1: ");
        sol.dump(l1);
        System.out.println("l2: ");
        sol.dump(l2);
        System.out.println("l3: ");
        sol.dump(sol.addTwoNumbers(l1, l2));
    }
}
