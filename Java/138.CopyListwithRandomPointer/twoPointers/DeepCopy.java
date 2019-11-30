/* Two pointers: Time:O(n), Space:O(1)
 * 1. Put the clone node to its parent node's next
 * 2. Assigne the random of the clone nodes
 * 3. Recover the original list, and retrieve the clone list
 */

import java.util.*;

class Node {
    public int val;
    public Node next;
    public Node random;
    public Node() {}
    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};

public class DeepCopy {
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        
        //put the clone node to its node's next
        Node ptr0 = head;
        Node ptr1 = head.next;
        while(ptr0 != null){
            Node clone = new Node(ptr0.val, ptr1, null);
            ptr0.next = clone;
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
        Node cloneHead = head.next;
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
        DeepCopy dc = new DeepCopy();
        Node head = new Node(1, null, null);
        head.next = new Node(2, null, null);
        head.next.next = new Node(3, null, null);
        head.random = head.next;
        head.next.random = head.next.next;
        head.next.next.random = head;

        System.out.println("head:");
        Node node = head;
        while(node != null){
            System.out.println("node.val:" + node.val);
            System.out.println("node.random.val:" + node.random.val);
            node = node.next;
        }

        Node cloneHead = dc.copyRandomList(head);
        
        System.out.println("cloneHead:");
        node = cloneHead;
        while(node != null){
            System.out.println("node.val:" + node.val);
            System.out.println("node.random.val:" + node.random.val);
            node = node.next;
        }

    }
}
