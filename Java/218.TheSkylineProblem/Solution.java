/* MaxmunHeap + HashMap: time = O(nlogn), space = O(n)
 * 1. Have addHeightMap to record the height when encounter the left edge of building
 * 2. Have deleteHeightMap to record the height when encounter the right edge of building
 * 3. Traverse all the building to create addHeightMa, deleteHeightMapp and xPositions
 * 4. Sort the xPositions with increasing order
 * 5. Traverse xPositions and skip if encounetr duplicated one, add height if encounter left edge, delete if encounter right edge
 *  5.1 Add the heights if addHeightMap contains the current x, because it encounters left edge
 *  5.2 Delete the heights if deleteHeightMap contains the current x, because it encounters right edge
 *  5.3 If maxHeap empty, add int[x, 0], else add int[x, maxHeap.peek()] into skyline
 * 6. Delete the duplicated points from skyline, if the current point has the same height as the previous one has
 */

import java.util.*;

public class Solution{
    static class HeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer x, Integer y){
            return y - x;
        }
    }
    
    public List<int[]> getSkyline(int[][] buildings) {
        List<Integer> xPositions = new LinkedList<Integer>();
        HashMap<Integer, List<Integer>> addHeightMap = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> deleteHeightMap = new HashMap<Integer, List<Integer>>();
        List<int[]> skyline = new LinkedList<int[]>(); 
        
        for(int[] building: buildings){
            int startX = building[0];
            int endX = building[1];
            int height = building[2];
            xPositions.add(startX);
            xPositions.add(endX);
            if(!addHeightMap.containsKey(startX)){
                addHeightMap.put(startX, new LinkedList<Integer>());
            }
            addHeightMap.get(startX).add(height);
            
            if(!deleteHeightMap.containsKey(endX)){
                deleteHeightMap.put(endX, new LinkedList<Integer>());
            }
            deleteHeightMap.get(endX).add(height);
        }
        
        Collections.sort(xPositions);
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new HeapComparator());
        int prevX = -1;
        for(int x: xPositions){
            if(x == prevX){
                continue;
            }
            else{
                if(addHeightMap.containsKey(x)){
                    for(int height: addHeightMap.get(x)){
                        maxHeap.add(height);
                    }
                }
                
                if(deleteHeightMap.containsKey(x)){
                    for(int height: deleteHeightMap.get(x)){
                        maxHeap.remove(height);
                    }
                }
                
                if(!maxHeap.isEmpty()){
                    skyline.add(new int[]{x, maxHeap.peek()});
                }
                else{
                    skyline.add(new int[]{x, 0});
                }
            }
            prevX = x;
        }

        //reduce
        int[] prevPoint = new int[]{-1, -1};
        Iterator<int[]> iterator = skyline.iterator();
        while(iterator.hasNext()){
            int[] currentPoint = iterator.next();
            if(currentPoint[1] == prevPoint[1]){
                iterator.remove();
            }
            prevPoint = currentPoint;
        }
        
        return skyline;
    }

    public static void main(String[] args){
        Solution sol;
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};

        sol = new Solution();
        System.out.println("buildings: ");
        for(int[] building: buildings){
            System.out.print(Arrays.toString(building) + ", ");
        }
        System.out.println("");

        List<int[]> skyline = sol.getSkyline(buildings);
        System.out.println("skylin: ");
        for(int[] point: skyline){
            System.out.print(Arrays.toString(point) + ", ");
        }
        System.out.println("");
    }
}
