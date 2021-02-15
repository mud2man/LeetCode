/* Two pointers: Time:O(1), Space:O(1)
 * 1. Use class Node to implement linkedlist and "cuu" to point the current position
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class BrowserHistory {
    private class Node{
        String url;
        Node next;
        Node prev;
        Node(String u){url = u;}
    }
    Node curr;
    
    public BrowserHistory(String homepage) {
       this.curr = new Node(homepage);
    }
    
    public void visit(String url) {
        curr.next = new Node(url);
        curr.next.prev = curr;
        curr = curr.next;
    }
    
    public String back(int steps) {
        for(int i = 0; i < steps && curr.prev != null; i++){
            curr = curr.prev;
        }
        return curr.url;
    }
    
    public String forward(int steps) {
        for(int i = 0; i < steps && curr.next != null; i++){
            curr = curr.next;
        }
        return curr.url;
    }
  
    public static void main(String[] args){
        BrowserHistory history = new BrowserHistory("leetcode.com");
        String url = "google.com";
        history.visit(url);
        System.out.println("visit " + url);

        url = "facebook.com";
        history.visit(url);
        System.out.println("visit " + url);

        url = "youtube.com";
        history.visit(url);
        System.out.println("visit " + url);

        int step = 1;
        System.out.println("back(" + step + "):" + history.back(step));

        step = 1;
        System.out.println("back(" + step + "):" + history.back(step));

        step = 1;
        System.out.println("forward(" + step + "):" + history.forward(step));

        url = "linkedin.com";
        history.visit(url);
        System.out.println("visit " + url);

        step = 2;
        System.out.println("forward(" + step + "):" + history.forward(step));

        step = 2;
        System.out.println("forward(" + step + "):" + history.back(step));

        step = 7;
        System.out.println("back(" + step + "):" + history.back(step));
    }
}
