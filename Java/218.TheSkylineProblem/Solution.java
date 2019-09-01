/* MaxmunHeap + HashMap: time = O(nlogn), space = O(n)
 * 1. Have x2LeftHights to record the height when encounter the left edge of building
 * 2. Have x2RightHights to record the height when encounter the right edge of building
 * 3. Traverse all the building to create addHeightMa, deleteHeightMapp and xs
 * 4. Sort the xs with increasing order
 * 5. Traverse xs and skip if encounetr duplicated one, add height if encounter left edge, delete if encounter right edge
 *  5.1 Add the heights if x2LeftHights contains the current x, because it encounters left edge
 *  5.2 Delete the heights if x2RightHights contains the current x, because it encounters right edge
 *  5.3 Add (x, hight) to skyline if height changes
 */

import java.util.*;

public class Solution{
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Map<Integer, List<Integer>> x2LeftHights = new HashMap<>();
        Map<Integer, List<Integer>> x2RightHights = new HashMap<>();
        List<Integer> xs = new ArrayList<>();
        Set<Integer> visitedXs = new HashSet<>();
        for(int[] building: buildings){
            x2LeftHights.computeIfAbsent(building[0], key -> new ArrayList<>()).add(building[2]);
            x2RightHights.computeIfAbsent(building[1], key -> new ArrayList<>()).add(building[2]);
            if(!visitedXs.contains(building[0])){
                xs.add(building[0]);
            }
            if(!visitedXs.contains(building[1])){
                xs.add(building[1]);
            }
            visitedXs.add(building[0]);
            visitedXs.add(building[1]);
        }
        Collections.sort(xs);
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y.compareTo(x));
        List<List<Integer>> skyline = new LinkedList<>();
        int currHeight = 0;
        for(int x: xs){
            if(x2LeftHights.containsKey(x)){
                for(int y: x2LeftHights.get(x)){
                    maxHeap.add(y);
                }
            }
            if(x2RightHights.containsKey(x)){
                for(int y: x2RightHights.get(x)){
                    maxHeap.remove(y);
                }
            }
            int hight = maxHeap.isEmpty()? 0: maxHeap.peek();
            if(hight != currHeight){
                skyline.add(Arrays.asList(x, hight));
                currHeight = hight;
            }
        }
        return skyline;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};

        System.out.println("buildings: ");
        for(int[] building: buildings){
            System.out.print(Arrays.toString(building) + ", ");
        }
        System.out.println("");
        System.out.println("skylin: " + sol.getSkyline(buildings));
    }
}
