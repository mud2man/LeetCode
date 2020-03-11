/* Map:O(n), Space:O(1)
 * Iterate s char by char and keep the number % 2" of vowels 'a', 'e', 'i', 'o', and 'u'
 * Remeber the rightest index of encode {counts[0], counts[1], counts[2], counts[3], counts[4]} in "encodedCounts2Index"
 * The longest valid substring ending at s.charAt(i) is (i - encodedCounts2Index.get(encodedCounts))
 * We keep update max as max(max, i - encodedCounts2Index.get(encodedCounts))
 */

import java.util.*;

public class Solution {
    public int findTheLongestSubstring(String s) {
        Map<String, Integer> encodedCounts2Index = new HashMap<>();
        encodedCounts2Index.put("00000", -1);
        int[] counts = new int[5];
        char[] vowels = {'a', 'e', 'i', 'o', 'u'}; 
        int max = 0;
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            for(int j = 0; j < vowels.length; ++j){
                counts[j] = (vowels[j] == c)? (counts[j] + 1) % 2 : counts[j]; 
            }
            StringBuilder encodedCounts = new StringBuilder("");
            for(int j = 0; j < counts.length; ++j){
                encodedCounts.append(counts[j]);
            }
            if(encodedCounts2Index.containsKey(encodedCounts.toString())){
                max = Math.max(max, i - encodedCounts2Index.get(encodedCounts.toString()));
            }else{
                encodedCounts2Index.put(encodedCounts.toString(), i);
            }
        }
        return max;
    }
  
    public static void main(String[] args){
        String s = "eleetminicoworoep";
        Solution sol = new Solution();
        System.out.println("s:" + s);
        System.out.println("the size of the valid longest substring:" + sol.findTheLongestSubstring(s));
    }
}
