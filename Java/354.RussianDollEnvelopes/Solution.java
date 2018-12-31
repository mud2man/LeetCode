/* Dynamic Progamming: Time:O(nlogn), Space:O(n)
 * 1. Sort envelopes with envelope[0](width) with higher priority, ex: {5, 4}, {6, 4}, {6, 7}, {2, 3} => {2, 3}, {5, 4}, {6, 4}, {6, 7}
 * 2. Have a array dp, where dp[i] = the smallest height of doll sequence with size = (i + 1)
 * 3. In the loop, first, get the "heights" with the same width
 * 4. Traverse the hights from right, and use binary search to update dp
 * 5. The size of "dp" is the answer
 *
 * ex: envelopes = {2, 3}, {2, 4}, {2, 7}, {5, 5}, {5, 7}, {6, 7}, {6 ,8}
 *     loop[0]: dp = {1}
 *     loop[1]: dp = {1, 5}
 *     loop[2]: dp = {1, 5, 7}
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    private class DollComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            if(x[0] != y[0]){
                return (x[0] < y[0])? -1: 1;
            }
            else{
                return (x[1] < y[1])? -1: ((x[1] > y[1])? 1: 0);
            }
        }
    }
    
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new DollComparator());
        int[] dp = new int[envelopes.length];
        int size = 0;
        for(int i = 0; i < envelopes.length; ++i){
            int width = envelopes[i][0];
            int start = i;
            while(i < envelopes.length && width == envelopes[i][0]){
                i++;
            }
            i--;
            
            for(int j = i; j >= start; --j){
                int height = envelopes[j][1];
                int insetrtIdx = Arrays.binarySearch(dp, 0, size, height);
                insetrtIdx = (insetrtIdx < 0)? -(insetrtIdx + 1): insetrtIdx;
                if(insetrtIdx == size){
                    dp[size++] = height;
                }
                else{
                    dp[insetrtIdx] = height;
                }
            }
        }
        return size;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] envelopes = {{5, 4},{6, 4},{6, 7},{2, 3}};
        System.out.print("envelopes: ");
        for(int[] envelope: envelopes){
            System.out.print(Arrays.toString(envelope) + " ");
        }
        System.out.println("");
        System.out.println("max number of dolls: " + sol.maxEnvelopes(envelopes));
    }
}
