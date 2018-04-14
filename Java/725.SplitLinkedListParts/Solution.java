/* Time:O(n), Space(1)
 * 1. Get the length of the linkedlist, and get the number of larger group
 * 2. Fisrt loop traverse in terms of group
 * 3. Second loop traverse the member of gropp, after that cut the tail of the last element in the group
 */

import java.util.*;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution{
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] parts = new ListNode[k];
        int count = 0;
        ListNode node = root;
        while(node != null){
            count++;
            node = node.next;
        }
        
        node = root;
        int largerGroupCount = count % k;
        for(int i = 0; i < k; ++i){
            int groupSize = (i < largerGroupCount)? (count / k + 1): (count / k); 
            parts[i] = node;
            for(int j = 0; j < (groupSize - 1) && node != null; ++j){
                node = node.next;
            }
            
            if(node != null){
                ListNode tmp = node.next;
                node.next = null;
                node = tmp;
            }
        }
        return parts;
    }

    public static void main(String[] args){
        Solution sol;
        ListNode head;
        int k = 3;

        sol = new Solution();
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        
        System.out.println("k: " + k);
        System.out.println("list: ");
        ListNode node = head;
        while(node != null){    
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("");
        
        ListNode[] groups = sol.splitListToParts(head, k);

        System.out.println("groups:");
        for(ListNode group: groups){
            node = group;
            while(node != null){    
                System.out.print(node.val + "->");
                node = node.next;
            }
            System.out.println("");
        }
    }
}
