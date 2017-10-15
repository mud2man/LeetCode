/* HashMap: time = O(1), space = O(n)
 * 1. Have a bucket to store all avaolable values
 * 2. Have a HashMap valueIndexMap to store the index set of indexes w.r.t. the value as the key
 * 3. When insert, accumulate lastIndex if bucket full, and put the val-index pair into valueIndexMap
 * 4. WHen remove, remove val from bucket and valueIndexMap, replace val in bucket with lastValue and update valueIndexMap
 * 5. When getRandom, get the random index between 0 and (lastIndex + 1). then retrieve value from bucket
 */

import java.util.*;

public class RandomizedCollection{
    List<Integer> bucket;
    int lastIndex;
    HashMap<Integer, HashSet<Integer>> valueIndexMap;
    Random random;
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        bucket = new ArrayList<Integer>();
        lastIndex = -1;
        valueIndexMap = new HashMap<Integer, HashSet<Integer>>();
        random = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        lastIndex++;
        boolean ret = false;
        
        if(lastIndex == bucket.size()){
            bucket.add(val);
        }
        else{
            bucket.set(lastIndex, val);
        }
        
        if(!valueIndexMap.containsKey(val)){
            ret = true;
            valueIndexMap.put(val, new HashSet<Integer>());
        }
        valueIndexMap.get(val).add(lastIndex);
        
        return ret;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!valueIndexMap.containsKey(val) || valueIndexMap.get(val).isEmpty()){
            return false;
        }
        
        //remove val from bucket and valueIndexMap
        Iterator<Integer> iterator = valueIndexMap.get(val).iterator();
        int removeIndex = iterator.next();
        valueIndexMap.get(val).remove(removeIndex);
        
        //replace the position of val with lastValue
        if(removeIndex != lastIndex){
            int lastValue = bucket.get(lastIndex--);
            bucket.set(removeIndex, lastValue);
            valueIndexMap.get(lastValue).remove(lastIndex + 1);
            valueIndexMap.get(lastValue).add(removeIndex);
        }
        else{
            lastIndex--;
        }
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int randomNum = random.nextInt(lastIndex + 1);
        return bucket.get(randomNum);
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
