/* LinkedList and HashMap,
 * 1. Use HashMap to record positions
 * 2. In "remove" case, swap the last element to the position, which is gonna be removed, to maintain consistence of position map
 */

import java.util.*;


public class RandomizedSet {
    HashMap<Integer, Integer> map;
    LinkedList<Integer> set;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        set = new LinkedList<Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        int idx;
        
        if(!map.containsKey(val)){
            idx = set.size();
            map.put(val, idx);
            set.add(val);
            return true;
        }
        else{
            return false;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        int idx;
        
        if(map.containsKey(val)){
            idx = map.get(val);
            set.set(idx, set.peekLast());
            map.remove(set.peekLast());
            map.put(set.peekLast(), idx);
            set.pollLast();
            map.remove(val);
            return true;
        }
        else{
            return false;
        }
        
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
