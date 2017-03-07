/* .
 * 1. Devide the case into 26 characters
 * 2. Keep a posList to record the modified positions
 * 3. If str.charAt(idx) == target, keep scanning to right
 * 4. Else, take the head from the posList, and append to the tail
 * 
 * ex: str = "ABCABB", k = 2, assume target = B
 * time[0]: idx = 0, head = -1, posList=[0], str = "BBCABB", currLen = 1 
 * time[1]: idx = 1, head = -1, posList=[0], str = "BBCABB", currLen = 2
 * time[2]: idx = 2, head = -1, posList=[0, 2], str = "BBBABB", currLen = 3
 * time[3]: idx = 3, head = 0, posList=[2,3], str = "ABBBBB", currLen = 3
 * time[4]: idx = 4, head = 0, posList=[2,3], str = "ABBBBB", currLen = 4
 * time[5]: idx = 5, head = 0, posList=[2,3], str = "ABBBBB", currLen = 5
 */

import java.util.*; // Stack

public class Solution {
    public int getMaxRepeat(String s, char target, int k){
        LinkedList<Integer> posList;
        int idx, currLen, maxLen, head;
        
        posList = new LinkedList<Integer>();
        maxLen = 0;
        currLen = 0;
        head = -1;
        idx = 0;
        
        for(idx= 0 ; idx < s.length(); idx++){
            if(s.charAt(idx) != target){
                if(posList.size() < k){
                    posList.add(idx);
                }
                else{
                    if(!posList.isEmpty()){
                        head = posList.poll();
                        posList.add(idx);
                    }
                    else{
                        head = idx;
                    }
                }
            }
            currLen = idx - head;
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }
    
    public int characterReplacement(String s, int k) {
        int maxLen, currLen;
        char c;
        
        maxLen = 0;
        for(c = 'A'; c <= 'Z'; c++){
            currLen = getMaxRepeat(s, c, k);
            if(maxLen < currLen){
                maxLen = currLen;
            }
        }
        return maxLen;
    }
 
    public static void main(String[] args){
        Solution sol;
        String str = "AABABBA";
        int k, maxLen;

        sol = new Solution();
        k = 1;

        System.out.println("str: " + str);
        maxLen = sol.characterReplacement(str, k);
        System.out.println("maxLen: " + maxLen);
	}
}
