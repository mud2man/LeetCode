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
        
        int h = size;
        int count = 0;
        while(h >= 0){
            count += buckets[h];
            if(count >= h){
                break;
            }
            --h;
        }
        return h;
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
