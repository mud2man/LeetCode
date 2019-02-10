/* Map: Time:O(logn), Space:O(n)
 * 1. Hava database with value is tree map indexed by timestamp
 */

import java.util.*;

public class TimeMap{
    Map<String, TreeMap<Integer, String>> db;
    /** Initialize your data structure here. */
    public TimeMap() {
        db = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        db.putIfAbsent(key, new TreeMap<Integer, String>());
        db.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if(db.containsKey(key)){
            Integer floorKey = db.get(key).floorKey(timestamp);
            if(floorKey == null){
                return "";
            }
            else{
                return db.get(key).get(floorKey);
            }
        }
        else{
            return "";
        }
    }
  
    public static void main(String[] args){
        TimeMap sol = new TimeMap();;
        String key = "foo";
        String value = "bar";
        int timeStamp = 1;
        sol.set(key, value, timeStamp); 
        System.out.println("set(" + key + ", " + value + ", " + timeStamp + ")");
        
        key = "foo";
        timeStamp = 1;
        System.out.println("get(" + key + ", " + timeStamp + "): " + sol.get(key, timeStamp));
        
        key = "foo";
        timeStamp = 3;
        System.out.println("get(" + key + ", " + timeStamp + "): " + sol.get(key, timeStamp));
        
        key = "foo";
        value = "bar2";
        timeStamp = 4;
        sol.set(key, value, timeStamp); 
        System.out.println("set(" + key + ", " + value + ", " + timeStamp + ")");
        
        key = "foo";
        timeStamp = 4;
        System.out.println("get(" + key + ", " + timeStamp + "): " + sol.get(key, timeStamp));
        
        key = "foo";
        timeStamp = 5;
        System.out.println("get(" + key + ", " + timeStamp + "): " + sol.get(key, timeStamp));
    }
}
