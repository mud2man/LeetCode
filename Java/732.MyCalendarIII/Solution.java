/* TreeMap: Time:O(n), Space:O(n)
 * 1. Add 1 on start end, subtract 1 on end point
 * 2. Traverse tree, and update max
 */

import java.util.*;

public class Solution{
    TreeMap<Integer, Integer> map;
    
    public Solution() {
        map = new TreeMap<Integer, Integer>();
    }
    
    public int book(int start, int end) {
        if(map.containsKey(start)){
            map.put(start, map.get(start) + 1);
        }
        else{
            map.put(start, 1);
        }
        
        if(map.containsKey(end)){
            map.put(end, map.get(end) - 1);
        }
        else{
            map.put(end, -1);
        }
        
        int max = 0;
        int count = 0;
        for(int value: map.values()){
            count += value;
            max = Math.max(max, count);
        }
        return max;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] intervals = {{10, 20}, {50, 60}, {10, 40}, {5, 15}, {5, 10}, {25, 55}};
        
        for(int[] interval: intervals){
            System.out.println(Arrays.toString(interval));
        	System.out.println("insert success: " + sol.book(interval[0], interval[1]));
        }
    }
}
