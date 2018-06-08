/* Two pointers: Time:O(n), Space:O(1)
 * 1. Traverse from the tail, and get the effective character from both string
 * 2. If any effective character are not equal, then return fail
 */

import java.util.*;

public class Solution{
    private int getLastChar(String S, int end){
        int deleteCount = 0;
        while(end >= 0 && (S.charAt(end) == '#' || deleteCount > 0)){
            deleteCount += (S.charAt(end--) == '#')? 1: -1;
            if(end < 0){
                break;   
            }
        }
        return end;
    }
    
    public boolean backspaceCompare(String S, String T) {
        int[] index = {S.length() - 1, T.length() - 1};
        while(index[0] >= 0 || index[1] >= 0){
            index[0] = getLastChar(S, index[0]);
            index[1] = getLastChar(T, index[1]);
            if(index[0] >= 0 && index[1] >= 0){
                if(S.charAt(index[0]) != T.charAt(index[1])){
                    return false;
                }
            }
            else if(index[0] >= 0 || index[1] >= 0){
                return false;
            }
            index[0]--;
            index[1]--;
        }
        return (index[0] < 0 && index[1] < 0);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "ab#c";
        String T = "ad#c";
        System.out.println("S:" + S + ", T:" + T);
        System.out.println("equal? " + sol.backspaceCompare(S, T));
    }
}
