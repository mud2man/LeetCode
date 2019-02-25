/* Backtrack: O(n!)
 * 1. Have 3 sets leftParents, rightParents, upParents to record the occupied position, 
 *    where rightParent = i + curr, leftParent = i - curr, upParent = i on the row "curr"
 * 2. Use backtrack to find the available position if leftParent, rightParent, and upParent not in the parents set
 */

import java.util.*;


public class Solution{
    private String genRow(int queen, int n){
        String row = "";
        for(int i = 0; i < queen; ++i){
            row += ".";
        }
        row += "Q";
        for(int i = queen + 1; i < n; ++i){
            row += ".";
        }
        return row;
    }
    
    private void backtrack(int curr, Deque<Integer> path, int n, Set<Integer> leftParents, Set<Integer> rightParents,
                           Set<Integer> upParents, List<List<String>> boards){
        if(curr == n){
            List<String> board = new ArrayList<>();
            for(int queen: path){
                board.add(genRow(queen, n));
            }
            boards.add(board);
            return;
        }
        
        for(int i = 0; i < n; ++i){
            int rightParent = i + curr;
            int leftParent = i - curr;
            int upParent = i;
            if(!leftParents.contains(leftParent) && !rightParents.contains(rightParent) && !upParents.contains(upParent)){
                path.addLast(i);
                leftParents.add(leftParent);
                rightParents.add(rightParent);
                upParents.add(upParent);
                backtrack(curr + 1, path, n, leftParents, rightParents, upParents, boards);
                path.pollLast();
                leftParents.remove(leftParent);
                rightParents.remove(rightParent);
                upParents.remove(upParent);
            }
        }
    }
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> boards = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        backtrack(0, path, n, new HashSet<>(), new HashSet<>(), new HashSet<>(), boards);
        return boards;
    }

    public static void main(String[] args){
        Solution sol= new Solution();
        int n = 4;
        List<List<String>> boards;
        boards = sol.solveNQueens(n);
        System.out.println("boards with n: " + n);
        for(List<String> board: boards){
            System.out.println(board);
        }
    }
}
