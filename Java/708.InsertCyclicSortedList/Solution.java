/* Two pointers: Time:O(n), Space:O(1)
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
        Node newNode = new Node();
        newNode.val = insertVal;
        if(head == null){
            newNode.next = newNode;
            return newNode;
        } 
        else if(head.next == head){
            newNode.next = head;
            head.next = newNode;
            return head;
        } 
        else{
            Node prev = head;
            Node curr = head.next;
            while(curr != head){
                if(prev.val < curr.val && prev.val <= insertVal && curr.val >= insertVal){
                    break;
                }
                if(prev.val > curr.val && (prev.val <= insertVal || curr.val >= insertVal)){
                    break;
                }
                prev = curr;
                curr = curr.next;
            }
            newNode.next = curr;
            prev.next = newNode;
            return head;
        }
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
