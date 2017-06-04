/* O(n)
 * 1. Create a dummy node s.t. summy.next = head
 * 2. Use "tail" to record the sub-list without duplicates, and "preTail" to record the previous node of tail
 * 3. Only go back from "tail" to "preTail" only on the first time encountered consecutive nodes
 * 
 * ex: list = 1->2->3->3->3->4->4->5, tail = list[0], preTail = dummy (dummy.val = 0)
 *            0  1  2  3  4  5  6  7
 * time[0]: currNode = list[1], tail = list[0], preTail = dummy, subList = 0->1
 * time[1]: currNode = list[1], tail = list[1], preTail = list[0], subList = 0->1->2
 * time[2]: currNode = list[2], tail = list[2], preTail = list[1], subList = 0->1->2->3
 * time[3]: currNode = list[3], tail = list[1], preTail = list[1], subList = 0->1->2
 * time[4]: currNode = list[4], tail = list[1], preTail = list[1], subList = 0->1->2
 * time[5]: currNode = list[5], tail = list[5], preTail = list[1], subList = 0->1->2->4
 * time[6]: currNode = list[6], tail = list[1], preTail = list[1], subList = 0->1->2
 * time[7]: currNode = list[7], tail = list[7], preTail = list[1], subList = 0->1->2->5
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy, tail, prevTail, currNode, preNode;
        int currVal, prevVal;
        
        if(head == null){
            return head;
        }
        
        dummy = new ListNode(head.val - 1);
        dummy.next = head;
        prevTail = dummy;
        tail = head;
        prevVal = head.val;
        
        currNode = head.next;
        preNode = head;
        while(currNode != null){
            currVal = currNode.val;
            if(prevVal != currVal){
                tail.next = currNode;
                prevTail = tail;
                tail = currNode;
                prevVal = currVal;
            }
            else{
                //only go back on the first time encountered consecutive nodes
                if(tail.val == currNode.val){
                    tail = prevTail;
                }
            }
            preNode = currNode;
            currNode = currNode.next;
        }
        
        //correct if the list has the same value on the first 2 nodes
        if(tail != preNode){
            tail.next = null;
        }
        
        return dummy.next;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;
        int m, n;

        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next.next = new ListNode(5);
        
        System.out.println("before remove duplictes: ");
        node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
        
        head = sol.deleteDuplicates(head);

        System.out.println("after remove duplictes: ");
        node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
