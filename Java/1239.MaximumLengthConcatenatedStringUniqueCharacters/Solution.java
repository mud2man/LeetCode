/* Backtrack: Time:O(2^n), Space:O(n) 
 * 1. Backtrack to find all combination as long as no duplicated character
 */

import java.util.*; // Stack

public class Solution {
    private void backtrack(List<String> arr, int start, int[] max, int[] counts){
        int length = 0;
        for(int count: counts){
            length += count;
        }
        
        max[0] = Math.max(max[0], length);
        for(int i = start; i < arr.size(); ++i){
            String word = arr.get(i);
            boolean overlap = false;
            for(char c: word.toCharArray()){
                counts[c - 'a']++;
                overlap =(counts[c - 'a'] > 1)? true: overlap;
            }
            if(!overlap){
                backtrack(arr, i + 1, max, counts);
            }
            for(char c: word.toCharArray()){
                counts[c - 'a']--;
            }
        }
    }
    
    public int maxLength(List<String> arr) {
        int[] max = {0};
        backtrack(arr, 0, max, new int[26]);
        return max[0];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        List<String> arr = new ArrayList(Arrays.asList("un", "iq", "ue"));
        System.out.println("arr:" + arr);
        System.out.println("maximum length:" + sol.maxLength(arr));
    }
}
