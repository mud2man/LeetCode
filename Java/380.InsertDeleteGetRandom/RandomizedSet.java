/* ArrayList and HashMap,
 * 1. Use HashMap to record positions
 * 2. In "remove" case, swap the last element to the position, which is gonna be removed, to maintain consistence of position map
 * 3. Have a variable validLength to keep the length of the list. Only append the list when validLength == set.size()
 */

import java.util.*;


public class RandomizedSet {
    HashMap<Integer, Integer> map;
    ArrayList<Integer> set;
    int validLength;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        set = new ArrayList<Integer>();
        int validLength = 0;
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!map.containsKey(val)){
            if(validLength == set.size()){
                set.add(val);
                map.put(val, validLength++);
            }
            else{
                map.put(val, validLength);
                set.set(validLength++, val);
            }
            return true;
        }
        else{
            return false;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)){
            int idx = map.get(val);
            if(idx == (validLength - 1)){
                validLength--;
            }
            else{
                int lastValue = set.get(--validLength);
                set.set(idx, lastValue);
                map.put(lastValue, idx);
            }
            map.remove(val);
            return true;
        }
        else{
            return false;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random rand = new Random();
        int idx = rand.nextInt(validLength);
        return set.get(idx);
    }
 
    /** Get a random element from the set. */
    public int getRandom() {
        int idx;
        Random rand;
        
        rand = new Random();
        idx = rand.nextInt(set.size());
        return set.get(idx);
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
