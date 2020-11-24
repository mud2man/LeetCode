/* Dynamic Programming: Time:O(m*n), Space:O(m*n)
 * 1. zeroDiffDp[y][x] = number of substring pairs without difference ending at s.charAt(y) and t.charAt(x)
 * 2. oneDiffDp[y][x] = number of substring pairs with one different letter ending at s.charAt(y) and t.charAt(x)
 * 3. Accumulate count while update oneDiffDp[y][x]
 */

import java.util.*; // Stack


public class Solution {
    public int countSubstrings(String s, String t) {
        int[][] zeroDiffDp = new int[s.length()][t.length()];
        for(int y = 0; y < s.length(); y++){
            for(int x = 0; x < t.length(); x++){
                if(s.charAt(y) == t.charAt(x)){
                    zeroDiffDp[y][x] =(y > 0 && x > 0)? zeroDiffDp[y - 1][x - 1] + 1: 1;
                }
            }
        }
        int count = 0;
        int[][] oneDiffDp = new int[s.length()][t.length()];
        for(int y = 0; y < s.length(); y++){
            for(int x = 0; x < t.length(); x++){
                if(s.charAt(y) == t.charAt(x)){
                    oneDiffDp[y][x] =(y > 0 && x > 0)? oneDiffDp[y - 1][x - 1]: 0;
                }else{
                    oneDiffDp[y][x] =(y > 0 && x > 0)? zeroDiffDp[y - 1][x - 1] + 1: 1;
                }
                count += oneDiffDp[y][x];
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "abe"; 
        String t = "bbc"; 
        System.out.println("s:" + s + ", t:" + t);
        System.out.println("substrings count:" + sol.countSubstrings(s, t));
    }
}
