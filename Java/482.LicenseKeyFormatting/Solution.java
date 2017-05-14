/* O(n)
 * 1. Traverse from the end of the string, and do some corner case handling. Not thing special
 */          

import java.util.*; // Stack

public class Solution {
    public String licenseKeyFormatting(String S, int K) {
        int idx, count;
        StringBuilder key;
        char c;
        
        idx = S.length() - 1;
        count = 0;
        key = new StringBuilder();
        
        while(idx >= 0){
            c = S.charAt(idx--);
            if(Character.isLetter(c) || Character.isDigit(c)){
                count++;
                
                if(Character.isLetter(c)){
                    key.insert(0, Character.toUpperCase(c));
                }
                else{
                    key.insert(0, c);
                }
                
                if(count % K == 0 && idx >= 0){
                    key.insert(0, '-');
                }
            }
        }
        
        if(key.length() > 0 && key.charAt(0) == '-'){
            key.deleteCharAt(0);
        }
        
        return key.toString();
    }
    
    public static void main(String[] args){
        Solution sol;
        String S = "2-4A0r7-4k";
        int K = 3;

        sol = new Solution();
        
        System.out.println("S: " + S);
        System.out.println("K: " + K);
        System.out.println("key: " + sol.licenseKeyFormatting(S, K));
    }
}
