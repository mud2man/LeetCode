/* Backtarck: Time:O(n!), Space:O(n)
 * 1. Use back backtrack to fit in the largest number to maxSequence[idx]
 * 2. Since we always search from n, the first possible sequence is the max
 */         

import java.util.*;

public class Solution {
    private boolean backtrack(int[] maxSequence, boolean[] used, int idx){
        if(idx == maxSequence.length){
            return true;
        }else if(maxSequence[idx] != 0){
            return backtrack(maxSequence, used, idx + 1);
        }else{
            for(int num = used.length - 1; num > 1; num--){
                if(!used[num] && idx + num < maxSequence.length && maxSequence[idx + num] == 0){
                    used[num] = true;
                    maxSequence[idx] = num;
                    maxSequence[idx + num] = num;
                    if(backtrack(maxSequence, used, idx + 1)){
                        return true;
                    }
                    used[num] = false;
                    maxSequence[idx] = 0;
                    maxSequence[idx + num] = 0;
                }
            }
            if(!used[1]){
                used[1] = true;
                maxSequence[idx] = 1;
                if(backtrack(maxSequence, used, idx + 1)){
                    return true;
                }
                used[1] = false;
                maxSequence[idx] = 0;
            }
            return false;
        }
    }
    
    public int[] constructDistancedSequence(int n) {
        int[] maxSequence = new int[2 * n - 1];
        boolean[] used = new boolean[n + 1];
        backtrack(maxSequence, used, 0);
        return maxSequence;
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        int n = 3;
        System.out.println("n:" + n);
        System.out.println("min sequence: " + Arrays.toString(sol.constructDistancedSequence(n)));
    }
}
