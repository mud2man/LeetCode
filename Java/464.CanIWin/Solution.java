/* Devide and counquer: O(2^n), while O(n!) without memory
 * 1. Record all the taken steps into an array, and caculate the identifier by 2's weight
 * 2. Keep records of lose set "lose" and win set "win" to  avoid duplicated searching
 * 3. Any one of step can make opponant cannot win, then I win
 */

import java.util.*; // Stack

public class Solution {
   private int getHashCode(boolean[] used){
        int hashCode = 0;
        for(int i = 1, weight = 1; i < used.length; ++i){
            hashCode += used[i]? weight: 0;
            weight = weight << 1;
        }
        return hashCode;
    }
    
    private boolean helper(boolean[] used, int remain, Set<Integer> win, Set<Integer> lose){
        int hashCode = getHashCode(used);
        if(remain <= 0){
            lose.add(hashCode);
            return false;
        }
        if(win.contains(hashCode)){
            return true;
        }
        if(lose.contains(hashCode)){
            return false;
        }
        
        for(int i = 1; i < used.length; ++i){
            if(used[i] == false){
                // my move
                used[i] = true;
                int nextRemain = remain - i;
                // opponant's move
                if(!helper(used, nextRemain, win, lose)){
                    win.add(hashCode);
                    used[i] = false;
                    return true;
                }
                used[i] = false;
            }
        }
        lose.add(hashCode);
        return false;
    }
    
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal == 0){
            return true;
        }
        int sum = (maxChoosableInteger + 1) * maxChoosableInteger / 2;
        if(sum < desiredTotal){
            return false;
        }
        boolean[] used = new boolean[maxChoosableInteger + 1];
        Set<Integer> win = new HashSet<>();
        Set<Integer> lose = new HashSet<>();
        return helper(used, desiredTotal, win, lose);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int maxChoosableInteger = 10;
        int desiredTotal = 11; 
        System.out.println("maxChoosableInteger: " + maxChoosableInteger + ", desiredTotal: " + desiredTotal);
        System.out.println("canIwin: " + sol.canIWin(maxChoosableInteger, desiredTotal));
    }
}
