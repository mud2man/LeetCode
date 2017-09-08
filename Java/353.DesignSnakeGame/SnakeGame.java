/* : Hahset and LinkedList: O(1)
 * 1. Encode the position in matrix from 0 to width*height - 1
 * 2. Use HashSet to store the body of snake, and then use it to check if it bites itself
 * 3. Use LinedList to store the position of snack body, and use it to move snake
 */

import java.util.*;
public class SnakeGame {
    private HashSet<Integer> snakeHash;
    private LinkedList<Integer> snakePosition;
    private LinkedList<Integer> foods;
    int width;
    int height;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.foods = new LinkedList<Integer>();
        for(int[] position: food){
            this.foods.add(position[0] * width + position[1]);
        }
        this.snakeHash = new HashSet<Integer>();
        this.snakeHash.add(0);
        this.snakePosition = new LinkedList<Integer>();
        this.snakePosition.add(0);
    }
    
    private boolean isValid(int headPosition, int tailPosition, int[] offset, HashSet<Integer> snakeHash, int width, int height){
        int y = headPosition / width + offset[0];
        int x = headPosition % width + offset[1];
        
        if(y < 0 || y >= height || x < 0 || x >= width){
            return false;
        }
        
        int nextHeadPosition = y * width + x;
        if(nextHeadPosition != tailPosition && snakeHash.contains(nextHeadPosition)){
            return false;
        }
        
        return true;
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int headPosition = snakePosition.getLast();
        int tailPosition = snakePosition.getFirst();
        int food = (!foods.isEmpty())? foods.getFirst(): -1;
        int[] offset;
        
        switch(direction){
            case "U":
                offset = new int[]{-1, 0};
                break;
            case "L":
                offset = new int[]{0, -1};
                break;
            case "R":
                offset = new int[]{0, 1};
                break;
            case "D":
                offset = new int[]{1, 0};
                break;
            default:
                offset = new int[]{0, 0};
        }
        
        if(!isValid(headPosition, tailPosition, offset, snakeHash, width, height)){
            return -1;
        }
        
        int nextHeadPosition = headPosition + offset[0] * width + offset[1];
        if(nextHeadPosition == food){
            snakePosition.add(nextHeadPosition);
            snakeHash.add(nextHeadPosition);
            foods.pollFirst();
        }
        else{
            snakePosition.pollFirst();
            snakePosition.add(nextHeadPosition);
            snakeHash.remove(tailPosition);
            snakeHash.add(nextHeadPosition);
        }
        return snakePosition.size() - 1;
    }

    public static void main(String[] args){
        int width;
        int height;
        SnakeGame snake;
        int[][] food = {{1,2}, {0,1}};
        
        width = 3;
        height = 2;
        snake = new SnakeGame(width, height, food);
        
        System.out.println("width: " + width + ", height: " + height);
        System.out.print("food: ");
        for(int[] f: food){
            System.out.print(Arrays.toString(f));
        }
        System.out.println("");
        
        System.out.println("move right: " + snake.move("R"));
        System.out.println("move down: " + snake.move("D"));
        System.out.println("move right: " + snake.move("R"));
        System.out.println("move up: " + snake.move("U"));
        System.out.println("move left: " + snake.move("L"));
        System.out.println("move up: " + snake.move("U"));
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
