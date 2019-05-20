/* Math: Time:O(n), Space:O(1), where n is length of instructions
 * 1. A single instruction is a line starting from (0, 0)
 * 2. The direction of the line is decided by the number of 'R's and 'L's, we represent it by (posAndDir[2], posAndDir[3])
 * 3. It can only be a retangle ,a line or a dot. Only a line cannot be bounded
 */          

import java.util.*; 
public class Solution {
    private void turnLeft(int[] posAndDir){
        if(posAndDir[2] == 1 && posAndDir[3] == 0){
            posAndDir[2] = 0;
            posAndDir[3] = -1;
        }else if(posAndDir[2] == 0 && posAndDir[3] == -1){
            posAndDir[2] = -1;
            posAndDir[3] = 0;
        }else if(posAndDir[2] == -1 && posAndDir[3] == 0){
            posAndDir[2] = 0;
            posAndDir[3] = 1;
        }else{
            posAndDir[2] = 1;
            posAndDir[3] = 0;
        }
    }
    
    private void turnRight(int[] posAndDir){
        if(posAndDir[2] == 1 && posAndDir[3] == 0){
            posAndDir[2] = 0;
            posAndDir[3] = 1;
        }else if(posAndDir[2] == 0 && posAndDir[3] == 1){
            posAndDir[2] = -1;
            posAndDir[3] = 0;
        }else if(posAndDir[2] == -1 && posAndDir[3] == 0){
            posAndDir[2] = 0;
            posAndDir[3] = -1;
        }else{
            posAndDir[2] = 1;
            posAndDir[3] = 0;
        }
    }
    
    public boolean isRobotBounded(String instructions) {
        int[] posAndDir = {0, 0, 1, 0};
        for(char c: instructions.toCharArray()){
            if(c == 'L'){
                turnLeft(posAndDir);
            }else if(c == 'R'){
                turnRight(posAndDir);
            }else{
                posAndDir[0] += posAndDir[2];
                posAndDir[1] += posAndDir[3];
            }
        }
        return (posAndDir[0] == 0 && posAndDir[1] == 0)? true: (posAndDir[2] != 1 || posAndDir[3] != 0);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String instructions = "GGLLGG";
        System.out.println("instructions: " + instructions);
        System.out.println("bounded in circle: " + sol.isRobotBounded(instructions));
    }
}
