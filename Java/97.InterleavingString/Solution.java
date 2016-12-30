/* Dynamic programming: O(n*m)
 * 1. If dp[y][x-1]==true && s1[x-1]=s3[x+y-1] => dp[y][x]=true
 * 2. If dp[y-1][x]==true && s2[y-1]=s3[x+y-1] => dp[y][x]=true
 * 3. Take example: s1=aab, s2=db, s3=aadbb 
 *
 *     X a a b(s1)
 *    --------
 *  X| 1 1 1 0
 *  d| 0 0 1 1
 *  b| 0 0 1 1 
 * (s2) 
 */

import java.util.*;

public class Solution{
    public boolean isInterleave(String s1, String s2, String s3) {
        boolean[][] dp;
        int s1Len;
        int s2Len;
        int s3Len;
        int x;
        int y;
        
        s1Len = s1.length();
        s2Len = s2.length();
        s3Len = s3.length();
        
        if(s3Len != (s1Len + s2Len)){
            return false;
        }
        
        dp = new boolean[s2Len + 1][s1Len + 1];
        
        dp[0][0] = true;
        
        for(y = 0; y <= s2Len; ++y){
            for(x = 0; x <= s1Len; ++x){
                if((y == 0) && (x == 0)){
                    dp[y][x] = true;
                }
                else if((y == 0) && (x != 0)){
                    if((dp[y][x - 1] == true) && (s1.charAt(x - 1) == s3.charAt(x - 1))){
                        dp[y][x] = true;
                    }
                }
                else if((y != 0) && (x == 0)){
                    if((dp[y - 1][x] == true) && (s2.charAt(y - 1) == s3.charAt(y - 1))){
                        dp[y][x] = true;
                    }
                }
                else{
                    if((dp[y][x - 1] == true) && (s1.charAt(x - 1) == s3.charAt(x + y - 1))){
                        dp[y][x] = true;
                    }
                    
                    if((dp[y - 1][x] == true) && (s2.charAt(y - 1) == s3.charAt(x + y - 1))){
                        dp[y][x] = true;
                    }
                }
            }
        }
        return dp[s2Len][s1Len];
    }
 
    public static void main(String[] args){
		Solution sol;
		String s1;
		String s2;
		String s3;
		
		s1 = "aabcc";
 		s2 = "dbbca";
 		s3 = "aadbbcbcac"; 
		sol = new Solution();
		
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("s3: " + s3);
		
		System.out.println("isInterLeaving: " + sol.isInterleave(s1, s2, s3));
	}
}
