/* Backtrack: O(n!)
 * 1. Have an array "down" to store the column index which has queen
 * 2. Have an array "diagnoal" to store the index coming from diagnol direction which has queen, where id is x + y
 * 3. Have an array "antiDiagnoal" to store the index coming from anit-diagnol direction which has queen, where id is x - y + n
 * 4. Since all records can only be occupied exactly one queue, all set and reset operation will not overlapped
 */

import java.util.*;


public class Solution{
    private void backtrace(int n, int y, int num[], boolean[] column, boolean[] diagnoal, boolean[] antiDiagnoal){
        if(y == n){
            num[0]++;
            return;
        }
        
        for(int x = 0; x < n; ++x){
            int columnId = x;
            int diagnolId = x - y + n;
            int antiDiagnolId = x + y;
            
            if(!column[columnId] && !diagnoal[diagnolId] && !antiDiagnoal[antiDiagnolId]){
                column[columnId] = true;
                diagnoal[diagnolId] = true;
                antiDiagnoal[antiDiagnolId] = true;
                backtrace(n, y + 1, num, column, diagnoal, antiDiagnoal);
                column[columnId] = false;
                diagnoal[diagnolId] = false;
                antiDiagnoal[antiDiagnolId] = false;
            }
        }
    }
    
    public int totalNQueens(int n) {
        int[] num = new int[1];
        boolean[] column = new boolean[n];
        boolean[] diagnoal = new boolean[2*n];
        boolean[] antiDiagnoal = new boolean[2*n];
        backtrace(n, 0, num, column, diagnoal, antiDiagnoal);
        return num[0];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 4;
        System.out.println("n: " + n);
        System.out.println("number of solution: " + sol.totalNQueens(n));
    }
}
