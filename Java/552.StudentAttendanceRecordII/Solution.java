/* Dynamic Programming: Time = O(n), Space = O(1)
 * 1. dp[y][x] = the count of attendance with y "Absent", x continuous tail "Late" at any given i-th day
 * 2. We can compute the next status "nexCount" by the current status "count"
 * 3. Get the answer from sumed from count
 */

import java.util.*;

public class Solution{
    public int checkRecord(int n) {
        int base = 1_000_000_007;
        int[][] count = new int[2][3];
        count[0][0] = 1; //A#=0, L#=0
        count[0][1] = 1; //A#=0, L#=1
        count[1][0] = 1; //A#=1, L#=0
        int[][] nexCount = new int[2][3];
        for(int i = 1; i < n; ++i){
            for(int y = 0; y < count.length; ++y){
                Arrays.fill(nexCount[y], 0);
            }
            nexCount[0][0] = (count[0][0] + count[0][1]) % base;
            nexCount[0][0] = (nexCount[0][0] + count[0][2]) % base;
            nexCount[0][1] = count[0][0];
            nexCount[0][2] = count[0][1];
            nexCount[1][0] = (count[0][0] + count[0][1]) % base;
            nexCount[1][0] = (nexCount[1][0] + count[0][2]) % base;
            nexCount[1][0] = (nexCount[1][0] + count[1][0]) % base;
            nexCount[1][0] = (nexCount[1][0] + count[1][1]) % base;
            nexCount[1][0] = (nexCount[1][0] + count[1][2]) % base;
            nexCount[1][1] = count[1][0];
            nexCount[1][2] = count[1][1];
            for(int y = 0; y < count.length; ++y){
                System.arraycopy(nexCount[y], 0, count[y], 0, count[y].length);
            }
        }
        
        int totalCount = 0;
        for(int y = 0; y < count.length; ++y){
            for(int x = 0; x < count[0].length; ++x){
                totalCount = (totalCount + count[y][x]) % base;
            }
        }
        return totalCount;
    }
   
    public static void main(String[] args){
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        Solution sol = new Solution();
        System.out.println("k: " + k);
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("topK: " + sol.topKFrequent(words, k));
	}
}
