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
    public ListNode getMid(ListNode prev, int window){
        ListNode mid = prev;
        for(int i = 0; i <= window; ++i){
            if(mid != null)
                mid = mid.next;
            else
                return null;
        }
        return mid;
    }
    
    //merge and return the tail of the merged list
    public ListNode merge(ListNode prev, ListNode mid, int window){
        ListNode head0 = prev.next;
        ListNode tail = prev;
        ListNode head1 = mid;
        int len0 = 0;
        int len1 = 0;
        
        ListNode nextHead = head1;
        for(int i = 0; i < window && nextHead != null; ++i)
            nextHead = nextHead.next;
        
        while(head1 != null && len0 < window && len1 < window){
            if(head0.val < head1.val){
                tail.next = head0;
                head0 = head0.next;
                len0++;
            }
            else{
                tail.next = head1;
                head1 = head1.next;
                len1++;
            }
            tail = tail.next;
        }
        
        if(len1 == window || head1 == null){
            tail.next = head0;
            for(int i = len0; i < window && tail.next != null; i++)
                tail = tail.next;
        }
        
        if(len0 == window){
            tail.next = head1;
            for(int i = len1; i < window && tail.next != null; i++)
                tail = tail.next;
        }
        
        tail.next = nextHead;
        return tail;
    }

    public ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode mid, prev;
        
        //get the lenth of the list
        ListNode curr = head;
        int len = 0;
        while(curr != null){
            len++;
            curr = curr.next;
        }
        
        int window = 1;
        while(window < len){
            prev = dummy;
            do{
                mid = getMid(prev, window);
                prev = merge(prev, mid, window);
            }while(prev != null && prev.next != null);
            window = window * 2;
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
