/* Reservoir sampling: O(n)
 * 1. Traverse and use random number to generate integer from 1 to i to decide if the value need be changed
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    private ListNode head;
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        Random rand = new Random(); 
        int val = 0;
        ListNode node = head;
        int idx = 0;
        while(node != null){
            val = (rand.nextInt(idx + 1) == 0)? node.val: val;
            idx++;
            node = node.next;
        }
        return val;
    }
 
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        Solution sol = new Solution(head);

        System.out.print("list: ");
        ListNode node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");

        System.out.println("random: " + sol.getRandom());
        System.out.println("random: " + sol.getRandom());
        System.out.println("random: " + sol.getRandom());
    }
}
