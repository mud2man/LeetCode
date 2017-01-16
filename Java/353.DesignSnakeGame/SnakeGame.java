/* : Hahset and LinkedList: O(1)
 * 1. Encode the position in matrix from 0 to width*height - 1
 * 2. Use HashSet to store the body of snake, and then use it to check if it bites itself
 * 3. Use LinedList to store the position of snack body, and use it to move snake
 */

import java.util.*;
public class SnakeGame {
    private HashSet<Integer> snakeBody;
    private LinkedList<Integer> snakeTrack;
    private LinkedList<Integer> foodTrain;
    private int width;
    private int height;
    private int score;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.snakeBody = new HashSet<Integer>();
        this.snakeTrack = new LinkedList<Integer>();
        this.foodTrain = new LinkedList<Integer>();
        
        this.snakeBody.add(0);
        this.snakeTrack.add(0);
        this.width = width;
        this.height = height;
        this.score = 0;
        
        for(int[] f: food){
            this.foodTrain.add(width*f[0] + f[1]);
        }
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int currHead;
        int nextHead;
        int food;
        
        currHead = snakeTrack.peekFirst();
        food = (foodTrain.isEmpty())? -1 : foodTrain.peek();
        
        //Check if hit boundry
        if(direction.equals("U")){
            if(currHead < width){
                return -1;
            }
            nextHead = currHead - width;
        }
        else if(direction.equals("L")){
            if((currHead % width) == 0){
                return -1;
            }
            nextHead = currHead - 1;
        }
        else if(direction.equals("R")){
            if(((currHead + 1) % width) == 0){
                return -1;
            }
            nextHead = currHead + 1;
        }
        else{
           if(currHead >= (width * (height - 1))){
                return -1;
            }
            nextHead = currHead + width; 
        }
        
        //Check if bites itself
        snakeBody.remove(snakeTrack.peekLast());
        if(snakeBody.contains(nextHead)){
            return -1;
        }
        snakeBody.add(snakeTrack.peekLast());
        
        //Check if eat food
        if(food == nextHead){
            score++;
            snakeTrack.addFirst(nextHead);
            snakeBody.add(nextHead);
            foodTrain.poll();
        }
        else{
            snakeTrack.addFirst(nextHead);
            snakeBody.remove(snakeTrack.pollLast());
            snakeBody.add(nextHead);
        }
        return score;
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
