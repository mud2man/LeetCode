/* Greedy:Time:O(n), Space:O(1)
 * 1. Get the count of character from s
 * 2. Retrieve singleCount and pairCount from "counts". e.g., singleCount=1, pairCount=4, given s="annabelle"
 * 3. Subtrate k from singleCount, and return false if (k - singleCount) < 0. 
 * 4. Otherwise, check pairCount * 2 >= k, since pairCount pairs can form 0 ~ (2 * pairCount) palindromes
 */          

import java.util.*; 
public class Solution {
    public boolean canConstruct(String s, int k) {
        int[] counts = new int[26];
        for(char c: s.toCharArray()){
            counts[c - 'a']++;
        }
        
        int singleCount = 0;
        int pairCount = 0;
        for(int count: counts){
            singleCount +=(count % 2 == 0)? 0: 1;
            pairCount += (count / 2);
        }
        
        k -= singleCount;
        if(k < 0){
            return false;
        }
        return (k < 0)? false: (pairCount * 2 >= k);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "annabelle";
        int k = 2;
        System.out.println("s:" + s);
        System.out.println("k:" + k);
        System.out.println("canConstruct:" + sol.canConstruct(s, k));
    }
}
