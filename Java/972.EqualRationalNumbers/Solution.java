/* Math: Time:O(1), Spaceo(1). LeetCode has 20-line solution
 * 1. Get the three parts integerPart, nonRepeatingPart, and repeatingPart
 * 2. Reduce the repeatingPart, and expend the nonRepeatingPart to 4 digits
 * 3. We replace repeatingPart with 9 if repeatingPart is empty or "0", and decrease 1 from nonRepeatingPart or integerPart
 * 4. Two numer are the same on if the three parts are all the same
 * 
 * ex: 0.(52) -> 0.5252(52), 0.5(25) -> 0.5252(52) 
 *     0.1666(6) -> 0.1666(6), 0.166(66) -> 0.1666(6)
 *     0.9(9) -> 0.9999(9), 1. -> 0.9999(9)
 */

import java.util.*; // Stack

public class Solution {
    private String reduce(String s){
        for(int i = 1; i < 4 && s.length() > 0; ++i){
            boolean success = true;
            String pattern = s.substring(0, i);
            for(int j = 0; j < s.length(); j += pattern.length()){
                String substring = s.substring(j, Math.min(j + pattern.length(), s.length()));
                if(!pattern.startsWith(substring)){
                    success = false;
                    break;
                }
            }
            if(success){
                return pattern;
            }
        }
        return s;
    }
    
    private String[] getParts(String s){
        int pointIdx = s.indexOf('.');
        int leftIdx = s.indexOf('(');
        int rightIdx = s.indexOf(')');
        String integerPart = s.substring(0, pointIdx);
        String nonRepeatingPart = (leftIdx != -1)? s.substring(pointIdx + 1, leftIdx): s.substring(pointIdx + 1);
        String repeatingPart = (leftIdx != -1)? s.substring(leftIdx + 1, rightIdx): "";
        repeatingPart = reduce(repeatingPart);
        if(repeatingPart.equals("") || Integer.valueOf(repeatingPart) == 0){
            if(integerPart.equals("0") && (nonRepeatingPart.equals("") || Integer.valueOf(nonRepeatingPart) == 0)){
                repeatingPart = "0";
            }
            else if(nonRepeatingPart.equals("") || Integer.valueOf(nonRepeatingPart) == 0){
                repeatingPart = "9";
                nonRepeatingPart = "9";
                integerPart = Integer.toString(Integer.valueOf(integerPart) - 1);
            }else{
                repeatingPart = "9";
                nonRepeatingPart = nonRepeatingPart.substring(0, nonRepeatingPart.lastIndexOf('0') + 1) + Integer.toString(Integer.valueOf(nonRepeatingPart) - 1);
            }
        }
        int len = 4 - nonRepeatingPart.length();
        StringBuilder sb = new StringBuilder(nonRepeatingPart);
        for(int i = 0; i < len; ++i){
            sb.append(repeatingPart.charAt(i % repeatingPart.length()));
        }
        nonRepeatingPart = sb.toString();
        sb = new StringBuilder("");
        for(int i = 0; i < repeatingPart.length(); ++i){
            sb.append(repeatingPart.charAt((len + i) % repeatingPart.length()));
        }
        repeatingPart = sb.toString();
        return new String[]{integerPart, nonRepeatingPart, repeatingPart};
    }
    
    public boolean isRationalEqual(String S, String T) {
        String[] sParts = getParts((S.indexOf('.') == -1)? S + ".": S);
        String[] tParts = getParts((T.indexOf('.') == -1)? T + ".": T);
        return sParts[0].equals(tParts[0]) & sParts[1].equals(tParts[1]) & sParts[2].equals(tParts[2]);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "0.(52)";
        String T = "0.5(25)";
        System.out.println("S:" + S);
        System.out.println("T:" + T);
        System.out.println("equal:" + sol.isRationalEqual(S, T));
    }
}
