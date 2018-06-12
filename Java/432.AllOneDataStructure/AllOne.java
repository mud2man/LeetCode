/* Circular queue and Has: O(1)
 * 1. Have an circular queue with "head", where head is the node stored minumum value, and head.prev stored maximum value
 * 2. Have "key2Value" to store key to value map
 * 3. Have "value2Keys" to store key sets with the same value
 * 4. Have "value2Node" to store value to node of circular queue
 * 5. Need to update circular queue if value2Keys.get(preValue) is empty
 */

import java.util.*;

public class AllOne {
    private class Node{
        int value;
        Node next;
        Node prev;
        Node(int v){next = this; prev = this; value = v;}
    }
    
    Node head;
    Map<String, Integer> key2Value;
    Map<Integer, Set<String>> value2Keys;
    Map<Integer, Node> value2Node;
    
    /** Initialize your data structure here. */
    public AllOne() {
        head = null;
        key2Value = new HashMap<String, Integer>();
        value2Keys = new HashMap<Integer, Set<String>>();
        value2Node = new HashMap<Integer, Node>();
    }
    
    private Node deleteNode(Node node){
        if(node.next == node){
            head = null;
            return null;
        }
        else{
            if(node == head){
                head = node.next;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node.prev;
        }
    }
    
    private void addNode(Node prevNode, Node nextNode){
        if(prevNode == null){
            head = nextNode;
        }
        else{
            if(nextNode.value < head.value){
                head = nextNode;
            }
            nextNode.prev = prevNode;
            nextNode.next = prevNode.next;
            nextNode.prev.next = nextNode;
            nextNode.next.prev = nextNode;
        }
    }
    
    private void update(int preValue, String key){
        Node prevNode = null;
        if(value2Keys.containsKey(preValue)){
            value2Keys.get(preValue).remove(key);
            if(value2Keys.get(preValue).isEmpty()){
                Node node = value2Node.get(preValue);
                value2Node.remove(preValue);
                value2Keys.remove(preValue);
                prevNode = deleteNode(node);
            }
            else{
                prevNode = value2Node.get(preValue);
            }
        }
        else{
            prevNode = (head != null)? head.prev: null;
        }
        
        if(key2Value.containsKey(key)){
            int newValue = key2Value.get(key);
            value2Keys.putIfAbsent(newValue, new HashSet<String>());
            if(value2Keys.get(newValue).isEmpty()){
                Node nextNode = new Node(newValue);
                addNode(prevNode, nextNode);
                value2Node.put(newValue, nextNode);
            }
            value2Keys.get(newValue).add(key);
        }
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        key2Value.putIfAbsent(key, 0);
        key2Value.put(key, key2Value.get(key) + 1);
        update(key2Value.get(key) - 1, key);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!key2Value.containsKey(key)){
            return;
        }
        key2Value.put(key, key2Value.get(key) - 1);
        if(key2Value.get(key) == 0){
            key2Value.remove(key);
        }
        update(key2Value.containsKey(key)? key2Value.get(key) + 1: 1, key);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return (head != null)? value2Keys.get(head.prev.value).iterator().next(): "";
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return (head != null)? value2Keys.get(head.value).iterator().next(): "";
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
