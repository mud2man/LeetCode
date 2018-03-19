/* Circular queue, HashMap: TIme:O(1), Space:O(n), where n is the capacity. Leetcodes has a shorter answer
 * 1. Have a circular queue to record all the node for eavery frequency
 * 2. Have a hashmap "frequency2Tail" to record the latest node for every frequency
 * 3. Have a hashmap "key2Index" to record the mapping between key and node index
 * 4. When adding, add the new node on the tail of the circular queue, which recorded by frequency2Tail
 * 5. When deleting, delete the head of the circular queue, which is the next of tail 
 */

import java.util.*;

public class LFUCache {
    private class Node{
        int next;
        int previous;
        int value;
        int key;
        int frequency;
        Node(int k, int v){key = k; value = v;}
    }
    Node[] queues;
    HashMap<Integer, Integer> key2Index;
    HashMap<Integer, Integer> frequency2Tail;
    int leastFrequency;
    int availableIndex;
    int capacity;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.availableIndex = 0;
        this.leastFrequency = -1;
        this.frequency2Tail = new HashMap<Integer, Integer>();
        this.key2Index = new HashMap<Integer, Integer>();
        this.queues = new Node[capacity];
    }
    
    private int delete(int key){
        int index = key2Index.get(key);
        if(queues[index].next == index){
            leastFrequency = (leastFrequency == queues[index].frequency)? -1: leastFrequency;
            frequency2Tail.remove(queues[index].frequency);
        }
        else{
            if(index == frequency2Tail.get(queues[index].frequency)){
                frequency2Tail.put(queues[index].frequency, queues[index].previous);
            }
            int previous = queues[index].previous;
            int next = queues[index].next;
            queues[previous].next = next;
            queues[next].previous = previous;
        }
        key2Index.remove(key);
        return index;
    }
    
    private void add(int index, int key, int value, int frequency){
        leastFrequency = (leastFrequency == -1)? frequency: Math.min(leastFrequency, frequency);
        queues[index].key = key;
        queues[index].value = value;
        queues[index].frequency = frequency;
        queues[index].next = index;
        queues[index].previous = index;
        key2Index.put(key, index);
        frequency2Tail.putIfAbsent(frequency, index);
        if(frequency2Tail.get(frequency) != index){
            int previous = frequency2Tail.get(frequency);
            int next = queues[previous].next;
            queues[previous].next = index;
            queues[next].previous = index;
            queues[index].next = next;
            queues[index].previous = previous;
        }
        frequency2Tail.put(frequency, index);
    }
    
    private void update(int key){
        int index = key2Index.get(key);
        delete(key);
        add(index, key, queues[index].value, queues[index].frequency + 1);
    }
    
    public int get(int key) {
        if(capacity > 0 && key2Index.containsKey(key)){
            update(key);
            return queues[key2Index.get(key)].value;
        }
        else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(capacity <= 0){
            return;
        }
        
        if(key2Index.containsKey(key)){
            queues[key2Index.get(key)].value = value;
            get(key);
        }
        else{
            if(availableIndex < capacity){
                queues[availableIndex] = new Node(key, value);
                add(availableIndex, key, value, 1);
                availableIndex++;
            }
            else{
                int tailIndex = frequency2Tail.get(leastFrequency);
                int deleteKey = queues[queues[tailIndex].next].key;
                int deleteIndex = delete(deleteKey);
                add(deleteIndex, key, value, 1);
            }
        }
    }

    public static void main(String[] args){
        LFUCache cache;
        int key, value;

        cache = new LFUCache(2);

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
        
        key = 3;
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
