/* O(n)
 * 1. Create a dummy node s.t. summy.next = head
 * 2. Use "tail" to record the sub-list without duplicates
 * 3. If curr.next == null || curr.val != curr.next.val, tail.next = curr and curr = curr.next 
 * 4. Otherwise, shift curr until curr.val != preVal
 * 
 * ex: list = 1->2->3->3->3->4->4->5, tail = list[0], preTail = dummy (dummy.val = 0)
 *            0  1  2  3  4  5  6  7
 * time[0]: curr = list[0], tail = list[0], subList = dummy->0
 * time[1]: curr = list[1], tail = list[0], subList = dummy->0->1
 * time[2]: curr = list[2], tail = list[2], subList = dummy->0->1->2
 * time[3]: curr = list[5], tail = list[2], subList = dummy->0->1->2
 * time[4]: curr = list[7], tail = list[2], subList = dummy->0->1->2->5
 * time[5]: curr = list[8], tail = list[7], subList = dummy->0->1->2->5
 * time[6]: curr = list[8], tail = list[7], subList = dummy->0->1->2->5->null
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
        ListNode dummy = new ListNode(0);
        ListNode tail, curr;
        int preVal;
        dummy.next = head;
        
        curr = head;
        tail = dummy;
        
        while(curr != null){
            if(curr.next == null || curr.val != curr.next.val){
                tail.next = curr;
                tail = curr;
                curr = curr.next;
            }
            else{
                preVal = curr.val;
                while(curr != null && curr.val == preVal){
                    curr = curr.next;
                }
            }
        } 
        tail.next = null;
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
