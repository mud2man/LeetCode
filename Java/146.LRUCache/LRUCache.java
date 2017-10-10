/* HashMap: O(1)
 * 1. Have an circular queue circularPriorityQueue to record all the pritity for each key
 * 2. The element of priorityOueue is composed of prev(the higher priority), next(the lower priority)
 * 3. The index of the key in the priorityQueue are stored in keyIndexMap
 * 4. Also, we have a key value map keyValueMap to record the key-value pair
 * 5. We have headIndex to denode the index of the highest-priority key, and tailIndex is circularPriorityQueue[headIndex].prev
 * 6. In put, when we need to update the priority, we just shift headIndex to its prev, and update it
 * 7. In get, we take the new head, and connect its neighbors. Then insert the new head, update the headIndex 
 */

import java.util.*;

public class LRUCache {
    private class Priority{
        int prev;
        int next;
        int key;
        Priority(int p, int n, int k){prev = p; next = n; key = k;}
    }
    
    Priority[] circularPriorityQueue;
    HashMap<Integer, Integer> keyValueMap;
    HashMap<Integer, Integer> keyIndexMap;
    int headIndex;
    int size;
    int availableIndex;
    
    public LRUCache(int capacity) {
        size = capacity;
        headIndex = 0;
        availableIndex = 0;
        keyValueMap = new HashMap<Integer, Integer>();
        keyIndexMap = new HashMap<Integer, Integer>();
        circularPriorityQueue = new Priority[size];
    }
    
    public int get(int key) {
        if(size == 0 || !keyValueMap.containsKey(key)){
            return -1;
        }
        
        int newHeadIndex = keyIndexMap.get(key);
        if(newHeadIndex == headIndex){
            return keyValueMap.get(key);
        }
        
        //Take the new head, and connect its neighbors
        Priority newHead = circularPriorityQueue[newHeadIndex];
        int prevIndex = newHead.prev;
        int nextIndex = newHead.next;
        circularPriorityQueue[prevIndex].next = nextIndex;
        circularPriorityQueue[nextIndex].prev = prevIndex;
            
        //Insert the new head, update the headIndex
        int tailIndex = circularPriorityQueue[headIndex].prev;
        circularPriorityQueue[newHeadIndex].next = headIndex;
        circularPriorityQueue[newHeadIndex].prev = tailIndex;
        circularPriorityQueue[headIndex].prev = newHeadIndex;
        circularPriorityQueue[tailIndex].next = newHeadIndex;
        headIndex = newHeadIndex;
        
        return keyValueMap.get(key);
    }
    
    public void put(int key, int value) {
        if(size == 0){
            return;
        }
        
        if(keyValueMap.containsKey(key)){
            //update the pririty and key-value pair
            get(key);
            keyValueMap.put(key, value);
        }
        else{
            if(availableIndex < size){
                //put the current head to second head, and append the new node with headIndex 
                int oldHeadIndex = headIndex;
                int oldTaiIdx = (availableIndex == 0)? 0: circularPriorityQueue[oldHeadIndex].prev;
                int newHeadIndex = availableIndex++;
                circularPriorityQueue[newHeadIndex] = new Priority(oldTaiIdx, oldHeadIndex, key);
                circularPriorityQueue[oldHeadIndex].prev = newHeadIndex;
                circularPriorityQueue[oldTaiIdx].next = newHeadIndex;
                headIndex = newHeadIndex;
                keyValueMap.put(key, value);
                keyIndexMap.put(key, newHeadIndex);
            }
            else{
                //shift the headIndex to the tailIndex, because we delete tail and ues it as the last-used node
                headIndex = circularPriorityQueue[headIndex].prev;
                int oldKey = circularPriorityQueue[headIndex].key;
                circularPriorityQueue[headIndex].key = key;
                keyValueMap.remove(oldKey);
                keyValueMap.put(key, value);
                keyIndexMap.remove(oldKey);
                keyIndexMap.put(key, headIndex);
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
