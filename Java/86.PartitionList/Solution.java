/* QuickSort partion: O(n)
 * 1. Keep the pointer of the last node which are smaller than x
 * 2. If currNode.val < x, cut of this node, and append to the last node with value smaller than x
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode partition(ListNode head, int x) {
        ListNode lastSmallNode;
        ListNode currNode;
        ListNode prevNode;
        ListNode tmpNode;
        
        lastSmallNode = null;
        prevNode = null;
        currNode = head;
        
        //find the first node with val >= x
        while(currNode != null){
            if(currNode.val < x){
                prevNode = currNode;
                currNode = currNode.next;
            }
            else{
                break;
            }
        }
        lastSmallNode = prevNode;
        
        while(currNode != null){
            if(currNode.val < x){
                if(lastSmallNode == null){
                    lastSmallNode = currNode;
                    currNode = currNode.next;
                    prevNode.next = currNode;
                    lastSmallNode.next = head;
                    head = lastSmallNode;
                }
                else{
                    tmpNode = currNode;
                    currNode = currNode.next;
                    prevNode.next = currNode;
                    tmpNode.next = lastSmallNode.next;
                    lastSmallNode.next = tmpNode;
                    lastSmallNode = tmpNode;
                }
            }
            else{
                prevNode = currNode;
                currNode = currNode.next;
            }
        }
        
        return head;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;
        int x;

        x = 3;
        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        
        System.out.println("before partition by " + x);
        node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        sol.partition(head, x);

        System.out.println("\nafter partition by " + x);
        node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
