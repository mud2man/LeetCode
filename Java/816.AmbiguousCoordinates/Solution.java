/* Time:O(n), Space:O(1). Where n is the number of possible permutations
 * 1. Split S to x and y, and find their correesponding combinations
 * 2. Multiply two combinations, and return the result
 */         

import java.util.*;

public class Solution {
    private List<String> geCombinations(String x){
        List<String> combinations = new ArrayList<String>();
        if(x.charAt(0) == '0' && x.length() > 1 && x.charAt(x.length() - 1) != '0'){
            combinations.add(x.substring(0, 1) + "." + x.substring(1));
        }
        else{
            if(x.charAt(0) != '0' || x.length() == 1){
                combinations.add(x);
            }
            for(int i = 1; i < x.length() && x.charAt(x.length() - 1) != '0'; ++i){
                combinations.add(x.substring(0, i) + "." + x.substring(i));
            }
        }
        return combinations;
    }
    
    private List<String> getCombinations(String x, String y){
        List<String> xCombinations = geCombinations(x);
        List<String> yCombinations = geCombinations(y);    
        List<String> xyCombinations = new ArrayList<String>();
        for(String xCombination: xCombinations){
            for(String yCombination: yCombinations){
                xyCombinations.add("(" + xCombination + ", " + yCombination + ")");
            }
        }
        return xyCombinations;
    }
    
    private boolean isValid(String s){
        int zeroCount = 0;
        for(int i = 0; i < s.length(); ++i){
            if(s.charAt(i) != '0'){
                return true;
            }
            zeroCount++;
        }
        return zeroCount < 2;
    }
    
    public List<String> ambiguousCoordinates(String S) {
        S = S.substring(1, S.length() - 1);
        List<String> coordinates = new ArrayList<String>();
        for(int i = 0; i < (S.length() - 1); ++i){
            String x = S.substring(0, i + 1);
            String y = S.substring(i + 1);
            if(isValid(x) && isValid(y)){
                coordinates.addAll(getCombinations(x, y));
            }
        }
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
