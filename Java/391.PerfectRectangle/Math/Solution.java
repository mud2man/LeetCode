/* Math: Time:O(n), Space:O(n).
 * 1. If we meet 3 conditions, then the small retangles can form a big retangle
 * 2. Condition1: The sum of small retangle area is equal to the area of the big retangle
 * 3. Condition2: The number of poitn other than the 4 cotrners is even
 * 3. Condition3: There is single point for each corner
 * 4. We use "hash" to identify the point
 */

import java.util.*;


public class Solution{
    private String hash(int[] point){
        return Integer.toString(point[0]) + "." + Integer.toString(point[1]);    
    }
    
    public boolean isRectangleCover(int[][] rectangles) {
        int[] mostLeftBottom = new int[]{rectangles[0][0], rectangles[0][1]};
        int[] mostRightTop = new int[]{rectangles[0][2], rectangles[0][3]};
        Map<String, Integer> point2count = new HashMap<>();
        int areaSum = 0;
        for(int[] rectangle: rectangles){
            int[] leftBottom = new int[]{rectangle[0], rectangle[1]};
            int[] rightBottom = new int[]{rectangle[2], rectangle[1]};
            int[] rightTop = new int[]{rectangle[2], rectangle[3]};
            int[] leftTop = new int[]{rectangle[0], rectangle[3]};
            point2count.putIfAbsent(hash(leftBottom), 0);
            point2count.put(hash(leftBottom), point2count.get(hash(leftBottom)) + 1);
            point2count.putIfAbsent(hash(rightBottom), 0);
            point2count.put(hash(rightBottom), point2count.get(hash(rightBottom)) + 1);
            point2count.putIfAbsent(hash(rightTop), 0);
            point2count.put(hash(rightTop), point2count.get(hash(rightTop)) + 1);
            point2count.putIfAbsent(hash(leftTop), 0);
            point2count.put(hash(leftTop), point2count.get(hash(leftTop)) + 1);
            mostLeftBottom[0] = Math.min(mostLeftBottom[0], rectangle[0]);
            mostLeftBottom[1] = Math.min(mostLeftBottom[1], rectangle[1]);
            mostRightTop[0] = Math.max(mostRightTop[0], rectangle[2]);
            mostRightTop[1] = Math.max(mostRightTop[1], rectangle[3]);
            areaSum += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
        }
        Set<String> corners = new HashSet<>();
        corners.add(hash(mostLeftBottom));
        corners.add(hash(new int[]{mostRightTop[0], mostLeftBottom[1]}));
        corners.add(hash(mostRightTop));
        corners.add(hash(new int[]{mostLeftBottom[0], mostRightTop[1]}));
        int singlePointcount = 0;
        for(Map.Entry<String, Integer>entry: point2count.entrySet()){
            int count = entry.getValue();
            if(count == 1){
                if(corners.contains(entry.getKey())){
                    singlePointcount++;
                }else{
                    return false;
                }
            }else if(count % 2 == 1){
                return false;
            }
        }
        int area = (mostRightTop[0] - mostLeftBottom[0]) * (mostRightTop[1] - mostLeftBottom[1]);
        return (singlePointcount == 4) & (area == areaSum);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] rectangles = {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}};
        System.out.println("rectangles:");
        for(int[] rectangle: rectangles){
            System.out.println(Arrays.toString(rectangle));
        }
        System.out.println("is perfect rectangle:" + sol.isRectangleCover(rectangles));
    }
}
