/* Time:O(n*m), Space:O(n)
 * 1. Construct an array column, where column[y] = x means the x coordinate on the the y-th row, which has only one 'B' on the row
 * 2. Traverse column, and scan the column[y]-th column. If there is only one 'B' on the column, increase lonelyNumber 
 */

import java.util.*;

public class Solution{
    public int findLonelyPixel(char[][] picture) {
        int depth = picture.length;
        int width = picture[0].length;
        
        int[] column = new int[depth];
        for(int y = 0; y < depth; ++y){
            column[y] = -1;
            int count = 0;
            for(int x = 0; x < width; ++x){
                if(picture[y][x] == 'B'){
                    count++;
                    column[y] = x;
                }
            }
            column[y] = (count == 1)? column[y]: -1;
        }
        
        int lonelyNumber = 0;
        for(int y = 0; y < depth; ++y){
            if(column[y] != -1){
                int count = 0;
                int x = column[y];
                for(int yy = 0; yy < depth; ++yy){
                    count = (picture[yy][x] == 'B')? count + 1: count;
                }
                lonelyNumber = (count == 1)? lonelyNumber + 1: lonelyNumber;
            }
        }
        
        return lonelyNumber;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] picture = {{'W', 'W', 'B'},
                            {'W', 'B', 'W'},
                            {'B', 'W', 'W'}};
       
        System.out.println("picture:");
        for(char[] row: picture){
            System.out.println(Arrays.toString(row));
        } 
        System.out.println("lonelyPixel: " + sol.findLonelyPixel(picture));
    }
}
