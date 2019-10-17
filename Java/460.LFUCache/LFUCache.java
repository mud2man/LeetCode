/* Circular queue, HashMap: TIme:O(1), Space:O(n)
 * 1. Have a hashmap "freq2Keys" to record all the keys for eavery frequency
 * 2. Have a hashmap "key2Freq" to record key-frequency pair
 * 3. Have a hashmap "key2Val" to record key-value pair
 * 4. Have minFreq and remain to record the minimum frequecy, and remaining count
 * 5. When delete the least recently element, we can use iterator to delete the first element
 */

import java.util.*;

public class LFUCache {
    Map<Integer, Integer> key2Val;
    Map<Integer, Integer> key2Freq;
    Map<Integer, Set<Integer>> freq2Keys;
    int reamin;
    int minFreq;
    
    public LFUCache(int capacity) {
        key2Val = new HashMap<>();
        key2Freq = new HashMap<>();
        freq2Keys = new HashMap<>();
        reamin = capacity;
        minFreq = Integer.MAX_VALUE;
    }
    
    public int get(int key) {
        if(!key2Val.containsKey(key)){
            return -1;
        }
        else{
            int val = key2Val.get(key);
            int freq = key2Freq.get(key);
            key2Freq.put(key, freq + 1);
            freq2Keys.get(freq).remove(key);
            freq2Keys.computeIfAbsent(freq + 1, k -> new LinkedHashSet()).add(key);
            if(minFreq == freq && freq2Keys.get(freq).isEmpty()){
                minFreq++;
            }
            return val;
        }
    }
    
    public void put(int key, int value) {
        if(key2Val.containsKey(key)){
            key2Val.put(key, value);
            get(key);
        }
        else{
            if(reamin > 0){
                key2Val.put(key, value);
                key2Freq.put(key, 1);
                freq2Keys.computeIfAbsent(1, k -> new LinkedHashSet()).add(key);
                minFreq = 1;
                reamin--;
            }else{
                if(!freq2Keys.containsKey(minFreq)){
                    return;
                }
                int deleteKey = freq2Keys.get(minFreq).iterator().next();
                freq2Keys.get(minFreq).remove(deleteKey);
                key2Val.remove(deleteKey);
                key2Freq.remove(deleteKey);
                
                key2Val.put(key, value);
                minFreq = 1;
                freq2Keys.computeIfAbsent(1, k -> new LinkedHashSet()).add(key);
                key2Freq.put(key, 1);
            }
        }
    }

    public static void main(String[] args){
        LFUCache cache = new LFUCache(2);
        int key = 1;
        int value = 1;
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
