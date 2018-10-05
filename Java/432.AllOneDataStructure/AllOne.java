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
        int val;
        Set<String> keys;
        Node next;
        Node prev;
        Node(int v){val = v; keys = new HashSet<>(); next = this; prev = this;} 
    }
    Map<String, Integer> key2Val;
    Map<Integer, Node> val2Node;
    Node head;
    
    /** Initialize your data structure here. */
    public AllOne() {
        key2Val = new HashMap<>();
        val2Node = new HashMap<>();
        head = new Node(0);
        val2Node.put(0, head);
    }
    
    private void addNode(Node prev, String key, int val){
        if(!val2Node.containsKey(val)){
            Node node = new Node(val);
            node.keys.add(key);
            Node next = prev.next;
            prev.next = node;
            next.prev = node;
            node.next = next;
            node.prev = prev;
            val2Node.put(val, node);
        }
        else{
            val2Node.get(val).keys.add(key);
        }
    }
    
    private void deleteNode(int val){
        if(val != 0 && val2Node.get(val).keys.isEmpty()){
            Node node = val2Node.get(val);
            node.next.prev = node.prev;
            node.prev.next = node.next;
            val2Node.remove(val);
        }
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(key2Val.containsKey(key)){
            int val = key2Val.get(key);
            key2Val.put(key, val + 1);
            val2Node.get(val).keys.remove(key);
            addNode(val2Node.get(val), key, val + 1);
            deleteNode(val);
        }
        else{
            key2Val.put(key, 1);
            addNode(val2Node.get(0), key, 1);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!key2Val.containsKey(key)){
            return;
        }
        else{
            int val = key2Val.get(key);
            if(val == 1){
                key2Val.remove(key);
                val2Node.get(1).keys.remove(key);
                deleteNode(1);
            }
            else{
                key2Val.put(key, val - 1);
                val2Node.get(val).keys.remove(key);
                addNode(val2Node.get(val).prev, key, val - 1);
                deleteNode(val);
            }
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return (head.next == head)? "": head.prev.keys.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return (head.next == head)? "": head.next.keys.iterator().next();
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
