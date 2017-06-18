/* Backtrack: O(n!)
 * 1. Create s list of rows with only one queen, which every queen is on different position
 * 2. It can guarantee no conflict in x-direction and y-direction
 * 3. Use "HashSet[] avalibilities" to record every available positions in each rows
 * 4. Before invoke "pickUpRow", call "tag" method to mark the unavailable positions in the following rows
 * 5. After invoke "pickUpRow", call "unTag" method to retrieve the unavailabel positions in the following rows 
 */

import java.util.*;


public class Solution{
    public void tag(HashSet<Integer>[] avalibilities, int y, int x, int boundry, int[][] obstacles){
        int left, right;
        
        left = x - 1;
        right = x + 1;
        obstacles[y][x] = 1;
        for(int row = y + 1; row < avalibilities.length; ++row){
            avalibilities[row].remove(x);
            obstacles[row][x]++;
            if(left >= 0){
                avalibilities[row].remove(left);
                obstacles[row][left]++;
                left--;
            }
            if(right < boundry){
                avalibilities[row].remove(right);
                obstacles[row][right]++;
                right++;
            }
        }
    }
    
    public void unTag(HashSet<Integer>[] avalibilities, int y, int x, int boundry, int[][] obstacles){
        int left, right;
        
        left = x - 1;
        right = x + 1;
        obstacles[y][x] = 0;
        for(int row = y + 1; row < avalibilities.length; ++row){
            obstacles[row][x]--;
            if(obstacles[row][x] == 0)
                avalibilities[row].add(x);
                
            if(left >= 0){
                obstacles[row][left]--;
                if(obstacles[row][left] == 0)
                    avalibilities[row].add(left);
                left--;
            }
            
            if(right < boundry){
                obstacles[row][right]--;
                if(obstacles[row][right] == 0)
                    avalibilities[row].add(right);
                right++;
            }
        }
    }
    
    public void pickUpRow(List<List<String>> boards, HashMap<Integer, String> rowsMap, HashSet<Integer>[] avalibilities, 
                          List<String> board, int boundry, int[][] obstacles){
        int y = board.size();
        
        if(y == avalibilities.length){
            boards.add(new ArrayList(board));
            return;
        }
        
        for(int x: avalibilities[y]){
            board.add(new String(rowsMap.get(x)));
            tag(avalibilities, y, x, boundry, obstacles);
            pickUpRow(boards, rowsMap, avalibilities, board, boundry, obstacles);
            unTag(avalibilities, y, x, boundry, obstacles);
            board.remove(board.size() - 1);
        }
    }
    
    public List<List<String>> solveNQueens(int n) {
        int i;
        char[] row;
        HashMap<Integer, String> rowsMap;
        List<List<String>> boards;
        HashSet[] avalibilities;
        Integer[] avalibility;
        int[][] obstacles;
        
        boards = new ArrayList<List<String>>();
        rowsMap = new HashMap<Integer, String>();
        row = new char[n];
        avalibility = new Integer[n];
        avalibilities = new HashSet[n];
        obstacles = new int[n][];
        for(i = 0; i < n; ++i){
            row[i] = '.';
            avalibilities[i] = new HashSet<Integer>();
            avalibility[i] = i;
            obstacles[i] = new int[n];
        }
        
        for(i = 0; i < n; ++i){
            row[i] = 'Q';
            rowsMap.put(i, new String(row));
            row[i] = '.';
            avalibilities[i] = new HashSet<Integer>(Arrays.asList(avalibility));
        }
        pickUpRow(boards, rowsMap, avalibilities, new ArrayList<String>(), n, obstacles);
        return boards;
    }

    public static void main(String[] args){
        Solution sol;
        int n;
        List<List<String>> boards;

        sol = new Solution();
        n = 4;

        boards = sol.solveNQueens(n);
        
        System.out.println("boards with n: " + n);
        for(List<String> board: boards){
            System.out.println(board);
        }
    }
}
