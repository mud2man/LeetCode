/* Two Pointers: Time:O(n), Space:O(1)
 * 1. Have ptrA starting at headA, ptrB starting at headB
 * 2. When ptrA reach to the end, swith to headB, ptrB do the same thing
 * 3. Ih there is an intersection, ptrA and ptrB will meet at the intersection
 */

import java.util.*;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ptrA = headA;
        ListNode ptrB = headB;
        boolean hitEndA = false;
        boolean hitEndB = false;
        
        while(ptrA != null && ptrB != null){
            if(ptrA == ptrB){
                return ptrA;
            }
            else{
                if(ptrA.next != null){
                    ptrA = ptrA.next;
                }
                else{
                    if(!hitEndA){
                        ptrA = headB;
                        hitEndA = true;
                    }
                    else{
                        ptrA = null;
                    }
                }
                
                if(ptrB.next != null){
                    ptrB = ptrB.next;
                }
                else{
                    if(!hitEndB){
                        ptrB = headA;
                        hitEndB = true;
                    }
                    else{
                        ptrB = null;
                    }
                }
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
