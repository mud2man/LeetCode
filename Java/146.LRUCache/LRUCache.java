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
        int nextIndex;
        int prevIndex;
        int value;
        int key;
        Priority(int k, int v){nextIndex = 0; prevIndex = 0; key = k; value = v;}
    }
    
    Priority[] priorities;
    int headIndex;
    int availableIndex;
    HashMap<Integer, Integer> keyToIndex;

    public LRUCache(int capacity) {
        priorities = new Priority[capacity];
        headIndex = -1;
        availableIndex = 0;
        keyToIndex = new HashMap<Integer, Integer>();
    }
    
    public int get(int key) {
        if(!keyToIndex.containsKey(key)){
            return -1;
        }
        else{
            int currIndex = keyToIndex.get(key);
            if(currIndex == headIndex){
               return priorities[currIndex].value; 
            }
            
            int prevIndex = priorities[currIndex].prevIndex;
            int nextIndex = priorities[currIndex].nextIndex;
            
            //reconnect the previous and next node
            priorities[prevIndex].nextIndex = nextIndex;
            priorities[nextIndex].prevIndex = prevIndex;
            
            //update head
            int tailIndex = priorities[headIndex].nextIndex;
            priorities[headIndex].nextIndex = currIndex;
            priorities[currIndex].prevIndex = headIndex;
            priorities[currIndex].nextIndex = tailIndex;
            priorities[tailIndex].prevIndex = currIndex;
            headIndex = currIndex;

            return priorities[currIndex].value;
        }
    }
    
    public void put(int key, int value) {
        if(keyToIndex.containsKey(key)){
            int currIndex = keyToIndex.get(key);
            priorities[currIndex].value = value;
            get(key);
        }
        else{
            //priorities full, need to delete the least recent used node
            if(availableIndex == priorities.length){
                int tailIndex = priorities[headIndex].nextIndex;
                int tailKey = priorities[tailIndex].key;
                keyToIndex.remove(tailKey);
                keyToIndex.put(key, tailIndex);
                priorities[tailIndex].key = key;
                priorities[tailIndex].value = value;
                headIndex = tailIndex;
            }
            else{
                if(headIndex == -1){
                    priorities[availableIndex] = new Priority(key, value);
                    headIndex = availableIndex++;
                }
                else{
                    int tailIndex = priorities[headIndex].nextIndex;
                    priorities[availableIndex] = new Priority(key, value);
                    priorities[headIndex].nextIndex = availableIndex;
                    priorities[availableIndex].prevIndex = headIndex;
                    priorities[availableIndex].nextIndex = tailIndex;
                    priorities[tailIndex].prevIndex = availableIndex;
                    headIndex = availableIndex++;
                }
                keyToIndex.put(key, headIndex);
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
