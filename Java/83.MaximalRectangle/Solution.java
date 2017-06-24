/* Dynamic Programming: O(n^3)
 * 1. dp[y][x] = all the possible retangles, with the right-bottom corner on (y, x)
 * 2. Caculate dp[y][x] based on dp[y - 1][x] and the last retangle of dp[y][x - 1]
 */

import java.util.*;


public class Solution{
    private class Rectangle{
        int hight;
        int width;
        Rectangle(int h, int w){hight = h; width = w;}
    }
    
    public int currArea(List<Rectangle>[][] dp, char[][] matrix, int y , int x){
        List<Rectangle> upperRectangles = new ArrayList<Rectangle>();
        Rectangle leftRectangle = new Rectangle(1, 0);
        int hight, width, maxArea;
        
        maxArea = 0;
        dp[y][x] = new ArrayList<Rectangle>();
        
        if(matrix[y][x] == '1'){
            if(y > 0 && matrix[y - 1][x] == '1'){
                upperRectangles = dp[y - 1][x];
            }
            
            if(x > 0 && matrix[y][x - 1] == '1'){
                leftRectangle.width = dp[y][x - 1].get(dp[y][x - 1].size() - 1).width;
            }
            
            for(Rectangle rectangle: upperRectangles){
                if(rectangle.width < leftRectangle.width + 1){
                    hight = rectangle.hight + 1;
                    width = rectangle.width;
                    maxArea = Math.max(maxArea, hight * width);
                    dp[y][x].add(new Rectangle(hight, width));
                }
                else if(rectangle.width >= leftRectangle.width + 1){
                    hight = rectangle.hight + 1;
                    width = Math.min(rectangle.width, leftRectangle.width + 1);
                    maxArea = Math.max(maxArea, hight * width);
                    dp[y][x].add(new Rectangle(hight, width));
                    break;
                }
            }

            if(dp[y][x].isEmpty()){
                dp[y][x].add(new Rectangle(1, leftRectangle.width + 1));
                maxArea = Math.max(maxArea, leftRectangle.width + 1);
            }
            else{
                if(leftRectangle.width >= dp[y][x].get(dp[y][x].size() - 1).width){
                    dp[y][x].add(new Rectangle(1, leftRectangle.width + 1));
                    maxArea = Math.max(maxArea, leftRectangle.width + 1);
                }
            }
        }
        return maxArea;
    }
    
    public int maximalRectangle(char[][] matrix) {
        List<Rectangle>[][] dp;
        int hight = matrix.length;
        int width = (hight > 0)? matrix[0].length: 0;
        int maxArea;
        
        dp = (ArrayList<Rectangle>[][]) new ArrayList<?>[hight][width];
        maxArea = 0;
        
        for(int y = 0; y < hight; y++){
            for(int x = 0; x < width; x++){
                maxArea = Math.max(currArea(dp, matrix, y ,x), maxArea);
            }
        }
        return maxArea;
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
