/* Time:O(n) Space:O(n), where n is the number of characters
 * 1. Use split to separate string to real part and imaginary part 
 * 2. Execute complex multiplication and transfer the result back to string
 */

import java.util.*;

public class Solution{
    public String complexNumberMultiply(String a, String b) {
        String[] aStrings = a.split("\\+|i");
        String[] bStrings = b.split("\\+|i");
        int[] aNumbers = {Integer.parseInt(aStrings[0]), Integer.parseInt(aStrings[1])};
        int[] bNumbers = {Integer.parseInt(bStrings[0]), Integer.parseInt(bStrings[1])};
        int[] cNumbers = {0, 0};
        cNumbers[0] = aNumbers[0] * bNumbers[0] - aNumbers[1] * bNumbers[1];
        cNumbers[1] = aNumbers[0] * bNumbers[1] + aNumbers[1] * bNumbers[0];
        return Integer.toString(cNumbers[0]) + "+" + Integer.toString(cNumbers[1]) + "i";
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String a = "1+1i";
        String b = "1+1i";
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("a*b: " + sol.complexNumberMultiply(a, b));
    }
}
