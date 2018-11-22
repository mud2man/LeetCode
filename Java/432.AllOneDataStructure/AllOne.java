/* Circular queue and Has: O(1)
 * 1. Have an circular queue with "head", where head is the node stored 0, head.next stored minimum, head.prev stored maximum
 * 2. Have "key2Value" to store key to value map
 * 3. Have "value2Keys" to store key sets with the same value
 * 4. Have "value2Node" to store value to node of circular queue
 * 5. When update circular queue, need to do add, and then delete
 * 6. When deleteNode, need to get the previous node of inserted node
 * 7. When addNode, just delete the node
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
    Map<String, Integer> key2Freq;
    Map<Integer, Node> freq2Node;
    
    /** Initialize your data structure here. */
    public AllOne() {
        head = new Node(0);
        key2Freq = new HashMap<>();
        freq2Node = new HashMap<>();
        freq2Node.put(0, head);
    }
    
    private void add(Node prev, int freq, String key){
        if(!freq2Node.containsKey(freq)){
            Node newNode = new Node(freq);
            newNode.keys.add(key);
            Node next = prev.next;
            newNode.prev = prev;
            newNode.next = next;
            prev.next = newNode;
            next.prev = newNode;
            freq2Node.put(freq, newNode);
        }
        freq2Node.get(freq).keys.add(key);
    }
    
    private void delete(Node node, String key){
        node.keys.remove(key);
        if(node.keys.isEmpty()){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            freq2Node.remove(node.freq);
        }
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(!key2Freq.containsKey(key)){
            key2Freq.put(key, 1);
            add(head, 1, key);
        }
        else{
            key2Freq.put(key, key2Freq.get(key) + 1);
            int freq = key2Freq.get(key);
            add(freq2Node.get(freq - 1), freq, key);
            delete(freq2Node.get(freq - 1), key);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!key2Freq.containsKey(key)){
            return;
        }
    
        if(key2Freq.get(key) == 1){
            key2Freq.remove(key);
            delete(freq2Node.get(1), key);
        }
        else{
            key2Freq.put(key, key2Freq.get(key) - 1);
            int freq = key2Freq.get(key);
            add(freq2Node.get(freq + 1).prev, freq, key);
            delete(freq2Node.get(freq + 1), key);
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
