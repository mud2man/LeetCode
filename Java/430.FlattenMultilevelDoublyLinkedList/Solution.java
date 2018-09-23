/* DFS: Time:O(n), Space:O(n)
 * 1. Have a utility method "dfs" and return the pair [head, tail]
 * 2. Concatenate the original list with the returned pair
 */

import java.util.*;

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
    public Node() {}
    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};

public class Solution{
    private Node[] dfs(Node head){
        Node[] pair = new Node[2];
        pair[0] = head;
        Node curr = head;
        Node prev = null;
        while(curr != null){
            if(curr.child != null){
                Node[] nextPair = dfs(curr.child);
                Node next = curr.next;
                curr.next = nextPair[0];
                nextPair[0].prev = curr;
                curr.child = null;
                nextPair[1].next = next;
                if(next != null){
                    next.prev = nextPair[1];
                }
                curr = next;
                prev = nextPair[1];
            }
            else{
                prev = curr;
                curr = curr.next;
            }
        }
        pair[1] = prev;
        return pair;
    }
    
    public Node flatten(Node head) {
        dfs(head);
        return head;
    } 

    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println("no test case: ");
    }
}
