/* Stack: Time:O(n*m), Space:O(n). LeetCode has a dynamic programming solution
 * 1. Traverse matrix row by row, and transform the row into heights
 * 2. Apply the solution of LeetCode#84 to get the max retangle area
 */

import java.util.*;


public class Solution{
    private int largestRectangleArea(int[] heights){
        int max = 0;
        Deque<int[]> stack = new LinkedList<>();
        for(int i = 0; i < heights.length; ++i){
            int[] top = new int[]{i, heights[i]};
            while(!stack.isEmpty() && stack.peekLast()[1] >= top[1]){
                int[] end = stack.pollLast();
                int area = end[1] * (i - end[0]);
                max = Math.max(max, area);
                top[0] = end[0];
            }
            stack.add(top);
        }
        
        while(!stack.isEmpty()){
            int[] front = stack.pollFirst();
            int area = front[1] * (heights.length - front[0]);
            max = Math.max(max, area);
        }
        return max;
    }
    
    public int maximalRectangle(char[][] matrix) {
        int depth = matrix.length;
        int width = (depth > 0)? matrix[0].length: 0;
        int[] heights = new int[width];
        int max = 0;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                heights[x] = (matrix[y][x] == '0')? 0: heights[x] + 1; 
            }
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                           {'1', '0', '1', '1', '1'},
                           {'1', '1', '1', '1', '1'},
                           {'1', '0', '0', '1', '0'}};
        int maxArea;

        maxArea = sol.maximalRectangle(matrix);
        
        System.out.println("matrix: ");
        for(char[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("maxArea: " + maxArea);
    }
}
