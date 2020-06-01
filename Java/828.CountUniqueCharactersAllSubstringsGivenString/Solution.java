/* Dynamic programming: Time:O(n), Space:O(1).
 * 1. dp[i] is the count of unique characters of substrings ending at s.charAt(i)
 * 2. dp[i] = dp[i - 1] - ((latestIndexes[c - 'A'] + 1) * 2)(removing duplicated case) + (secondLatestIndexes[c - 'A'] + 1)(recover from over cut)
 */     


import java.util.*; // Stack

public class Solution {
    public int uniqueLetterString(String s) {
        int base = 1_000_000_007;
        int[] dp = new int[2];
        int[] latestIndexes = new int[26];
        int[] secondLatestIndexes = new int[26];
        Arrays.fill(latestIndexes, -1);
        Arrays.fill(secondLatestIndexes, -1);
        int count = 0;
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            int leftVal =(i > 0)? dp[(i - 1) % 2]: 0;
            dp[i % 2] = leftVal + (i + 1) - (latestIndexes[c - 'A'] + 1) * 2 + (secondLatestIndexes[c - 'A'] + 1);
            count = (count + dp[i % 2]) % base;
            secondLatestIndexes[c - 'A'] = latestIndexes[c - 'A'];
            latestIndexes[c - 'A'] = i;
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "ABA";
        System.out.println("s:" + s);
        System.out.println("count of character of all substring:" + sol.uniqueLetterString(s));
    }
}
