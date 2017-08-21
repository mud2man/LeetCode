/* Backtrace: O(n), n = number of strobogrammatic number
 * 1. Call backtracker with parameter "s"+strobogrammaticNum +"s", where s = "0", "1", "6", "8" and "9"
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private void backtracker(List<String> strobogrammaticNums, String strobogrammaticNum, int n){
        if(strobogrammaticNum.length() == n){
            strobogrammaticNums.add(strobogrammaticNum);
        }
        else if(strobogrammaticNum.length() == (n - 2)){
            backtracker(strobogrammaticNums, "1" + strobogrammaticNum + "1", n);
            backtracker(strobogrammaticNums, "6" + strobogrammaticNum + "9", n);
            backtracker(strobogrammaticNums, "8" + strobogrammaticNum + "8", n);
            backtracker(strobogrammaticNums, "9" + strobogrammaticNum + "6", n);
        }
        else{
            backtracker(strobogrammaticNums, "0" + strobogrammaticNum + "0", n);
            backtracker(strobogrammaticNums, "1" + strobogrammaticNum + "1", n);
            backtracker(strobogrammaticNums, "6" + strobogrammaticNum + "9", n);
            backtracker(strobogrammaticNums, "8" + strobogrammaticNum + "8", n);
            backtracker(strobogrammaticNums, "9" + strobogrammaticNum + "6", n);
        }
    }
    
    public List<String> findStrobogrammatic(int n) {
        List<String> strobogrammaticNums = new ArrayList<String>();
        
        if(n % 2 == 1){
            backtracker(strobogrammaticNums, "0", n);
            backtracker(strobogrammaticNums, "1", n);
            backtracker(strobogrammaticNums, "8", n);
        }
        else{
            backtracker(strobogrammaticNums, "", n);
        }
        
        return strobogrammaticNums;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        List<String> strobogrammaticWords;
        int n = 3;

        strobogrammaticWords = sol.findStrobogrammatic(n);

        System.out.println("n: " + n);
        System.out.println("strobogrammaticWords: " + strobogrammaticWords);
    }
}
