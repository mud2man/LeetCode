/* Math: O(n*k), where k is the length of the longest string
 * 1. Scan the input string from the leftest position
 * 2. Get the lower bound from the current position (e.g., getLowBound(18XXXX) = 179971)
 * 3. Get the higher bound from the current position (e.g., getLowBound(18XXXX) = 190091)
 * 4. Pick the one from lower and higher bound with smaller difference into list candidates
 * 5. Get the final cnadidate (e.g., 18972 => 18981), and put it into candidates
 * 6. Get the candiate with the minimum difference, and return
 */

import java.util.*;
import java.math.*;

public class Solution{
    private BigInteger getBound(StringBuilder commonPart, char digit, int length, boolean isLowBound){
        char appendDigit;
        StringBuilder bound = new StringBuilder(commonPart);

        if(isLowBound){
            if(commonPart.length() == 0 && digit == '1'){
                if(length == 1){
                    bound.append('0');
                }
                else{
                    for(int i = 0; i < length - 1; ++i){
                        bound.append('9');
                    }
                }
                return new BigInteger(bound.toString());
            }
            
            if(digit == '0'){
                return null;
            }
            
            digit = (char)((int)digit - 1);
            appendDigit = '9';
        }
        else{
            if(commonPart.length() == 0 && digit == '9'){
                bound.append('1');
                for(int i = 0; i < length - 1; ++i){
                    bound.append('0');
                }
                 bound.append('1');
                return new BigInteger(bound.toString());
            }
            
            if(digit == '9'){
                return null;
            }
            digit = (char)((int)digit + 1);
            appendDigit = '0';
        }
        
        bound.append(digit);
        int commonPartSize = commonPart.length();
        int size = length - (commonPartSize + 1) * 2;
        for(int i = 0; i < size; ++i){
            bound.append(appendDigit);
        }
        
        int endIdx = (length % 2 == 1 && commonPartSize == length / 2)? (commonPartSize - 1): commonPartSize;
        for(int i = endIdx; i >= 0; --i){
            char c = bound.charAt(i);
            bound.append(c);
        }
        
        return new BigInteger(bound.toString());
    }
    
    public String nearestPalindromic(String n) {
        BigInteger integerN = new BigInteger(n);
        List<BigInteger> candidates = new ArrayList<BigInteger>();
        int size = (n.length() + 1) / 2;
        
        for(int index = 0; index < size; ++index){
            StringBuilder commonPart = new StringBuilder(n.substring(0, index));
            char digit = n.charAt(index);
            BigInteger lb = getBound(commonPart, digit, n.length(), true);
            BigInteger hb = getBound(commonPart, digit, n.length(), false);

            if(lb != null && hb != null){
                BigInteger lbDiff = lb.subtract(integerN);
                lbDiff = lbDiff.abs();
                BigInteger hbDiff = hb.subtract(integerN);
                hbDiff = hbDiff.abs();
                if(hbDiff.compareTo(lbDiff) >= 0){
                    candidates.add(lb);
                }
                else{
                    candidates.add(hb);
                }
            }
            else if (lb == null){
                candidates.add(hb);
            }
            else{
                candidates.add(lb);
            }
        }
        
        //get the final candidate
        StringBuilder finalCandidate = new StringBuilder(n.substring(0, size));
        int startIndex = (n.length() % 2 == 0)? size - 1: size - 2;
        for(int i = startIndex; i >= 0; --i){
            char c = finalCandidate.charAt(i);
            finalCandidate.append(c);
        }
        if(!finalCandidate.toString().equals(n)){
            candidates.add(new BigInteger(finalCandidate.toString()));
        }
        
        //find the candiate with the minimal difference 
        BigInteger candidate = candidates.get(0);
        BigInteger minDifference = candidate.subtract(integerN).abs();
        for(int i = 1; i < candidates.size(); ++i){
            BigInteger currDifference = candidates.get(i).subtract(integerN).abs();
            if(minDifference.compareTo(currDifference) == 1){
                candidate = candidates.get(i);
                minDifference = currDifference;
            }
            else if(minDifference.compareTo(currDifference) == 0){
                if(candidate.compareTo(candidates.get(i)) == 1){
                    candidate = candidates.get(i);
                }
            }
        }
        
        return candidate.toString();
    }

    public static void main(String[] args){
        Solution sol;
        String n = "123";

        sol = new Solution();
        System.out.println("n: " + n);
        System.out.println("closestPalindrome: " + sol.nearestPalindromic(n));
	}
}
