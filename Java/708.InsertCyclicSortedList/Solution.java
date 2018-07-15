/* Binary search: Time:O(n), Space:O(1)
 * 1. Traverse the linklist, and insert the new node into correct position
 */

import java.util.*;

class Node {
    public int val;
    public Node next;
    public Node() {}
    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};

public class Solution{
    public Node insert(Node head, int insertVal) {
        if(head == null){
            Node node = new Node();
            node.next = node;
            node.val = insertVal;
            return node;
        }
        
        Node itr;
        Set<Node> visited = new HashSet<>();
        if(head.val > insertVal){
            itr = head;
            //get tail
            while(itr.next.val >= itr.val && !visited.contains(itr)){
                visited.add(itr);
                itr = itr.next;
            }
            
            while(itr.next.val < insertVal && !visited.contains(itr)){
                visited.add(itr);
                itr = itr.next;
            }
            Node newNode = new Node(insertVal, itr.next);
            itr.next = newNode;
        }
        else if(head.val == insertVal){
            Node newNode = new Node(insertVal, head.next);
            head.next = newNode;
        }
        else{
            itr = head;
            while(itr.next.val < insertVal && itr.next.val >= itr.val && !visited.contains(itr)){
                visited.add(itr);
                itr = itr.next;
            }
            Node newNode = new Node(insertVal, itr.next);
            itr.next = newNode;
        }
        
        return head;
    }
    
    private void traverse(Node head){
        Set<Node> visited = new HashSet<>();
        while(!visited.contains(head)){
            System.out.print(head.val + "->");
            visited.add(head);
            head = head.next;
        }
        System.out.println("");
    } 
 
    public static void main(String[] args){
        Solution sol = new Solution();
        Node head = new Node(3, null);
        head.next = new Node(4, null);
        head.next.next = new Node(1, null);
        head.next.next.next = head;
        
        System.out.println("Before insert:");
        sol.traverse(head);
        sol.insert(head, 2);
        System.out.println("After insert:");
        sol.traverse(head);
    }
}
