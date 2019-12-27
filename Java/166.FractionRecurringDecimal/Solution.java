/* Hash: Time:O(n), Space:O(n)
 * 1. First, we caculate the number part and append it to "fraction"
 * 2. And then, we caculate the fraction part digit by digit
 * 3. Also we have a hashmap "numeratorToIndex" to record the visited numerator and its according index in fraction
 * 4. If we encouner the visited numerator, we know there is a cycle. Then we add "(" and ")"
 */

import java.util.*;

public class Solution{
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder fraction = new StringBuilder("");
        fraction.append(((long)numerator * (long)denominator < 0)? "-": "");
        long longNumerator = Math.abs((long)numerator);
        long longDenominator = Math.abs((long)denominator);
        if(longNumerator >= longDenominator){
            fraction.append(Long.toString(longNumerator / longDenominator));
            longNumerator = longNumerator % longDenominator;
        }else{
            fraction.append('0');
        }
        
        fraction.append((longNumerator > 0)? ".": "");
        int offset = fraction.length();
        HashMap<Long, Integer> numeratorToIndex = new HashMap<Long, Integer>();
        longNumerator = longNumerator * 10;
        while(longNumerator > 0){
            while(longNumerator < longDenominator){
                longNumerator = longNumerator * 10;
                fraction.append('0');
                offset++;
            }
            
            if(numeratorToIndex.containsKey(longNumerator)){
                int insertIndex = numeratorToIndex.get(longNumerator);
                fraction.insert(insertIndex, '(');
                fraction.append(')');
                break;
            }else{
                numeratorToIndex.put(longNumerator, offset);
                fraction.append(Long.toString(longNumerator / longDenominator));
                longNumerator = (longNumerator % longDenominator) * 10;
            }
            offset++;
        }
        return fraction.toString();
    }
 
    public static void main(String[] args){
        int numerator = 2;
        int denominator = 3;
        Solution sol = new Solution();
        System.out.println("numerator:" + numerator + ", denominator:" + denominator);
        System.out.println("answer: " + sol.fractionToDecimal(numerator, denominator));
    }
}
