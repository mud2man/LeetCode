/* Math: Time:O(n), Space:O(1)
 * 1. Skip 'X', only consider 'L' and 'R', and consider if their relative position is the same 
 * 2. There are only two cases can be transormed
 * 3. case1: start[startIndex] = 'R', end[endIndex] = 'R', startIndex <= endIndex
 * 4. case2: start[startIndex] = 'L', end[endIndex] = 'L', startIndex >= endIndex
 */

import java.util.*;

public class Solution{
    public boolean canTransform(String start, String end) {
        if(start.length() != end.length()){
            return false;
        }
        
        int startIndex = 0;
        int endIndex = 0;
        int length = start.length();
        while(startIndex < length && endIndex < length){
            while(startIndex < length && start.charAt(startIndex) == 'X'){
                startIndex++;
            }
            
            while(endIndex < length && end.charAt(endIndex) == 'X'){
                endIndex++;
            }

            if(startIndex == length && endIndex == length){
                return true;
            }
            
            if(startIndex == length || endIndex == length){
                return false;
            }
            
            if(start.charAt(startIndex) != end.charAt(endIndex)){
                return false;
            }
            
            if(start.charAt(startIndex) == 'R' && startIndex > endIndex){
                return false;
            }     
            
            if(start.charAt(startIndex) == 'L' && startIndex < endIndex){
                return false;
            }
            
            startIndex++;
            endIndex++;
        }
        return true;
    }

    public static void main(String[] args){
        Solution sol;
        String start = "RXXLRXRXL";
        String end = "XRLXXRRLX";

        sol = new Solution();
        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("can transform?: " + sol.canTransform(start, end));
    }
}
