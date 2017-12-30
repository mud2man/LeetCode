/* Time:O(n*m*n), Space:O(n). LeetCode has better solution
 * 1. countInRow[i] = j means the number of 'B' on column i is j
 * 2. countInColumn[i] = j means the number of 'B' on row i is j
 * 3. columnToRow[i] = j means the first row sees 'B' on column i is j-th row. -1: not assign; -2: invalid column; others: valid
 * 4. rowToRow[i] = j means i-th row is the same as j-th row 
 * 5. Get countInRow and countInColumn
 * 6. Get columnToRow to learn the valid column
 * 7. Accumulate count only if countInRow[x] == N && countInColumn[y] == N && columnToRow[x] >= 0 (valid column) 
 */

import java.util.*;

public class Solution{
    private boolean isSameRow(int source, int target, char[][] picture){
        for(int x = 0; x < picture[0].length; ++x){
            if(picture[source][x] != picture[target][x]){
                return false;
            }
        }
        return true;
    }
    
    public int findBlackPixel(char[][] picture, int N) {
        int depth = picture.length;
        int width = picture[0].length;
        int[] countInRow = new int[width];
        int[] countInColumn = new int[depth];
        int[] columnToRow = new int[width];
        int[] rowToRow = new int[depth];
        
        for(int x = 0; x < width; ++x){
            columnToRow[x] = -1;
            for(int y = 0; y < depth; ++y){
                if(picture[y][x] == 'B'){
                    countInRow[x]++;
                    countInColumn[y]++;
                    rowToRow[y] = -1;
                }
            }
        }
        
        for(int y = 0; y < depth; ++y){
            rowToRow[y] = y;
            for(int x = 0; x < width; ++x){
                if(picture[y][x] == 'B'){
                    if(columnToRow[x] == -1){
                        columnToRow[x] = y;
                        continue;
                    }
                    
                    if(rowToRow[y] < y || columnToRow[x] == -2){
                        continue;
                    }
                    
                    int row = columnToRow[x];
                    int parentRow = rowToRow[row];
                    if(isSameRow(parentRow, y, picture)){
                        rowToRow[y] = parentRow;
                    }
                    else{
                        columnToRow[x] = -2;
                    }   
                }
            }
        }

        int count = 0;
        for(int y = 0; y < depth; ++y){
            for(int x = 0; x < width; ++x){
                if(picture[y][x] == 'B' && countInRow[x] == N && countInColumn[y] == N && columnToRow[x] >= 0){
                    count++;
                }
            }
        }
        
        return count;
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
