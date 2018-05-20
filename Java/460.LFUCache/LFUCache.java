/* Circular queue, HashMap: TIme:O(1), Space:O(n)
 * 1. Have a hashmap "freq2Keys" to record all the keys for eavery frequency
 * 2. Have a hashmap "key2Freq" to record key-frequency pair
 * 3. Have a hashmap "key2Val" to record key-value pair
 * 4. Have minFreq and remain to record the minimum frequecy, and remaining count
 * 5. When delete the least recently element, we can use iterator to delete the first element
 */

import java.util.*;

public class LFUCache {
    int minFreq;
    int remain;
    HashMap<Integer, Integer> key2Val;
    HashMap<Integer, Integer> key2Freq;
    HashMap<Integer, LinkedHashSet<Integer>> freq2Keys;
    
    public LFUCache(int capacity) {
        this.remain = capacity;
        this.minFreq = 0;
        this.key2Val = new HashMap<Integer, Integer>();
        this.key2Freq = new HashMap<Integer, Integer>();
        this.freq2Keys = new HashMap<Integer, LinkedHashSet<Integer>>();
    }
    
    public int get(int key) {
        if(!key2Val.containsKey(key)){
            return -1;
        }
        else{
            int freq = key2Freq.get(key);
            key2Freq.put(key, freq + 1);
            LinkedHashSet<Integer> keys = freq2Keys.get(freq);
            keys.remove(key);
            if(keys.isEmpty() && minFreq == freq){
                minFreq = freq + 1;
            }
            freq++;
            freq2Keys.putIfAbsent(freq, new LinkedHashSet<Integer>());
            freq2Keys.get(freq).add(key);
            return key2Val.get(key);
        }
    }
    
    public void put(int key, int value) {
        if(key2Val.containsKey(key)){
            key2Val.put(key, value);
            get(key);
        }
        else{
            if(this.remain > 0){
                this.remain--;
            }
            else{
                LinkedHashSet<Integer> keys = freq2Keys.get(this.minFreq);
                if(keys == null || keys.isEmpty()){
                    return;
                }
                Iterator<Integer> itr = keys.iterator();
                int removeKey = itr.next();
                itr.remove();
                key2Val.remove(removeKey);
                key2Freq.remove(removeKey);
            }
            key2Val.put(key, value);
            key2Freq.put(key, 1);
            freq2Keys.putIfAbsent(1, new LinkedHashSet<Integer>());
            freq2Keys.get(1).add(key);
            this.minFreq = 1;
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
