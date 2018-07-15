/* DFS: Time:O(n), Space:O(n)
 * 1. Have a wrapper class including current position, and current direction of robor
 * 2. Use set "visited" to record visited position
 * 3. In DFS, go back the previous position by the information "from" after visiting all four directions
 */

import java.util.*;

// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();
    public void turnRight();

    // Clean the current cell.
    public void clean();
}
 

public class Solution{
    private class IndexdRobot{
        int y;
        int x;
        //up: 0, right: 1, down: 2, left: 3
        int dirc;
        Robot robot;
        IndexdRobot(int i, int j, Robot r){y = i; x = j; dirc = 0; robot = r;}
    }
    
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    private void turn(IndexdRobot ir, int dir){
        while(ir.dirc != dir){
            ir.robot.turnRight();
            ir.dirc = (ir.dirc + 1) % 4;
        }
    }
    
    private void dfs(Set<String> visited, int from, IndexdRobot ir){
        String pos = Integer.toString(ir.y) + "." + Integer.toString(ir.x);
        visited.add(pos);
        ir.robot.clean();
        
        for(int dir = 0; dir < 4; ++dir){
            turn(ir, dir);
            int nextY = ir.y + dirs[dir][0];
            int nextX = ir.x + dirs[dir][1];
            String nextPos = Integer.toString(nextY) + "." + Integer.toString(nextX);
            if(!visited.contains(nextPos) && ir.robot.move()){
                ir.y += dirs[dir][0];
                ir.x += dirs[dir][1];
                dfs(visited, dir, ir);
            }
        }
        
        switch (from){
            case 0:
                turn(ir, 2);
                ir.y++;
                break;
            case 1:
                turn(ir, 3);
                ir.x--;
                break;
            case 2:
                turn(ir, 0);
                ir.y--;
                break;
            case 3:
                turn(ir, 1);
                ir.x++;
                break;
            default:
                return;
        }
        ir.robot.move();
    }
    
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        IndexdRobot ir = new IndexdRobot(0, 0, robot);
        dfs(visited, -1, ir);
    }
  
    public static void main(String[] args){
        System.out.println("No test cases");
    }
}
