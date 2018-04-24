/* Time:O(n*m), Space:O(n*m)
 * 1. Retrieve the count of black pixel per column numberInColumn, and the rowToCount
 * 2. Only the row of with count of black pixel is equal to N can put into rowToCount
 * 3. Accumulate blackPixelCount with N only if the count of the row == N, and the numberInColumn[x]
 */

import java.util.*;

public class Solution{
    public int findBlackPixel(char[][] picture, int N) {
        int depth = picture.length;
        int width = picture[0].length;
        int[] numberInColumn = new int [width]; 
        HashMap<String, Integer> rowToCount = new HashMap<String, Integer>();
        
        for(int y = 0; y < depth; ++y){
            StringBuilder row = new StringBuilder("");
            int count = 0;
            for(int x = 0; x < width; ++x){
                char c = picture[y][x];
                numberInColumn[x] += (c == 'B')? 1: 0;
                count += (c == 'B')? 1: 0;
                row.append(c);
            }
            if(count == N){
                rowToCount.putIfAbsent(row.toString(), 0);
                rowToCount.put(row.toString(), rowToCount.get(row.toString()) + 1);
            } 
        }
        
        int blackPixelCount = 0;
        for(Map.Entry<String, Integer> entry: rowToCount.entrySet()){
            String row = entry.getKey();
            if(entry.getValue() != N){
                continue;
            }
            else{
                for(int i = 0; i < row.length(); ++i){
                    blackPixelCount += (row.charAt(i) == 'B' && numberInColumn[i] == N)? N: 0;
                }
            }
        }
        
        return blackPixelCount;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 3;
        char[][] picture = {{'W', 'B', 'W', 'B', 'B', 'W'},
                            {'W', 'B', 'W', 'B', 'B', 'W'},
                            {'W', 'B', 'W', 'B', 'B', 'W'},
                            {'W', 'W', 'B', 'W', 'B', 'W'}};
        
        System.out.println("N: " + N);
        System.out.println("picture:");
        for(char[] row: picture){
            System.out.println(Arrays.toString(row));
        } 
        System.out.println("lonelyPixel: " + sol.findBlackPixel(picture, N));
    }
}
