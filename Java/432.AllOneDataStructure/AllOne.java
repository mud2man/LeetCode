/* Circular queue and Hash: O(1)
 * 1. Have an circular queue with "head", where head is the node stored 0, head.next stored minimum, head.prev stored maximum
 * 2. Have "key2Node" to store key to node of circular queue
 * 3. When update circular queue, need to do add, and then delete
 * 4. When deleteNode, need to get the previous node of inserted node
 * 5. When addNode, just delete the node
 */

import java.util.*;

public class AllOne {
        private class Node{
        int freq;
        Set<String> keys;
        Node next;
        Node prev;
        Node(int f){freq = f; keys = new HashSet<>(); next = this; prev = this;}
    }
    Node head;
    Map<String, Node> key2Node;
    
    /** Initialize your data structure here. */
    public AllOne() {
        head = new Node(0);
        key2Node = new HashMap<>();
    }
    
    private void add(Node prev, int freq, String key){
        if(prev.next.freq == freq){
            key2Node.put(key, prev.next);
        }
        else{
            Node newNode = new Node(freq);
            key2Node.put(key, newNode);
            Node next = prev.next;
            newNode.prev = prev;
            newNode.next = next;
            prev.next = newNode;
            next.prev = newNode;
        }
        key2Node.get(key).keys.add(key);
    }
    
    private void delete(Node node, String key){
        node.keys.remove(key);
        if(node.keys.isEmpty()){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        if(key2Node.get(key) == node){
            key2Node.remove(key);
        }
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(!key2Node.containsKey(key)){
            add(head, 1, key);
        }
        else{
            Node curr = key2Node.get(key);
            add(curr, curr.freq + 1, key);
            delete(curr, key);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!key2Node.containsKey(key)){
            return;
        }
    
        Node curr = key2Node.get(key);
        if(key2Node.get(key).freq == 1){
            delete(curr, key);
        }
        else{
            add((curr.prev.freq < curr.freq - 1)? curr.prev : curr.prev.prev, curr.freq - 1, key);
            delete(curr, key);
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return (head.prev != head)? head.prev.keys.iterator().next(): "";
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return (head.next != head)? head.next.keys.iterator().next(): "";
    }
  
    public static void main(String[] args){
        AllOne obj = new AllOne();;
        String key;

        key = "abc";
        obj.inc(key);
        System.out.println("inc(" + key + ")");

        key = "abc";
        obj.inc(key);
        System.out.println("inc(" + key + ")");
        
        key = "def";
        obj.inc(key);
        System.out.println("inc(" + key + ")");
        
        key = "def";
        obj.inc(key);
        System.out.println("inc(" + key + ")");

        key = "def";
        obj.dec(key);
        System.out.println("dec(" + key + ")");

        System.out.println("getMaxKey: " + obj.getMaxKey());
        System.out.println("getMinKey: " + obj.getMinKey());
    }
}
