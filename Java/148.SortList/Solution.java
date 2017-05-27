/* Merge sort: O(n)
 * 1. Merge lists with the length of 1*1, 2*2, 4*4, 8*8, ..., in 
 * 2. Modify the pointer in every node when doing merge
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    private class Pair{
        ListNode head;
        ListNode tail;
        Pair(ListNode h, ListNode t){head = h; tail = t;};
    }
    
    public Pair merge(ListNode l1, ListNode l2, int len) {
        Pair pair;
        ListNode nextHead;
        ListNode l3, node;
        int l1Len, l2Len, i;
        
        pair = new Pair(null, null);
        l1Len = len;
        l2Len = len;
        l3 = new ListNode(0);
        
        if(l2 == null){
            pair.head = l1;
            return pair;
        }
        
        //find the next head
        for(i = 0, nextHead = l2; nextHead != null && i < len; i++){
            nextHead = nextHead.next;
        }
        
        //merge both l1 and l2 until any one of the list reach to the end
        node = l3;
        while(l1Len > 0 && l2Len > 0 && l2 != null){
            if(l1.val < l2.val){
                node.next = l1;
                l1 = l1.next;
                l1Len--; 
            }
            else{
                node.next = l2;
                l2 = l2.next;
                l2Len--; 
            }
            node = node.next;
        }
        
        //concatenate the residure
        if(l1Len == 0){
            node.next = l2;
            for(; node != null && l2Len > 0; l2Len--){
                node = node.next;
            }
        }
        else{
            node.next = l1;
            for(; l1Len > 0; l1Len--){
                node = node.next;
            }
        }
        
        //connect the merged list to the rest
        if(node != null){
            node.next = nextHead;
        }
        
        return new Pair(l3.next, node);
    }
    
    public ListNode sortList(ListNode head) {
        ListNode dummy, node, prevTail, mid, tempHead;
        Pair pair;
        int len, i, j;
        
        dummy = new ListNode(0);
        
        //Count the length
        node = head;
        len = 0;
        while(node != null){
            len++;
            node = node.next;
        }
        
        dummy.next = head;
        for(i = 1; i < len; i = i * 2){
            prevTail = dummy;
            do{
                //find middle
                for(mid = prevTail.next, j = 0; j < i && mid != null; ++j){
                    mid = mid.next;
                }
                
                //merge
                pair = merge(prevTail.next, mid, i);
                prevTail.next = pair.head;
                prevTail = pair.tail;
            }
            while(mid != null && prevTail != null);
        }
        return dummy.next;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        ListNode node;

        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        
        System.out.println("before sort ");
        node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        
        head = sol.sortList(head);

        System.out.println("\nafter sort");
        node = head;
        while(node != null){
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
    }
}
