/* Hash: Time:O(n), Space:O(n), where n is the number of bricks
 * 1. Have a hashmap "edgeMap" to record the occurance of accumulated length row by row
 * 2. Keep track the most occurance maxCount
 * 3. Return wall.size() - maxCount
 */

import java.util.*;

public class Solution{
    public int leastBricks(List<List<Integer>> wall) {
        int rowLength = 0;
        for(Integer num: wall.get(0)){
            rowLength += num;
        }
        
        HashMap<Integer, Integer> edgeMap = new HashMap<Integer, Integer>();
        int maxCount = 0;
        for(List<Integer> row: wall){
            int length = 0;
            for(Integer num: row){
                length += num;
                if(length < rowLength){
                    edgeMap.putIfAbsent(length, 0);
                    edgeMap.put(length, edgeMap.get(length) + 1);
                    maxCount = Math.max(edgeMap.get(length), maxCount); 
                }
            }
        }
        return wall.size() - maxCount;
    }
  
    public static void main(String[] args){
        List<List<Integer>> wall = new ArrayList<List<Integer>>();
        wall.add(Arrays.asList(1, 2, 2, 1));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 2));
        wall.add(Arrays.asList(2, 4));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 1, 1));
        Solution sol = new Solution();
        System.out.println("wall: " + wall);
        System.out.println("the least number of crossing bricks: " + sol.leastBricks(wall));
    }
}
