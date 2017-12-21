/* Greedy + Two Pointers: Time:O(nk), Space:O(n), where n = size of dic, k = length of s
 * 1. Have a 2-D array indexs to store the current index of word in d
 * 2. If index == word.length(), update longestWord 
 */

import java.util.*;

public class Solution{
    public String findLongestWord(String s, List<String> d) {
        int[][] indexs = new int[d.size()][1];
        
        String longestWord = "";
        
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            for(int j = 0; j < indexs.length; ++j){
                String word = d.get(j);
                int index = indexs[j][0];
                if(index < word.length() && c == word.charAt(index)){
                    indexs[j][0]++;
                    if(indexs[j][0] == word.length()){
                        if(word.length() > longestWord.length()){
                            longestWord = word;
                        }
                        else if(word.length() == longestWord.length() && longestWord.compareTo(word) > 0){
                            longestWord = word;
                        }
                    }
                }
            }
        }
        
        return longestWord;
    }  

    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "apple";
        List<String> d = new ArrayList<String>();
        d.add("ale");
        d.add("apple");
        d.add("monkey");
        d.add("plea");
        
        System.out.println("s: " + s);
        System.out.println("d: " + d);
        System.out.println("longestWord:" + sol.findLongestWord(s, d));
    }
}
