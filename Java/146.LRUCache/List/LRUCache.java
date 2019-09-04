/* HashMap: O(1)
 * 1. Have an circular queue with "head" to record all the pritity for each key
 * 2. The element of queue is composed of prev(older), next(newer)
 * 3. Have a map "key2Node" to index key and its associated node
 * 4. In put, when we need to update the priority, we just shift head to its next, and update it
 * 5. In get, we take the new head, and connect its neighbors. Then insert the new head 
 */

import java.util.*;

public class LRUCache {
        private class Node{
        int key;
        int val;
        Node prev;
        Node next;
        Node(int k, int v){key = k; val = v; prev = this; next = this;}
    }
    Map<Integer, Node> key2Node;
    int remain;
    Node head;

    public LRUCache(int capacity) {
        key2Node = new HashMap<>();
        remain = capacity;
        head = null;
    }
    
    public int get(int key) {
        if(!key2Node.containsKey(key)){
            return -1;
        }else{
            Node node = key2Node.get(key);
            if(node == head){
                head = head.next;
            }else{
                node.next.prev = node.prev;
                node.prev.next = node.next;
                
                node.next = head;
                node.prev = head.prev;
                head.prev.next = node;
                head.prev = node;
            }
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        if(key2Node.containsKey(key)){
            key2Node.get(key).val = value;
            get(key);
        }else{
            remain--;
            if(remain >= 0){
                Node node = new Node(key, value);
                key2Node.put(key, node);
                
                if(head == null){
                    head = node;
                }else{
                    node.next = head;
                    node.prev = head.prev;
                    head.prev.next = node;
                    head.prev = node;
                }
            }else{
                if(head == null){
                    return;
                }else{
                    key2Node.remove(head.key);
                    key2Node.put(key, head);
                    head.val = value;
                    head.key = key;
                    head = head.next;
                }
            }
        }
    }
 
    public static void main(String[] args){
        LRUCache cache = new LRUCache(2);
        int n = 6;
        int key, value;


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
