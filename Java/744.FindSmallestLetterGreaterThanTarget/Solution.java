/* Binary search Time:O(logn), Space:O(1). 
 * 1. Set the candidate as the first char, and use binary search to find the solution between first and last
 * 2. If c > target, we select the left range, since all number in right range has bigger distance
 * 3. Otherwise, select left range
 */          

import java.util.*; // Stack

public class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int lb = 0;
        int hb = letters.length - 1;
        char candidate = letters[0];
        
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            char c = letters[mid];
            int midDiff = (candidate > target)? candidate - target: candidate - target + 26;
            int diff = (c > target)? c - target: c - target + 26;
            candidate = (midDiff > diff)? c: candidate;
            
            if(c > target){
                hb = mid - 1;
            }
            else{
                lb = mid + 1;
            }
        }
        return candidate;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        System.out.println("letters: " + Arrays.toString(letters));
        System.out.println("target: " + target);
        System.out.println("next greatest letter: " + sol.nextGreatestLetter(letters, target));
    }
}
