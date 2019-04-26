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
        if(head == null){
            return new Node[2];
        }
        
        Node[] pair = new Node[2];
        pair[0] = head;
        Node[] childPair = dfs(head.child);
        Node[] nextPair = dfs(head.next);
        head.child = null;
        if(childPair[0] != null && nextPair[0] != null){
            childPair[1].next = nextPair[0];
            nextPair[0].prev = childPair[1];
            head.next = childPair[0];
            childPair[0].prev = head;
            pair[1] = nextPair[1];
        }
        else if(childPair[0] != null){
            head.next = childPair[0];
            childPair[0].prev = head;
            pair[1] = childPair[1];
        }
        else if(nextPair[0] != null){
            head.next = nextPair[0];
            nextPair[0].prev = head;
            pair[1] = nextPair[1];
        }
        else{
            pair[1] = head;
        }
        return pair;
    }
    
    public Node flatten(Node head) {
        return dfs(head)[0];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println("no test case: ");
    }
}
