/* Map: Time:O(1), Space:O(n)
 * 1. Use "indexMap" to store index to value, "reverseIndexMap" to stroe value to index
 * 2. When inser, append the new value to the tail(index=size)
 * 3. When remove, swap the last value to the "removeIndex"
 */

import java.util.*;


public class RandomizedSet {
    int size;
    Map<Integer, Integer> index2Num;
    Map<Integer, Integer> num2Index;
    Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        size = 0;
        index2Num = new HashMap<>();
        num2Index = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!num2Index.containsKey(val)){
            index2Num.put(size, val);
            num2Index.put(val, size++);
            return true;
        }else{
            return false;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(num2Index.containsKey(val)){
            int lastVal = index2Num.get(--size);
            int removeIndex = num2Index.get(val);
            index2Num.put(removeIndex, lastVal);
            num2Index.put(lastVal, removeIndex);
            num2Index.remove(val);
            index2Num.remove(size);
            return true;
        }else{
            return false;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int randomIndex = rand.nextInt(size);
        return index2Num.get(randomIndex);
    }
  
    public static void main(String[] args){
        RandomizedSet obj = new RandomizedSet();
        int val = 1;
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
