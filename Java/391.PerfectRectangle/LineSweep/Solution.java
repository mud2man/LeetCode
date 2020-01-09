/* Line Sweep: Time:O(nlogn), Space:O(n). There is O(n) solution
 * 1. Sweep intervals with respect to different y cordinations
 * 2. We remove the intervals by y2RemoveIntervals and add intervals from y2AddIntervals
 * 3. RemoveIntervals are from top edges of retangle and AddIntervals are from bottom edges
 * 3. In each iteration, we check if the intervals are continuous and no-overlap
 * 4. Return true if the sum of all samll retancles is equal to the area of the big retangle
 */

import java.util.*;


public class Solution{
    private boolean isContinuous(List<int[]> intervals){
        Collections.sort(intervals, (x, y) -> (x[0] - y[0]));
        for(int i = 0; i < intervals.size() - 1; ++i){
            if(intervals.get(i)[1] != intervals.get(i + 1)[0]){
                return false;
            }
        }
        return true;
    }

    public boolean isRectangleCover(int[][] rectangles) {
        Map<Integer, List<int[]>> y2AddIntervals = new HashMap<>();
        Map<Integer, List<int[]>> y2RemoveIntervals = new HashMap<>();
        List<Integer> ys = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        int areaSum = 0;
        int[] leftBottom = new int[]{rectangles[0][0], rectangles[0][1]};
        int[] rightTop = new int[]{rectangles[0][2], rectangles[0][3]};
        for(int[] rectangle: rectangles){
            y2AddIntervals.computeIfAbsent(rectangle[1], key -> new ArrayList<>()).add(new int[]{rectangle[0], rectangle[2]});
            y2RemoveIntervals.computeIfAbsent(rectangle[3], key -> new ArrayList<>()).add(new int[]{rectangle[0], rectangle[2]});
            areaSum += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
            if(!seen.contains(rectangle[1])){
                ys.add(rectangle[1]);
                seen.add(rectangle[1]);
            }
            if(!seen.contains(rectangle[3])){
                ys.add(rectangle[3]);
                seen.add(rectangle[3]);
            }
            if(leftBottom[0] > rectangle[0] || leftBottom[1] > rectangle[1]){
                leftBottom = new int[]{rectangle[0], rectangle[1]};
            }
            if(rightTop[0] < rectangle[2] || rightTop[1] < rectangle[3]){
                rightTop = new int[]{rectangle[2], rectangle[3]};
            }
        }
        
        List<int[]> intervals = new ArrayList<>();
        Collections.sort(ys);
        for(int y: ys){
            List<int[]> addIntervals = y2AddIntervals.getOrDefault(y, new ArrayList<>());
            Collections.sort(addIntervals, (a, b) -> (a[0] - b[0]));
            List<int[]> removeIntervals = y2RemoveIntervals.getOrDefault(y, new ArrayList<>());
            Collections.sort(removeIntervals, (a, b) -> (a[0] - b[0]));
            List<int[]> nextIntervals = new ArrayList<>();
            for(int i = 0, j = 0; i < intervals.size(); ++i){
                if(j < removeIntervals.size() && removeIntervals.get(j)[0] == intervals.get(i)[0]){
                    ++j;
                }else{
                    nextIntervals.add(intervals.get(i));
                }
            }
            for(int[] addInterval: addIntervals){
                nextIntervals.add(addInterval);
            }
            intervals = nextIntervals;
            if(!isContinuous(intervals)){
                return false;
            }
        }
        return (((rightTop[0] - leftBottom[0]) * (rightTop[1] - leftBottom[1])) == areaSum);
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
