/* Hash Table: O(n)
 * 1. Scan both strings from the leftest position
 * 2. Increase bullCount if secret.charAt(i) == guess.charAt(i);
 * 3. Record statics for both string secretStatics[i] = the occurence number of digit i, where 0 <= i <= 9
 * 4. totalCount = sum of min(secretStatics[i], guessStatics[i]), where 0 <= i <= 9
 * 5. cowCount = totalCount - bullCount
 */

import java.util.*;

public class Solution{
    public String getHint(String secret, String guess) {
        int[] secretStatics = new int[10];
        int[] guessStatics = new int[10];
        int bullCount = 0;
        int cowCount = 0;
        int totalCount = 0;
        
        for(int i = 0; i < secret.length(); ++i){
            char secretChar = secret.charAt(i);
            char guessChar = guess.charAt(i);
            
            if(secretChar == guessChar){
                bullCount++;
            }
            secretStatics[secretChar - '0']++;
            guessStatics[guessChar - '0']++;
        }
        
        for(int i = 0; i < 10; ++i){
            totalCount = totalCount + Math.min(secretStatics[i], guessStatics[i]);
        }
        
        cowCount = totalCount - bullCount;
        
        return Integer.toString(bullCount) + "A" + Integer.toString(cowCount) + "B";
    }

    public static void main(String[] args){
        Solution sol;
        String secret = "1123";
        String guess = "0111";

        sol = new Solution();

        System.out.println("secret: " + secret);
        System.out.println("guess: " + guess);
        System.out.println("Bulls and Cows: " + sol.getHint(secret, guess));
    }
}
