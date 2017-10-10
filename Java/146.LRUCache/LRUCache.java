/* HashMap: O(1)
 * 1. Have an arrey priorityOueue to record all the pritity for each key
 * 2. The element of priorityOueue is composed of prev(the higher priority), next(the lower priority)
 * 3. The index of the key in the priorityQueue are stored in keyPriorityIndexMap 
 * 4. Also, we have a key value map keyValueMap to record the key-value pair
 * 5. We have headIdx to denode the index of the highest-priority key, and tailIdx for the index of the lowest-priority key
 * 6. When we need to update the priority, we delete the tail, update tail, and replace head with tail 
 */

import java.util.*;

public class LRUCache {
    private class Priority{
        int prev;
        int next;
        int key;
        Priority(int p, int n, int k){prev = p; next = n; key = k;}
    }
    
    /* newest index of key*/
    int headIdx;
    
    /* oldest index of key*/
    int tailIdx;
    
    /* priority queue */
    Priority[] priorityOueue;
    
    /* key and priorityOueue index */
    HashMap<Integer, Integer> keyPriorityIndexMap;
    
    /* key and value*/
    HashMap<Integer, Integer> keyValueMap;

    /* available index */
    int availableIdx;
    
    /* capacity */
    int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.availableIdx = 0;
        this.headIdx = -1;
        this.tailIdx = -1;
        this.priorityOueue = new Priority[capacity];
        keyPriorityIndexMap= new HashMap<Integer, Integer>();
        keyValueMap = new HashMap<Integer, Integer>();
    }
    
    public int get(int key) {
        if(!keyValueMap.containsKey(key)){
            return -1;
        }
        else{
            int currIdx = keyPriorityIndexMap.get(key);
            int value = keyValueMap.get(key);
            if(currIdx == headIdx){ 
                return value;
            }
            
            Priority currPriority = priorityOueue[currIdx];
            int nextIdx = currPriority.next;
            int prevIdx = currPriority.prev;
            
            //update the tail
            if(currIdx == tailIdx){
                tailIdx = prevIdx;
            }
            
            //break and append
            if(prevIdx != -1){
                priorityOueue[prevIdx].next = nextIdx; 
            }
            if(nextIdx != -1){
                priorityOueue[nextIdx].prev = prevIdx;
            }
            
            //update the head
            currPriority.next = headIdx;
            currPriority.key = key;
            currPriority.prev = -1;
            priorityOueue[headIdx].prev = currIdx;
            headIdx = currIdx;
            
            return value;
        }
    }
    
    public void put(int key, int value) {
        if(capacity == 0){
            return;
        }
        else if(capacity == 1){
            headIdx = 0;
            tailIdx = 0;
            keyValueMap = new HashMap<Integer, Integer>(); 
            keyValueMap.put(key, value);
            keyPriorityIndexMap = new HashMap<Integer, Integer>();
            keyPriorityIndexMap.put(key, 0);
        }
        else{
            if(keyValueMap.containsKey(key)){
                get(key);
                keyValueMap.put(key, value);
            }
            else if(availableIdx < capacity){
                if(availableIdx == 0){
                    headIdx = 0;
                    tailIdx = 0;
                    priorityOueue[availableIdx] = new Priority(-1, -1, key);
                }
                else{
                    priorityOueue[availableIdx] = new Priority(-1, headIdx, key);
                    priorityOueue[headIdx].prev = availableIdx;
                }
                headIdx = availableIdx++;
                keyPriorityIndexMap.put(key, headIdx);
                keyValueMap.put(key, value);
            }
            else{
                // delete the tail, update tail, replace head with tail
                int currIdx = tailIdx;
                Priority currPriority = priorityOueue[currIdx];
                int oldKey = currPriority.key;
                int prevIdx = currPriority.prev;
                tailIdx = prevIdx;
                priorityOueue[tailIdx].next = -1;
                priorityOueue[headIdx].prev = currIdx;
                currPriority.next = headIdx;
                currPriority.prev = -1;
                currPriority.key = key;
                headIdx = currIdx;
                keyValueMap.remove(oldKey);
                keyPriorityIndexMap.remove(oldKey);
                keyValueMap.put(key, value);
                keyPriorityIndexMap.put(key, currIdx);
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
