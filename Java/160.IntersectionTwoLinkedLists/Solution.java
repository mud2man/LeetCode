/* Time:O(n), Space:O(1)
 * 1. Get the length for both list
 * 2. Move forward the longer list with the "difference of length" times
 * 3. Start compare, if hit the same node, then return it
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    private int getLength(ListNode head){
        int length = 0;
        while(head != null){
            length++;
            head = head.next;
        }
        return length;
    }
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        
        if(lengthA > lengthB){
            for(int i = 0; i < (lengthA - lengthB); ++i){
                nodeA = nodeA.next;
            }
        }
        else if(lengthA < lengthB){
            for(int i = 0; i < (lengthB - lengthA); ++i){
                nodeB = nodeB.next;
            }
        }
        
        while(nodeA != null && nodeB != null){
            if(nodeA != nodeB){
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
            else{
                return nodeA;
            }
        }
        
        return null;
    } 
    public static void main(String[] args){
        Solution sol;
        ListNode headA;
        ListNode headB;
        ListNode node;

        sol = new Solution();
        headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        headA.next.next.next = new ListNode(4);
        headB = new ListNode(5);
        headB.next = headA.next.next;
        
        System.out.println("listA: ");
        node = headA;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
        
        System.out.println("listB: ");
        node = headB;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
        
        ListNode intersection = sol.getIntersectionNode(headA, headB);

        System.out.println("intersection: " + intersection.val);
    }
}
