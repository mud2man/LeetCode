/* Devide and counquer: O(2^n), while O(n!) without memory
 * 1. Record all the taken steps into an array, and caculate the identifier by 2's weight
 * 2. Keep records of loseSet and winSet to save avoid duplicated searching
 * 3. If the step is taken by me, any one of step can make me force a win, then I win
 * 4. If the step is taken by opponent, the condition I win is that I can force a win whatever opponent make his step
 */

import java.util.*; // Stack

public class Solution {
    public int idCalc(int[] flags){
        int id, idx, weight;
        
        id = 0;
        weight = 2;
        for(idx = 1; idx < flags.length; ++idx){
            id += flags[idx] * weight;
            weight = 2 * weight;
        }
        return id;
    }
    
    public boolean helper(int[] flags, HashSet<Integer> winsSet, HashSet<Integer> loseSet, int residue, int leftStep){
        int id, idx, jdx;
        
        // I already lost the game
        if(residue <= 0 || leftStep == 0){
            loseSet.add(idCalc(flags));
            return false;
        }
        
        // check if I can force a win before under the same situation  
        id = idCalc(flags);
        if(winsSet.contains(id)){
            return true;
        }
        
        // check if I lost before under the same situation 
        if(loseSet.contains(id)){
            return false;
        }
        
        // taken by me
        // if I can win in any one of taking, then I can force a win
        for(idx = flags.length - 1; idx > 0; --idx){
            if(flags[idx] == 1 && idx >= residue){
                winsSet.add(idCalc(flags));
                return true;
            }
            
            if(flags[idx] == 0){
                continue;
            }
            flags[idx] = 0;
            leftStep--;
            if(leftStep == 0){
                flags[idx] = 1;
                return false;
            }
            
            // taken by opponent
            // if I can win whatever opponent take, then I can force a win
            for(jdx = flags.length - 1; jdx > 0; --jdx){
                if(flags[jdx] == 0){
                    continue;
                }
                flags[jdx] = 0;
                leftStep--;
                if(helper(flags, winsSet, loseSet, residue - idx -jdx, leftStep) == false){
                    flags[jdx] = 1;
                    leftStep++;
                    break;
                }
                flags[jdx] = 1;
                leftStep++;
            }
            flags[idx] = 1;
            leftStep++;
            
            if(jdx == 0){
                winsSet.add(idCalc(flags));
                return true; 
            }
        }
        loseSet.add(idCalc(flags));
        return false;
    }
    
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int[] flags;
        HashSet<Integer> winsSet, loseSet;
        int idx;
        
        flags = new int[maxChoosableInteger + 1];
        winsSet = new HashSet<Integer>();
        loseSet = new HashSet<Integer>();
        for(idx = 1; idx < flags.length; ++idx){
            flags[idx] = 1;
        }
        
        if(desiredTotal == 0){
            return true;
        }
        
        return helper(flags, winsSet, loseSet, desiredTotal, maxChoosableInteger);
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
