/* Divide and Conquer: Time:O(n^2 * n^2 * n), Space:O(n * n^2), since we have as most n^2 difference
 * 1. Have a cache to store the count of sequences starting at index "i", with difference "diff"
 * 2. Use dfs to get the count given diff and index
 */

import java.util.*; // Stack

public class Solution {
    private int dfs(int[] A, int start, long diff, Map<Integer, Map<Long, Integer>> cache){
        if(cache.containsKey(start) && cache.get(start).containsKey(diff)){
            return cache.get(start).get(diff); 
        }
        cache.putIfAbsent(start, new HashMap<>());
        int count = 0;
        for(int i = start + 1; i < A.length; ++i){
            if((long)A[i] - (long)A[start] == diff){
                 count = count + 1 + dfs(A, i, diff, cache);  
            }
        }
        cache.get(start).put(diff, count);
        return count;
    }
    
    public int numberOfArithmeticSlices(int[] A) {
        Map<Integer, Map<Long, Integer>> cache = new HashMap<>();
        int count = 0;
        for(int i = 0; i < A.length - 2; ++i){
            for(int j = i + 1; j < A.length - 1; ++j){
                long diff = (long)A[j] - (long)A[i];
                count += dfs(A, j, diff, cache);
            }
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {1, 3, 5, 7, 9}; 
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("slices: " + sol.numberOfArithmeticSlices(A));
    }
}
