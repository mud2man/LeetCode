/* Dynamic Programming: Time:O(2^n), Space:O(2^n * n^2).
 * 1. dp.get(tail).get(head).get(i) = the different palindromic subsequences in S.substring(head, tail + 1), with two endpoints = 'a' + i
 * 2. Traverse on tail (0, n - 1), head = (0, tail), i = (0, 3)
 * 3. There are three cases: two endpoints = 'a' + i, S.charAt(head) != ('a' + i), and others
 * 4. In case0: differentiate with head and tail
 * 5. In case1: dp.get(tail).get(head).get(i) = (head < tail)? dp.get(tail)(head + 1).get(i): empty list
 * 6. In case2: dp.get(tail).get(head).get(i) = (head < tail)? dp.get(tail - 1)(head).get(i): empty list
 */

import java.util.*;

public class Solution{
       public List<String> palindromicSubsequences(String S) {
        int n = S.length();
        Map<Integer, Map<Integer, Map<Integer, List<String>>>> dp = new HashMap<>();
        for(int tail = 0; tail < n; ++tail){
            for(int head = tail; head >= 0; --head){
                for(int i = 0; i < 4; ++i){
                    dp.putIfAbsent(tail, new HashMap<>());
                    dp.get(tail).putIfAbsent(head, new HashMap<>());
                    dp.get(tail).get(head).putIfAbsent(i, new ArrayList<>());
                    List<String> palindromes = dp.get(tail).get(head).get(i);
                    if(S.charAt(head) == 'a' + i && S.charAt(tail) == 'a' + i){
                        char c = S.charAt(head); 
                        if(head == tail){
                            palindromes.add(String.valueOf(c));
                        }
                        else if(head + 1 <= tail){
                            StringBuilder p = new StringBuilder("");
                            p.append(c);
                            palindromes.add(p.toString());
                            p.append(c);
                            palindromes.add(p.toString());
                            if(head + 1 < tail){
                                for(int j = 0; j < 4; ++j){
                                    List<String> subPs = dp.get(tail - 1).get(head + 1).get(j);
                                    for(String subP: subPs){
                                        p = new StringBuilder(subP);
                                        p.append(c);
                                        p.insert(0, c);
                                        palindromes.add(p.toString());
                                    }
                                }
                            }
                        }
                    }
                    else if(S.charAt(head) != 'a' + i && head < tail){
                        palindromes.addAll(dp.get(tail).get(head + 1).get(i));
                    }
                    else if(S.charAt(tail) != 'a' + i && head < tail){
                        palindromes.addAll(dp.get(tail - 1).get(head).get(i));
                    }
                }
            }
        }
        
        List<String> palindromes = new ArrayList<>();
        int sum = 0;
        for(int i = 0; i < 4; ++i){
            palindromes.addAll(dp.get(n - 1).get(0).get(i));
        }
        return palindromes;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "bccb";
        System.out.println("S: " + S);
        System.out.println("palindromes:" + sol.palindromicSubsequences(S));
    }
}
