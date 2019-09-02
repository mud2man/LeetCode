/* HashMap: time = O(1), space = O(n)
 * 1. Have a bucket "index2Vals" to simulate as a list
 * 2. Have a HashMap "val2Indexs" to store the index set of indexes w.r.t. the value as the key
 * 3. When insert, put(len, val) to index2Val, and put the val-index pair into val2Indexs 
 * 4. WHen remove, remove val from index2Vals and valueIndexMap, replace val in bucket with lastValue and update index2Vals and val2Indexs
 * 5. When getRandom, get the random index between 0 and len. then retrieve value from index2Vals
 */

import java.util.*;

public class RandomizedCollection{
    Map<Integer, Integer> index2Val;
    Map<Integer, Set<Integer>> val2Indexs;
    int len;
    Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        rand = new Random();
        index2Val = new HashMap<>();
        val2Indexs = new HashMap<>();
        len = 0;
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean ret = (!val2Indexs.containsKey(val) || val2Indexs.get(val).size() == 0)? true: false;
        index2Val.put(len++, val);
        val2Indexs.computeIfAbsent(val, key -> new HashSet<>()).add(len - 1);
        return ret;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!val2Indexs.containsKey(val) || val2Indexs.get(val).size() == 0){
            return false;
        }
        int removeIdx = val2Indexs.get(val).iterator().next();
        val2Indexs.get(val).remove(removeIdx);
        len--;
        if(removeIdx < len){
            int lastVal = index2Val.get(len);
            index2Val.put(removeIdx, lastVal);
            val2Indexs.get(lastVal).remove(len);
            val2Indexs.computeIfAbsent(lastVal, key -> new HashSet<>()).add(removeIdx);
        }
        index2Val.remove(len);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return index2Val.get(rand.nextInt(len));
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
