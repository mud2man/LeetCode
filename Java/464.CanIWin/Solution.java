/* Devide and counquer: O(2^n), while O(n!) without memory
 * 1. Record all the taken steps into an array, and caculate the identifier by 2's weight
 * 2. Keep records of win/lose to "cache" avoid duplicated searching
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
    
    private boolean helper(boolean[] used, int remain, Map<Integer, Boolean> cache){
        int hashCode = getHashCode(used);
        if(remain <= 0){
            cache.put(hashCode, false);
            return false;
        }else if(cache.containsKey(hashCode)){
            return cache.get(hashCode);
        }else{
            for(int i = 1; i < used.length; ++i){
                if(used[i] == false){
                    used[i] = true;
                    if(!helper(used, remain - i, cache)){
                        cache.put(hashCode, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
        }
        cache.put(hashCode, false);
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
        Map<Integer, Boolean> cache = new HashMap<>();
        return helper(new boolean[maxChoosableInteger + 1], desiredTotal, cache);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int maxChoosableInteger = 10;
        int desiredTotal = 11; 
        System.out.println("maxChoosableInteger: " + maxChoosableInteger + ", desiredTotal: " + desiredTotal);
        System.out.println("canIwin: " + sol.canIWin(maxChoosableInteger, desiredTotal));
    }
}
