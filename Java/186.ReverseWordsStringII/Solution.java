/* Reverse twice: O(n),
 * 1. Reverse the whole string
 * 2. Reverse every word in the string
 */

import java.util.*;

public class Solution{
    public void reverseWord(char[] s, int headIdx, int tailIdx) {
        char c;
        
        while(headIdx < tailIdx){
            c = s[headIdx];
            s[headIdx] = s[tailIdx];
            s[tailIdx] = c;
            tailIdx--;
            headIdx++;
        }
    }
    
    public void reverseWords(char[] s) {
        int tailIdx;
        int headIdx;
        char c;
        int tmpIdx;
        
        headIdx = 0;
        tailIdx = s.length - 1;
        
        if(s.length == 0){
            return;
        }
        
        reverseWord(s, headIdx, tailIdx);
        
        headIdx = 0;
        tailIdx = 0;
        while(headIdx < s.length){
            while((tailIdx < s.length) && (s[tailIdx] != ' ')){
                tailIdx++;
            }
            tmpIdx = tailIdx;
            --tailIdx;
            reverseWord(s, headIdx, tailIdx);
            tailIdx = tmpIdx + 1;
            headIdx = tailIdx;
        }
    }
    
    public static void main(String[] args){
		Solution sol;
        String s;
		char[] cArray;

       	s = "the sky is blue";
		sol = new Solution();

        System.out.println("before rotate: " + s);

		cArray = s.toCharArray();
		sol.reverseWords(cArray);
		s = String.valueOf(cArray);

        System.out.println("after rotate: " + s);
	}
}
