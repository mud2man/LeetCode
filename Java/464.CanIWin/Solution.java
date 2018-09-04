/* Devide and counquer: O(2^n), while O(n!) without memory
 * 1. Record all the taken steps into an array, and caculate the identifier by 2's weight
 * 2. Keep records of loseSet and winSet to save avoid duplicated searching
 * 3. Any one of step can make me force a win, then I win
 */

import java.util.*; // Stack

public class Solution {
    private int getRemainStepsId(boolean[] remainSteps, int max){
        int remainStepsId = 0;
        int weight = 1;
        for(int i = 1; i <= max; ++i){
            weight = weight << 1;
            if(remainSteps[i] == true){
                remainStepsId |= weight;
            }
        }
        return remainStepsId;
    }
    
    private boolean helper(boolean[] remainSteps, Set<Integer> loseSet, Set<Integer> winSet, int desiredTotal, int max){
        int remainStepsId = getRemainStepsId(remainSteps, max);

        if(loseSet.contains(remainStepsId)){
            return false;
        }
        
        if(winSet.contains(remainStepsId)){
            return true;
        }
        
        if(desiredTotal <= 0){
            loseSet.add(remainStepsId);
            return false;
        }
        
        for(int i = 1; i <= max; ++i){
            if(remainSteps[i] == false){
                continue;
            }
            
            //push
            remainSteps[i] = false;
            if(!helper(remainSteps, loseSet, winSet, desiredTotal - i, max)){
                remainSteps[i] = true;
                winSet.add(remainStepsId);
                return true;
            }
            //push
            remainSteps[i] = true;
        }
        
        loseSet.add(remainStepsId);
        return false;
    }
    
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal == 0){
            return true;
        }
        
        double lb = (double)maxChoosableInteger;
        if((((lb + 1) / 2) * lb) < (double)desiredTotal){
            return false;
        }
        
        boolean[] remainSteps = new boolean[maxChoosableInteger + 1];
        Set<Integer> loseSet = new HashSet<Integer>();
        Set<Integer> winSet = new HashSet<Integer>();
        for(int i = 1; i <= maxChoosableInteger; ++i){
            remainSteps[i] = true;
        }
        
        return helper(remainSteps, loseSet, winSet, desiredTotal, maxChoosableInteger);
    }

    public static void main(String[] args){
        Solution sol;
        int maxChoosableInteger, desiredTotal; 

        sol = new Solution();
        maxChoosableInteger = 20;
        desiredTotal = 300; 
        
        System.out.println("maxChoosableInteger: " + maxChoosableInteger + ", desiredTotal: " + desiredTotal);
        System.out.println("canIwin: " + sol.canIWin(maxChoosableInteger, desiredTotal));
    }
}
