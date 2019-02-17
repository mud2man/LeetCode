/* Dynamic Programming: Time:O(N), Space:O(1)
 * 1. Have nextJumps to get the next positions nextJumps[i], when knight is on position i
 * 2. currCount[i] = the number of dial sequence ending at position "i"
 * 2. Use nextJumps and currCount to accumulate the nextCount by nextCount[next] = (nextCount[next] + currCount[j]) % base
 */

import java.util.*;

public class Solution{
    public int knightDialer(int N) { 
        int base = 1_000_000_007;
        int[][] nextJumps = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {-1}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        int[] currCount = new int[10];
        int[] nextCount = new int[10];
        Arrays.fill(currCount, 1);
        for(int i = 0; i < N - 1; ++i){
            for(int j = 0; j < 10; ++j){
                if(j == 5){
                    continue;
                }
                int[] nexts = nextJumps[j];
                for(int next: nexts){
                    nextCount[next] = (nextCount[next] + currCount[j]) % base;
                }
            }
            System.arraycopy(nextCount, 0, currCount, 0, currCount.length);
            Arrays.fill(nextCount, 0);
        }
        
        int count = 0;
        for(int i = 0; i < currCount.length; ++i){
            count = (count + currCount[i]) % base;
        }
        return (N > 0)? count: 0;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 2;
        System.out.println("N: " + N);
        System.out.println("steps: " + sol.knightDialer(N));
    }
}
