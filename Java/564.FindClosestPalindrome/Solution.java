/* Math: O(n), where k is the length of the longest string
 * 1. Get the lower bound "lb". e.g., lb of 100 is 99
 * 2. Get the upper bound "ub". e.g., ub of 100 is 1001
 * 3. Get the palidrome with the same left half of n "equal". e.g., equal of 100 is 101
 * 4. Get the palidrome with the left half of n plus 1 "big". e.g., big of 101 is 111
 * 5. Get the palidrome with the left half of n minus 1 "big". e.g., small of 100 is 99
 * 6. Get the nearest palindrome with the order {lb, small, equal, big, ub}
 */

import java.util.*;
import java.math.*;

public class Solution{
    private void mirrow(StringBuilder sb, int end){
        for(int i = end; i >= 0; --i){
            sb.append(sb.charAt(i));
        }
    }
    
    public String nearestPalindromic(String n) {
        List<StringBuilder> candidates = new ArrayList<>();
        int len = n.length();
        StringBuilder equal = new StringBuilder();
        for(int i = 0; i < (len + 1) / 2; ++i){
            equal.append(n.charAt(i));
        }
        
        StringBuilder lb = (len == 1)? new StringBuilder("0"): new StringBuilder();
        for(int i = 0; i < len - 1; ++i){
            lb.append('9');
        }
        
        StringBuilder ub = new StringBuilder();
        ub.append('1');
        for(int i = 0; i < len - 1; ++i){
            ub.append('0');
        }
        ub.append('1');

        StringBuilder big = new StringBuilder(Long.toString(Long.valueOf(equal.toString()) + 1));
        StringBuilder small = new StringBuilder(Long.toString(Long.valueOf(equal.toString()) - 1));
        mirrow(equal, (len % 2 == 1)? equal.length() - 2: equal.length() - 1);
        mirrow(big, (len % 2 == 1)? big.length() - 2: big.length() - 1);
        mirrow(small, (len % 2 == 1)? small.length() - 2: small.length() - 1);
        
        // candidates are sorted by value
        candidates.add(lb);
        candidates.add(small);
        candidates.add(equal);
        candidates.add(big);
        candidates.add(ub);
        double minDiff = Double.MAX_VALUE;
        StringBuilder nearest = null;
        for(StringBuilder candidate: candidates){
            double diff = Math.abs(Double.valueOf(candidate.toString()) - Double.valueOf(n));
            diff = (diff == 0.0)? Double.MAX_VALUE: diff;
            if(diff < minDiff){
                minDiff = diff;
                nearest = candidate;
            }
        }
        return nearest.toString();
    }
 
    public static void main(String[] args){
        Solution sol= new Solution();
        String n = "123";
        System.out.println("n: " + n);
        System.out.println("closestPalindrome: " + sol.nearestPalindromic(n));
	}
}
