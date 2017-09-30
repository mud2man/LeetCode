/* Sliding window: O(n)
 * 1. Have a HashMap HashMap<Character, int[] pair> charCountMap to record the character occurance number
 * 2. pair[0] = the occurance of character in s1, pair[1] = the occurance of character in s2
 * 3. If pair[0] == pair[1], which means match, reduce the number of raminaing matchs remainMatch
 * 4. Otherwise, increase remainMatch when pair[0] + 1) == pair[1] when add directioni, or when pair[0] -1 1) == pair[1] when add direction
 */

import java.util.*;


public class Solution{
    public boolean checkInclusion(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        if(s2Length < s1Length){
            return false;
        }
        
        int[] pair;
        HashMap<Character, int[]> charCountMap = new HashMap<Character, int[]>();
        HashSet<Character> s1CharSet = new HashSet<Character>();
        for(int i = 0; i < s1Length; ++i){
            char c = s1.charAt(i);
            s1CharSet.add(c);
            if(!charCountMap.containsKey(c)){
                pair = new int[2];
                charCountMap.put(c, pair);
            }
            pair = charCountMap.get(c);
            pair[0]++;
        }
        
        int remainMatch = s1CharSet.size();
            
        for(int i = 0; i < s1Length; ++i){
            char c = s2.charAt(i);
            if(s1CharSet.contains(c)){
                pair = charCountMap.get(c);
                pair[1]++;
                if(pair[0] == pair[1]){
                    remainMatch--;
                }
                else if((pair[0] + 1) == pair[1]){
                    remainMatch++;
                }
            }
        }
        
        //sliding window
        int index = s1Length;
        while(remainMatch > 0 && index < s2Length){
            char deleteChar = s2.charAt(index - s1Length);
            if(s1CharSet.contains(deleteChar)){
                pair = charCountMap.get(deleteChar);
                pair[1]--;
                if(pair[0] == pair[1]){
                    remainMatch--;
                }
                else if((pair[0] - 1) == pair[1]){
                    remainMatch++;
                }
            }
            
            char addChar = s2.charAt(index);
            if(s1CharSet.contains(addChar)){
                pair = charCountMap.get(addChar);
                pair[1]++;
                if(pair[0] == pair[1]){
                    remainMatch--;
                }
                else if((pair[0] + 1) == pair[1]){
                    remainMatch++;
                }
            }
            
            index++;
        }
        
        return (remainMatch == 0);
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s1 = "ab";
        String s2 = "eidbaooo";
        
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("match exist: " + sol.checkInclusion(s1, s2));
    }
}
