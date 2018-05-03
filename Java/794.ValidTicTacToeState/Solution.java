/* Time:O(1), Space:O(1)
 * 1. Transform the string array to 2D number array, 'X'=>-1. 'O'=>1, ' '=>0
 * 2. Have a utility method "getWinCount" to caculate how many wins given the target value
 * 3. Traverse 5 conditions to determine if it valid
 */

import java.util.*;

public class Solution{
    private int getWinCount(int[][] nums, int target){
        int[] columnCount = new int[3];
        int[] rowCount = new int[3];
        int diagnal = 0;
        int anitDiagnal = 0;
        
        for(int y = 0; y < 3; ++y){
            for(int x = 0; x < 3; ++x){
                diagnal += (y == x)? nums[y][x]: 0;
                anitDiagnal += ((y + x) == 2)? nums[y][x]: 0;                
                columnCount[x] += nums[y][x];
                rowCount[y] += nums[y][x];
            }
        }
        
        int count = 0;
        count += (diagnal == target)? 1: 0;
        count += (anitDiagnal == target)? 1: 0;
        for(int i = 0; i < 3; ++i){
            count += (columnCount[i] == target)? 1: 0;
            count += (rowCount[i] == target)? 1: 0;
        }
        return count;
    }
    
    public boolean validTicTacToe(String[] board) {
        int[][] nums = new int[3][3];
        int xCount = 0;
        int oCount = 0;
        for(int y = 0; y < 3; ++y){
            for(int x = 0; x < 3; ++x){
                char c = board[y].charAt(x);
                if(c == 'X'){
                    nums[y][x] = -1;
                    xCount++;
                }
                else if(c == 'O'){
                    nums[y][x] = 1;
                    oCount++;
                }
            } 
        }
        
        if(((xCount - oCount) != 1) && (xCount != oCount)){
            return false;
        }
        
        int xWinCount = getWinCount(nums, -3);
        if(xWinCount == 2){
            return true;
        }
        
        if(xWinCount == 1 && (xCount - oCount) != 1){
            return false;
        }
        
        int oWinCount = getWinCount(nums, 3);
        if(oWinCount == 1 && (xCount != oCount)){
            return false;
        }
        
        return (xWinCount + oWinCount) < 2;
    }
 
    public static void main(String[] args){
        Solution sol;
        String[] board = {"XOX", " X ", "   "};

        sol = new Solution();
        System.out.println("board: " + Arrays.toString(board));
        System.out.println("is vlaid: " + sol.validTicTacToe(board));
    }
}
