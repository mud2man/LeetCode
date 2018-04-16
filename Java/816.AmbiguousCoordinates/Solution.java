/* Backtrack: Time:O(n), Space:O(1). Where n is the number of possible permutations
 * 1. In backtrack, check if both x and y have leading zero. Return if yes
 * 2. If index reach to the end, check if both x and y is valid number, Add it to coordinates if yes
 * 3. Shift index and call backtrack recursively
 */         

import java.util.*;

public class Solution {
    private boolean noLeadingZero(String x, boolean hasPoint){
        if(hasPoint){
            if(x.length() >= 3 && x.charAt(0) == '0' && x.charAt(1) == '0'){
                return false;
            }
        }
        else{
            if(x.length() > 1 && x.charAt(0) == '0'){
                return false;
            }
        }
        return true;
    }
    
    private boolean isNumber(String x, boolean hasPoint){
        if(hasPoint){
            if(x.charAt(x.length() - 1) == '0' || x.charAt(x.length() - 1) == '.'){
                return false;
            }
        }
        else{
            if(x.length() == 0){
                return false;
            }
        }
        return true;
    }
    
    private void backtrack(String S, int index, String x, boolean xPoint, String y, boolean yPoint, List<String> coordinates){
        if(!noLeadingZero(x, xPoint) || !noLeadingZero(y, yPoint)){
            return;
        }
        
        if(index == (S.length() - 1)){
            if(isNumber(x, xPoint) && isNumber(y, yPoint)){
                coordinates.add("(" + x + ", " + y + ")");
            }
            return;
        }
        
        char c = S.charAt(index);
        if(y.length() == 0){
            backtrack(S, index + 1, x + c, xPoint, y, yPoint, coordinates);
            if(xPoint == false && x.length() > 0){
               backtrack(S, index + 1, x + "." + c, true, y, yPoint, coordinates); 
            }
        }
        backtrack(S, index + 1, x, xPoint, y + c, yPoint, coordinates);
        if(yPoint == false && y.length() > 0){
            backtrack(S, index + 1, x, xPoint, y + "." + c, true, coordinates);
        }
    }
    
    public List<String> ambiguousCoordinates(String S) {
        List<String> coordinates = new ArrayList<String>();
        backtrack(S, 1, "", false, "", false, coordinates);
        return coordinates;
    }

    public static void main(String[] args){
        Solution sol;
        String s = "(123)";
        sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("cordinates: " + sol.ambiguousCoordinates(s));
    }
}
