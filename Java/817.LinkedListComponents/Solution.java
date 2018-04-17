/* Hash: Time:O(n), Space:O(k), where n is list length, k is G's size
 * 1. Put all numbers in G into set
 * 2. Attach two dummy nodes onto the head
 * 3. The condition increasing count is only under: set.contains(nodes[2].val) && !set.contains(nodes[1].val)
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public int numComponents(ListNode head, int[] G) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int num: G){
            set.add(num);
        }
        
        ListNode dummy0 = new ListNode(-1);
        dummy0.next = head;
        ListNode dummy1 = new ListNode(-2);
        dummy1.next = dummy0;
        int count = 0;
        head = dummy1;
        ListNode[] nodes = new ListNode[3];
        nodes[0] = head;
        nodes[1] = (head != null) ? head.next: null;
        nodes[2] = (head != null && head.next != null) ? head.next.next: null;
        while(nodes[2] != null){
            count = (set.contains(nodes[2].val) && !set.contains(nodes[1].val))? count + 1: count;
            nodes[0] = nodes[1];
            nodes[1] = nodes[2];
            nodes[2] = nodes[2].next;
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;
        int[] G = {0, 1, 3};

        sol = new Solution();
        head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        
        System.out.print("list: ");
        node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("\nG: " + Arrays.toString(G));
        
        System.out.println("components#: " + sol.numComponents(head, G));
    }
}
