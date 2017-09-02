/* Backtrack with memorization: O(n!)
 * 1. If any case can make sure I win in the next match, the I win 
 * 2. If I win under no matter how opponent move in the next matches, then I win
 */

import java.util.*;

public class Solution{
    private boolean helper(HashSet<String> mustWinSet, HashSet<String> mayLoseSet, String s){
        if(mustWinSet.contains(s)){
            return true;
        }
        
        if(mayLoseSet.contains(s)){
            return false;
        }

        boolean canWin = false;
        for(int i = 0; i < (s.length() - 1); ++i){
            // if any step can cause I winm then I win
            if(s.startsWith("++", i)){
                canWin = true;
                String firstS = s.substring(0, i) + "--" + s.substring(i + 2);
                // must win under all the situations moved by opponent, then I win
                for(int j = 0; j < (firstS.length() - 1); ++j){
                    if(firstS.startsWith("++", j)){
                        String secondS = firstS.substring(0, j) + "--" + firstS.substring(j + 2);
                        if(!helper(mustWinSet, mayLoseSet, secondS)){
                            mayLoseSet.add(secondS);
                            canWin = false;
                            break;
                        }
                    }
                }
                if(canWin == true){
                    break;
                }
            }
        }
        
        if(canWin){
            mustWinSet.add(s);
            return true;
        }
        else{
            mayLoseSet.add(s);
            return false;
        }
    }
    
    public boolean canWin(String s) {
        HashSet<String> mustWinSet = new HashSet<String>();
        HashSet<String> mayLoseSet = new HashSet<String>();
        return helper(mustWinSet, mayLoseSet, s);
    }

    public static void main(String[] args){
        Solution sol;
        String s;

        s = "++++";
        sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("canWin: " + sol.canWin(s));
    }
}
