/* Dynamic programming: Time:O(n^2*2^n), Space:O(n^2*2^n, )where n is the length of string
 */

import java.util.*;

public class Solution{
   public Set<String> longestPalindromeSubseq(String s) {
       int maxLen = 1;
        Map<Integer, Map<Integer, Set<String>>> dp = new HashMap<>();
        for(int tail = 0; tail < s.length(); ++tail){
            char tailC = s.charAt(tail);
            dp.put(tail, new HashMap<>());
            dp.get(tail).put(tail, new HashSet<>());
            dp.get(tail).get(tail).add(Character.toString(tailC));
            for(int head = tail - 1; head >= 0; --head){
                char headC = s.charAt(head);
                dp.get(tail).put(head, new HashSet<>());
                dp.get(tail).get(head).addAll(dp.get(tail - 1).get(head));
                dp.get(tail).get(head).addAll(dp.get(tail).get(head + 1));
                if(tailC == headC){
                    if(tail == head + 1){
                        dp.get(tail).get(head).add(Character.toString(tailC));
                        dp.get(tail).get(head).add(Character.toString(tailC) + Character.toString(tailC));
                        maxLen = Math.max(maxLen, 2);
                    }
                    else{
                        dp.get(tail).get(head).add(Character.toString(tailC));
                        dp.get(tail).get(head).add(Character.toString(tailC) + Character.toString(tailC));
                        for(String p: dp.get(tail - 1).get(head + 1)){
                            dp.get(tail).get(head).add(Character.toString(headC) + p + Character.toString(tailC));
                            maxLen = Math.max(maxLen, p.length() + 2);
                        }
                    }
                }
            }
        }

        Set<String> longestPalindromes = new HashSet<>();
        for(String p: dp.get(s.length() - 1).get(0)){
            if(p.length() == maxLen){
                longestPalindromes.add(p);
            }
        }
        return longestPalindromes;
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "bbbaaa";
        sol = new Solution();

        System.out.println("s: " + s);
        System.out.println("max length of palindromic subsequence: " + sol.longestPalindromeSubseq(s));
    }
}
