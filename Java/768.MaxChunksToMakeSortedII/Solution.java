/* Time:O(n)), Space:O(n).
 * 1. Have array "mostFromLeft", where mostFromLeft[i] store the max between arr[0] and arr[i]
 * 2. Have array "leastFromRight", where leastFromRight[i] store the min between arr[i] and arr[arr.length - 1] 
 * 3. If leastFromRight[j] >= mostFromLeft[j - 1], arr[0] ~ arr[j - 1] and arr[j]-arr[arr.length - 1] can be separated
 * 4. Count the number of cases of step3
 */         

import java.util.*;

public class Solution {
    public int maxChunksToSorted(int[] arr) {
        int[] mostFromLeft = new int[arr.length];
        int[] leastFromRight = new int[arr.length];
        
        int max = 0;
        for(int i = 0; i < arr.length; ++i){
            max = Math.max(max, arr[i]);
            mostFromLeft[i] = max;
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = arr.length - 1; i >= 0; --i){
            min = Math.min(min, arr[i]);
            leastFromRight[i] = min;
        }
        
        int chunkCount = 1;
        for(int i = arr.length - 1; i > 0; --i){
            if(leastFromRight[i] >= mostFromLeft[i - 1]){
                chunkCount++;
            }
        }
        return chunkCount;
    }
 
    public static void main(String[] args){
        Solution sol= new Solution();
        int[] arr = {2, 1, 3, 4, 4}; 
        System.out.println("A: " + Arrays.toString(arr));
        System.out.println("maximum chunk numbers : " + sol.maxChunksToSorted(arr));
    }
}
