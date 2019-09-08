/* Map: Time:O(1), Space:O(n)
 * 1. Use "indexMap" to store index to value, "reverseIndexMap" to stroe value to index
 * 2. When inser, append the new value to the tail(index=size)
 * 3. When remove, swap the last value to the "removeIndex"
 */

import java.util.*;


public class RandomizedSet {
    int size;
    Map<Integer, Integer> indexMap;
    Map<Integer, Integer> reverseIndexMap;
    Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        size = 0;
        indexMap = new HashMap<>();
        reverseIndexMap = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!reverseIndexMap.containsKey(val)){
            indexMap.put(size, val);
            reverseIndexMap.put(val, size++);
            return true;
        }else{
            return false;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(reverseIndexMap.containsKey(val)){
            int lastVal = indexMap.get(--size);
            int removeIndex = reverseIndexMap.get(val);
            indexMap.put(removeIndex, lastVal);
            reverseIndexMap.put(lastVal, removeIndex);
            reverseIndexMap.remove(val);
            indexMap.remove(size);
            return true;
        }else{
            return false;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int randomIndex = rand.nextInt(size);
        return indexMap.get(randomIndex);
    }
   
    public static void main(String[] args){
        int val;
        RandomizedSet obj = new RandomizedSet();
        
        val = 1;
        System.out.println("Insert " + val + " :" + obj.insert(val));
        
        val = 2;
        System.out.println("Remove " + val + " :" + obj.remove(val));
        
        val = 2;
        System.out.println("Insert " + val + " :" + obj.insert(val));
        
        System.out.println("Get random: " + obj.getRandom());
        
        val = 1;
        System.out.println("Remove " + val + " :" + obj.remove(val));
        
        val = 2;
        System.out.println("Insert " + val + " :" + obj.insert(val));
        
        System.out.println("Get random: " + obj.getRandom());
    }
}
