/* OneShot: Time:O(n), Space:O(n)
 * 1. Find the length, and store the node into arraylist
 * 2. Reconnect the top and bottom half, given the reduced k
 */

import java.util.*; // Stack

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        
        List<ListNode> list = new ArrayList<>();
        ListNode itr = head;
        while(itr != null){
            list.add(itr);
            itr = itr.next;
        }
        
        k = k % list.size();
        if(k == 0){
            return head;
        }
        
        ListNode newHead = list.get(list.size() - k);
        ListNode newTail = list.get(list.size() - k - 1);
        newTail.next = null;
        list.get(list.size() - 1).next = head;
        return newHead;
    }

    public static void main(String[] args){
        int k = 2;
        ListNode head;
        Solution sol = new Solution();
        
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
            
        System.out.println("k: " + k);
        System.out.println("before rotate");
        ListNode itr = head;
        while(itr != null){
            System.out.print(itr.val + "->");
            itr = itr.next;
        }
        System.out.println("");
        head = sol.rotateRight(head, k);
        itr = head;
        while(itr != null){
            System.out.print(itr.val + "->");
            itr = itr.next;
        }
        System.out.println("");
    }
}
