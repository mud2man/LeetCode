/* HashMap: time = O(1), space = O(n)
 * 1. Have a bucket "val2Idx" to store all available values
 * 2. Have a HashMap valueIndexMap to store the index set of indexes w.r.t. the value as the key
 * 3. When insert, accumulate lastIndex if bucket full, and put the val-index pair into valueIndexMap
 * 4. WHen remove, remove val from bucket and valueIndexMap, replace val in bucket with lastValue and update valueIndexMap
 * 5. When getRandom, get the random index between 0 and (lastIndex + 1). then retrieve value from bucket
 */

import java.util.*;

public class RandomizedCollection{
    List<Integer> list;
    Map<Integer, Set<Integer>> val2Idx;
    int len;
    Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        rand = new Random();
        list = new ArrayList<>();
        val2Idx = new HashMap<>();
        len = 0;
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean ret = true;
        if(val2Idx.containsKey(val) && !val2Idx.get(val).isEmpty()){
            ret = false;
        }
        
        len++;
        if(list.size() >= len){
            list.set(len - 1, val);
        }
        else{
            list.add(val);
        }
        val2Idx.putIfAbsent(val, new HashSet<>());
        val2Idx.get(val).add(len - 1);
        return ret;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!val2Idx.containsKey(val) || val2Idx.get(val).isEmpty()){
            return false;
        }
        
        Iterator<Integer> itr = val2Idx.get(val).iterator();
        int removeIdx = itr.next();
        itr.remove();
        len--;
        list.set(removeIdx, list.get(len));
        
        val2Idx.get(list.get(removeIdx)).add(removeIdx);
        val2Idx.get(list.get(removeIdx)).remove(len);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int idx = rand.nextInt(len);
        return list.get(idx);
    }

    public static void main(String[] args){
        RandomizedCollection obj = new RandomizedCollection();
        System.out.println("obj.insert(1): " + obj.insert(1));
        System.out.println("obj.insert(1): " + obj.insert(1));
        System.out.println("obj.insert(2): " + obj.insert(2));
        System.out.println("obj.getRandom(): " + obj.getRandom());
        System.out.println("obj.remove(1): " + obj.insert(1));
        System.out.println("obj.getRandom(): " + obj.getRandom());
        System.out.println("obj.getRandom(): " + obj.getRandom());
        System.out.println("obj.getRandom(): " + obj.getRandom());
    }
}
