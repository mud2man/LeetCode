/* HashSet: O(1)
 * 1. Have columnsConditions, rowsConditions to record the condition for every column and row
 * 2. Have diagnalTopLeftCondition and diagnalTopRightCondition to record the diagnal direction
 * 3. In class Condion, condition.owner = "Null" means no one occupy, condition.owner = "Impossible" means impossible to success
 * 4. If condition.remains is empty, return the successful player
 */          

import java.util.*; // Stack

public class TicTacToe{
    /** Initialize your data structure here. */
    private class Condition{
        String owner;
        Set<Integer> remains;
        Condition(String o, Set<Integer> r){owner = o; remains = r;}
    }
    
    private Condition[] columnsConditions;
    private Condition[] rowsConditions;
    private Condition diagnalTopLeftCondition;
    private Condition diagnalTopRightCondition;
    private int width;
    
    public TicTacToe(int n) {
        width = n;
        
        columnsConditions = new Condition[n];
        for(int column = 0; column < n; ++column){
            String owner = new String("Null");
            Set<Integer> remains = new HashSet<Integer>();
            int position = column;
            for(int i = 0; i < n; ++i){
                remains.add(position);
                position = position + n;
            }
            columnsConditions[column] = new Condition(owner, remains);
        }
        
        rowsConditions = new Condition[n];
        for(int row = 0; row < n; ++row){
            String owner = new String("Null");
            Set<Integer> remains = new HashSet<Integer>();
            int position = row * n;
            for(int i = 0; i < n; ++i){
                remains.add(position);
                position = position + 1;
            }
            rowsConditions[row] = new Condition(owner, remains);
        }
        
        String owner = new String("Null");
        Set<Integer> remains = new HashSet<Integer>();
        int position = 0;
        for(int column = 0; column < n; ++column){
            remains.add(position);
            position = position + 1 + n;
        }
        diagnalTopLeftCondition = new Condition(owner, remains);
        
        owner = new String("Null");
        remains = new HashSet<Integer>();
        position = n - 1;
        for(int column = n - 1; column >= 0; --column){
            remains.add(position);
            position = position - 1 + n;
        }
        diagnalTopRightCondition = new Condition(owner, remains);   
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        Condition[] conditions = new Condition[]{columnsConditions[col], rowsConditions[row], 
                                                 diagnalTopLeftCondition, diagnalTopRightCondition};
        
        int position = col + row * width;
        String currPlayer = Integer.toString(player);
        for(Condition condition: conditions){
            if(condition.owner.equals("Null")){
                if(condition.remains.contains(position)){
                    condition.remains.remove(position);
                    condition.owner = currPlayer;
                }
            }
            else if(condition.owner.equals(currPlayer)){
                if(condition.remains.contains(position)){
                    condition.remains.remove(position);
                    if(condition.remains.isEmpty()){
                        return player;
                    }
                }
            }
            else{
                if(condition.remains.contains(position)){
                    condition.owner = new String("Impossible");
                    condition.remains.remove(position);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args){
        TicTacToe tic = new TicTacToe(3);
        int row;
        int col;
        int player;
        
        row = 0; 
        col = 0;
        player = 1;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 0; 
        col = 2;
        player = 2;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 2; 
        col = 2;
        player = 1;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 1; 
        col = 1;
        player = 2;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 2; 
        col = 0;
        player = 1;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 1; 
        col = 0;
        player = 2;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
        
        row = 2; 
        col = 1;
        player = 1;
        System.out.println("row:" + row + ", col:" + col + ", player:" + player + "result: " + tic.move(row, col, player));
    }
}
