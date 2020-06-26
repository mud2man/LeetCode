/* Dynamic Programming: Time:O(n), Space:O(n). We can reduce space complexity by reuse "next"
 * 1. We store the pant# of all past three combinations: rrr, rry, rrg, ... to "curr" 
 * 2. curr[i][j][k] = pant# of color combination (i, j, k), where r:0, g:1, y:2
 * 3. We caculate the next pant# given "curr", while keeping the neighbor having different color
 * 4. Finally, we sum all combinations
 */     

import java.util.*; // Stack

public class Solution {
    private static int BASE = 1_000_000_007;
    private int[][][] getNext(int[][][] curr, boolean isFirstColumn){
        int[][][] next = new int[3][3][3];
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                for(int k = 0; k < 3; ++k){
                    for(int l = 0; l < 3; ++l){
                        if(isFirstColumn){
                            next[i][j][k] =(i != l)? (next[i][j][k] + curr[j][k][l]) % BASE: next[i][j][k];
                        }else{
                            next[i][j][k] =(i != l && i != j)? (next[i][j][k] + curr[j][k][l]) % BASE: next[i][j][k];
                        }
                    }
                }
            }
        }
        return next;
    }
    
    public int numOfWays(int n) {
        //r:0, g:1, y:2
        int[][][] curr = {{{0, 0, 0}, {1, 0, 1}, {1, 1, 0}},  //rxx
                          {{0, 1, 1}, {0, 0, 0}, {1, 1, 0}},  //yxx
                          {{0, 1, 1}, {1, 0, 1}, {0, 0, 0}}}; //gxx
        
        for(int y = 1; y < n; ++y){
            curr = getNext(curr, true);
            curr = getNext(curr, false);
            curr = getNext(curr, false);
        }
        
        int sum = 0;
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                for(int k = 0; k < 3; ++k){
                    sum = (sum + curr[i][j][k]) % BASE;
                }
            }
        }
        return sum;
    }  

    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 2;
        System.out.println("n:" + n);
        System.out.println("ways#:" + sol.numOfWays(n));
    }
}
