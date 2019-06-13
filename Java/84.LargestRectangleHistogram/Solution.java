/* Stack: Time:O(n), SpaceO:(n)
 * 1. Have a stack, where the node is a pair{height, index} 
 * 2. If the current node is lower than the top's, pop the top and update the current's index. 
 * 3. During pop, the max region's height is determined by the top of stack
 * 4. So the stack is a monotonic stack, with increasing height
 * 5. Later, caculate the area, and update "max"
 *
 * ex: heights = {2, 1, 5, 6, 2, 3}
 * time[0], stack = {{2, 0}}, max = 0
 * time[1], stack = {{1, 0}}, max = 2
 * time[2], stack = {{1, 0}, {5, 2}}, max = 2
 * time[3], stack = {{1, 0}, {5, 2}, {6, 3}}, max = 2
 * time[4], stack = {{1, 0}, {2, 2}}, max = 10
 * time[5], stack = {{1, 0}, {2, 2}, {3, 5}}, max = 10
 */

import java.util.*;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Deque<int[]> queue = new LinkedList<>();
        for(int i = 0; i < heights.length; ++i){
            int[] tail = new int[]{heights[i], i};
            while(!queue.isEmpty() && queue.peekLast()[0] >= heights[i]){
                int[] end = queue.pollLast();
                tail[1] = end[1];
                max = Math.max(max, end[0] * (i - end[1]));
            }
            queue.add(tail);
        }
        
        while(!queue.isEmpty()){
            int[] front = queue.pollFirst();
            int area = front[0] * (heights.length - front[1]);
            max = Math.max(max, area);
        }
        return max;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("heights: " + Arrays.toString(heights));
        System.out.println("largest rectangle: " + sol.largestRectangleArea(heights));
    }
}
