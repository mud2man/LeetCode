/* HashMap: O(1)
 * 1. Have an circular queue with "head" to record all the pritity for each key
 * 2. The element of queue is composed of prev(older), next(newer)
 * 3. Have a map "key2Node" to index key and its associated node
 * 4. Also, we have a key value map key2Val to record the key-value pair
 * 5. In put, when we need to update the priority, we just shift head to its next, and update it
 * 6. In get, we take the new head, and connect its neighbors. Then insert the new head 
 */

import java.util.*;

public class LRUCache {
    private class Node{
        int key;
        Node prev;
        Node next;
        Node(int k){key = k; prev = this; next = this;}
    }
    
    Map<Integer, Integer> key2Val;
    Map<Integer, Node> key2Node;
    int remain;
    Node head;
    
    public LRUCache(int capacity) {
        key2Val = new HashMap<>();
        key2Node = new HashMap<>();
        remain = capacity;
        head = null;
    }
    
    public int get(int key) {
        if(!key2Val.containsKey(key)){
            return -1;
        }
        else{
            Node node = key2Node.get(key);
            if(node == head){
                head = head.next;
            }
            else{
                node.next.prev = node.prev;
                node.prev.next = node.next;
                
                node.next = head;
                node.prev = head.prev;
                head.prev.next = node;
                head.prev = node;
            }
            return key2Val.get(key);
        }
    }
    
    public void put(int key, int value) {
        if(key2Val.containsKey(key)){
            key2Val.put(key, value);
            get(key);
        }
        else{
            remain--;
            if(remain >= 0){
                key2Val.put(key, value);
                Node node = new Node(key);
                key2Node.put(key, node);
                
                if(head == null){
                    head = node;
                }
                else{
                    node.next = head;
                    node.prev = head.prev;
                    head.prev.next = node;
                    head.prev = node;
                }
            }
            else{
                if(head == null){
                    return;
                }
                else{
                    key2Val.remove(head.key);
                    key2Node.remove(head.key);
                    key2Val.put(key, value);
                    key2Node.put(key, head);
                    head.key = key;
                    head = head.next;
                }
            }
        }
    }

    public static void main(String[] args){
        LRUCache cache;
        int n = 6;
        int key, value;

        cache = new LRUCache(2);

        key = 1;
        value = 1;
        cache.put(key, value);
        System.out.println("put(" + key + ", " + value + ")");

        key = 2;
        value = 2;
        cache.put(key, value);
        System.out.println("put(" + key + ", " + value + ")");
        
        key = 1;
        System.out.println("get(" + key + "): " + cache.get(key));

        key = 3;
        value = 3;
        cache.put(key, value);
        System.out.println("put(" + key + ", " + value + ")");
        
        key = 2;
        System.out.println("get(" + key + "): " + cache.get(key));

        key = 4;
        value = 4;
        cache.put(key, value);
        System.out.println("put(" + key + ", " + value + ")");

        key = 1;
        System.out.println("get(" + key + "): " + cache.get(key));
        
        key = 3;
        System.out.println("get(" + key + "): " + cache.get(key));
        
        key = 4;
        System.out.println("get(" + key + "): " + cache.get(key));
    }
}
