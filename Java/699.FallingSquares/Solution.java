/* TreeMap:Time:O(nlogn), Space:O(n). LeetCode has an official segment tree
 * 1. Have a tree map to store all non-overlap segment 
 * 2. Take the biggest lower entry and put it to the segment set with is overlapped with the current cube (position[i][0], position[i][0] + position[i][1])
 * 3. Find the highest height among these overlapped segment
 * 4. Update or remove the segment which is in the overlapped subMap
 * 5. Append height to the returned "hights"
 */          

import java.util.*; 
public class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> hights = new ArrayList<>();
        TreeMap<Integer, int[]> map = new TreeMap<>();
        int max = 0;
        for(int[] position: positions){
            int start = position[0];
            int end = position[0] + position[1];
            Map.Entry<Integer, int[]> lowerEntry = map.lowerEntry(start);
            TreeMap<Integer, int[]> subMap = new TreeMap<>(map.subMap(start, true, end, false));
            if(lowerEntry != null && lowerEntry.getValue()[0] > start){
                subMap.put(lowerEntry.getKey(), lowerEntry.getValue());
            }
            int height = position[1];
            for(Map.Entry<Integer, int[]>entry: subMap.entrySet()){
                height = Math.max(height, position[1] + entry.getValue()[1]);
            }
            for(Map.Entry<Integer, int[]>entry: subMap.entrySet()){
                int key = entry.getKey();
                int[] value = entry.getValue();
                if(start <= key && end >= value[0]){
                    map.remove(key);
                }else if(start > key && end >= value[0]){
                    map.get(key)[0] = start;
                }else if(start < key && end < value[0]){
                    map.put(end, value.clone());
                    map.remove(key);
                }else{
                    map.put(end, value.clone());
                    map.get(key)[0] = start;
                }
            }
            map.put(start, new int[]{end, height});
            max = Math.max(max, height);
            hights.add(max);
        }
        return hights;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] positions = {{1, 2}, {2, 3}, {6, 1}};
        System.out.println("positions: ");
        for(int[] row: positions){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("heights: " + sol.fallingSquares(positions));
    }
}
