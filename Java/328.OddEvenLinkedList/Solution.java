/* Simple traversal: O(n)
 * 1. Create oddList and evenList while oddList keep odd nodes, and evenList keep even nodes
 * 2. Traverse the input list, and dispatch nodes to these two lists
 * 3. Append evenList to the end of oddList
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode oddEvenList(ListNode head) {
        ListNode oddList;
        ListNode evenList;
        ListNode itr;
        ListNode oddItr;
        ListNode evenItr;
        int count;
        
        oddList = new ListNode(0);
        evenList = new ListNode(0);
        
        count = 1;
        itr = head;
        oddItr = oddList; 
        evenItr = evenList;
        //dispatch node to oddList and evenList
        while(itr != null){
            if((count % 2) == 1){
                oddItr.next = itr;
                oddItr = oddItr.next;
            }
            else{
                evenItr.next = itr;
                evenItr = evenItr.next;
            }
            itr = itr.next;
            count++;
        }
        
        //append evenList to oddList
        oddItr.next = evenList.next;
        evenItr.next = null;
        
        return oddList.next;
    }

	public static void main(String[] args){
		Solution sol;
		ListNode head;
		ListNode node;
		int x;

		x = 3;
		sol = new Solution();
		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		
		System.out.println("before split: ");
		node = head;
		while(node != null){	
			System.out.print(node.val + "->");
			node = node.next;
		}
		
		sol.oddEvenList(head);

		System.out.println("\nafter split: ");
		node = head;
		while(node != null){
			System.out.print(node.val + "->");
			node = node.next;
		}
		System.out.println("");
	}
}
