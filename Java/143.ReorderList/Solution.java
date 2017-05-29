/* Revese list: O(n)
 * 1. Find the head of the second half list
 * 2. Reverse the second half
 * 3. Insert the reversed second helf into the first half list
 * 
 * ex: {1, 2, 3, 4, 5}
 * time[0]: firstHalf = 1->2->3, secondHalf = 4->5
 * time[1]: firstHalf = 1->2->3, secondHalf = 5->4
 * time[2]: firstHalf = 1->5->2->4->3
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode reverse(ListNode head){
        ListNode curr, next, tmp;
        
        if(head == null || head.next == null){
            return head;
        }
        
        curr = head;
        next = head.next;
        curr.next = null;
        do{
            tmp = next.next;
            next.next = curr;
            curr = next;
            next = tmp;
        }while(next != null);
        
        return curr; 
    }
    
    public void reorderList(ListNode head) {
        ListNode firstHalf, secondHalf, firstHalfCurr, firstHalfNext, secondHalfCurr, secondHalfNext;
        
        if(head == null || head.next == null){
            return;
        }
        
        //find the head of the second half
        firstHalf = head;
        secondHalf = head;
        while(secondHalf != null && secondHalf.next != null){
            firstHalf = firstHalf.next;
            
            if(secondHalf.next != null){
                secondHalf = secondHalf.next.next;
            }
            else{
                secondHalf = null;
            }
        }
        secondHalf = firstHalf.next;
        firstHalf.next = null;
        firstHalf = head;
        
        //reverse the second half
        secondHalf = reverse(secondHalf);
        
        //insert the reversed second helf to the first half
        firstHalfCurr = firstHalf;
        secondHalfCurr = secondHalf;
        while(secondHalfCurr != null){
            firstHalfNext = (firstHalfCurr!= null)? firstHalfCurr.next: null;
            secondHalfNext = (secondHalfCurr != null)? secondHalfCurr.next: null;
            firstHalfCurr.next = secondHalfCurr;
            secondHalfCurr.next = firstHalfNext;
            firstHalfCurr = firstHalfNext;
            secondHalfCurr = secondHalfNext;
        }
    }

	public static void main(String[] args){
		Solution sol;
		ListNode head;
		ListNode node;
		int x;

		sol = new Solution();
		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		
		System.out.println("before reorder: ");
		node = head;
		while(node != null){	
			System.out.print(node.val + "->");
			node = node.next;
		}
		
		sol.reorderList(head);

		System.out.println("\nafter reorder: ");
		node = head;
		while(node != null){
			System.out.print(node.val + "->");
			node = node.next;
		}
		System.out.println("");
	}
}
