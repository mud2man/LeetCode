/* Two pointers: Time:O(n), Space:O(1)
 * 1. Put the clone node to its parent node's next
 * 2. Assigne the random of the clone nodes
 * 3. Recover the original list, and retrieve the clone list
 */

import java.util.*;

class RandomListNode {
    int label;
    RandomListNode next, random;
    
    RandomListNode(int x) {
        this.label = x;
    }
}

public class DeepCopy {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null){
            return null;
        }
        
        //put the clone node to its node's next
        RandomListNode ptr0 = head;
        RandomListNode ptr1 = head.next;
        while(ptr0 != null){
            RandomListNode clone = new RandomListNode(ptr0.label);
            ptr0.next = clone;
            clone.next = ptr1;
            ptr0 = ptr1;
            ptr1 = (ptr1 != null)? ptr1.next: null;
        }
        
        //assigne the random of the clone nodes
        ptr0 = head;
        ptr1 = head.next;
        while(ptr0 != null){
            ptr1.random = (ptr0.random != null)? ptr0.random.next: null;
            ptr0 = ptr1.next;
            ptr1 = (ptr0 != null)? ptr0.next: null;
        }
        
        //recover the original list, and retrieve the clone list
        RandomListNode cloneHead = head.next;
        ptr0 = head;
        ptr1 = head.next;
        while(ptr1 != null){
            ptr0.next = ptr1.next;
            ptr0 = ptr1;
            ptr1 = (ptr1 != null)? ptr1.next: null;
        }
        return cloneHead;
    }
 
    public static void main(String[] args){
        RandomListNode head;
        RandomListNode cloneHead;
        RandomListNode node;
        DeepCopy dc;

        dc = new DeepCopy();

        head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.next = new RandomListNode(3);
        head.random = head.next;
        head.next.random = head.next.next;
        head.next.next.random = head;

        System.out.println("head:");
        node = head;
        while(node != null){
            System.out.println("node.label:" + node.label);
            System.out.println("node.random.label:" + node.random.label);
            node = node.next;
        }

        cloneHead = dc.copyRandomList(head);
        
        System.out.println("cloneHead:");
        node = cloneHead;
        while(node != null){
            System.out.println("node.label:" + node.label);
            System.out.println("node.random.label:" + node.random.label);
            node = node.next;
        }

    }
}
