/* Recursive: Time:O(2^n), Space:O(n)
 * 1. Have a global variable player1Win to have a higher priority than the return value of "canIwin"
 * 2. In "canIwin", pick the the left end or right end number, and return true if the next invoke of "canIwin" return false
 * 3. If both "canIwin" return true, then I have no chance to win. So return false
 */

import java.util.*;

public class Solution{
    private boolean canIwin(int l, int r, int[] nums, int myScore, int hisScore, boolean[] player1Win){
        if(player1Win[0] == true || l > r){
            if(myScore == hisScore){
                player1Win[0] = true;
            }
            return (player1Win[0] | (myScore > hisScore));
        }
        
        if(canIwin(l + 1, r, nums, hisScore, myScore + nums[l], player1Win) == false){
            return true;
        }
        
        if(canIwin(l, r - 1, nums, hisScore, myScore + nums[r], player1Win) == false){
            return true;
        }
        
        return false;
    }
    
    public boolean PredictTheWinner(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        boolean[] player1Win = new boolean[1];
        boolean ret = canIwin(l, r, nums, 0, 0, player1Win);
        return (player1Win[0] == true || ret == true);
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 5, 233, 7};
        
        sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can I win ?: " + sol.PredictTheWinner(nums));
    }
}
