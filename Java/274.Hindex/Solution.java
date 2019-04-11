/* Bucket sort: O(n)
 * 1. buckets[i] = number of paper with citation count = i, buckets[size] = number of paper with citation count >= size
 * 2. Check h from h = size until count >= h 
 */

import java.util.*; // Stack

public class Solution{
    public int hIndex(int[] citations) {
        int size = citations.length;
        int[] buckets = new int[size + 1];
        
        for(int citation: citations){
            if(citation >= size){
                buckets[size]++;
            }
            else{
                buckets[citation]++;
            }
        }
        
        for(int h = size, count = 0; h >= 0; --h){
            count += buckets[h];
            if(count >= h){
                return h;
            }
        }
        return 0;
    }
 
    public static void main(String[] args){
        
        Solution sol;
        int hIndex;
        int[] citations = new int[] {1, 1};
        
        sol = new Solution();
        hIndex = sol.hIndex(citations);
        
        System.out.println("hIndex: " +  hIndex);
    }
}
