/* Two pointers: Time:O(26*n), Space:O(n)
 * 1. We tarverse based on uniqueCount, where 1 <= uniqueCount <= 26
 * 2. In the loop, we shift "tail" right each time, and shift "front" right whenever uniqueCount > i 
 * 3. Therefore, we know the timing moveing "front" right because of the existence of "uniqueCount"
 * 4. Update "maxLength" when "badChars" is empty
 */

import java.util.*;

public class Solution{
    public int longestSubstring(String s, int k) {
        char[] chars = s.toCharArray();
        int maxLength = 0;
        for(int i = 1; i <= 26; ++i){
            int[] count = new int[26];
            int uniqueCount = 0;
            Set<Character> badChars = new HashSet<>();
            for(int tail = 0, front = 0; tail < chars.length; ++tail){
                char tailChar = chars[tail];
                count[tailChar - 'a']++;
                uniqueCount += (count[tailChar - 'a'] == 1)? 1: 0;
                if(count[tailChar - 'a'] < k){
                    badChars.add(tailChar);
                }
                else{
                    badChars.remove(tailChar);
                }
                
                while(uniqueCount > i && front <= tail){
                    char frontChar = chars[front++];
                    count[frontChar - 'a']--;
                    uniqueCount -= (count[frontChar - 'a'] == 0)? 1: 0;
                    if(count[frontChar - 'a'] < k && count[frontChar - 'a'] > 0){
                        badChars.add(frontChar);
                    }
                    else if(count[frontChar - 'a'] == 0){
                        badChars.remove(frontChar);
                    }
                }
                
                int length = tail - front + 1;
                if(uniqueCount == i && badChars.isEmpty()){
                    maxLength = Math.max(maxLength, length);
                }
            }
        }
        return maxLength;
    }
  
    public static void main(String[] args){
        String s = "aaabb";
        int k  = 3;
        Solution sol = new Solution();

        System.out.println("s" + s);
        System.out.println("k:" + k);
        System.out.println("longest length of substring: " + sol.longestSubstring(s, k));
    }
}
