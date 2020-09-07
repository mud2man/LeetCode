/* Math: Time:O(n), Space:O(1)
 * 1. If there is no one in s, the answer is c(n, 2)
 * 2. Otherwise, we get the count between the last one of 1st interval and the first one of 2nd interval
 * 3. And the count between the last one of 2nd interval and the first one of 3th interval. The answer is (zeroCount[0] + 1) * (zeroCount[1] + 1)
 */     

import java.util.*; // Stack

public class Solution {
    public int numWays(String s) {
        int oneCount = 0;
        for(int i = 0; i < s.length(); ++i){
            oneCount +=(s.charAt(i) == '1')? 1: 0;
        }
        if(oneCount % 3 != 0){
            return  0;
        }
        if(oneCount == 0){
            return (int)((((long)s.length() - 1) * ((long)s.length() - 2) / 2) % 1_000_000_007);
        }
        
        int oneCountOneThird = oneCount / 3;
        int idx = 0;
        long[] zeroCount = new long[2];
        for(int i = 0; i < 2; ++i){
            oneCount = 0;
            while(oneCount < oneCountOneThird){
                oneCount +=(s.charAt(idx++) == '1')? 1: 0;
            }
            while(s.charAt(idx) == '0'){
                zeroCount[i]++;
                idx++;
            }
        }
        return (int)(((zeroCount[0] + 1) * (zeroCount[1] + 1)) % 1_000_000_007);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "10101";
        System.out.println("s:" + s);
        System.out.println("number of ways to split:" + sol.numWays(s));
    }
}
