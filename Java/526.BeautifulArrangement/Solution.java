/* Backtrack: Time:O(n!), Space:O(n)
 * 1. Find add combinations and accumulate the count of beautiful array
 */

import java.util.*;

public class Solution{
    private void helper(int index, boolean[] used, int remainCount, int[] beautifulCount){
        if(remainCount == 0){
            beautifulCount[0]++;
            return;
        }
        
        for(int i = 1; i < used.length; ++i){
            if(used[i] == false && (i % index == 0 || index % i == 0)){
                used[i] = true;
                helper(index + 1, used, remainCount - 1, beautifulCount);
                used[i] = false;
            }
        }
    }
    
    public int countArrangement(int N) {
        boolean[] used = new boolean[N + 1];;
        int[] beautifulCount = new int[1];
        int remainCount = N;
        helper(1, used, remainCount, beautifulCount);
        return beautifulCount[0];
    }
 
    public static void main(String[] args){
        Solution sol;
        int N = 2;
        
        sol = new Solution();
        System.out.println("N: " + N);
        System.out.println("beautiful arrangement count: " + sol.countArrangement(N));
    }
}
