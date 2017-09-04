/* Backtrack: O(#abbs)
 * 1. Append the previous abb, and iterate all the possible abbreviations
 * ex: idx = 0 :{ w-ord,              1o-rd,           2r-d,     3d, 4} 
 *                 ||                   ||              ||
 *                 \/                   \/              \/
 *       {wo-rd, w1r-d, w2d, w3}  {1or-d, 1o1d, 1o2}  {2rd, 2r1}
 *               .....                 .....             ....
 */

import java.util.*;

public class Solution{
    private void backtracker(List<String> abbrs, StringBuilder abbr, int idx, String word){
        if(idx >= word.length()){
            abbrs.add(abbr.toString()); 
            return;
        }

        int size = word.length() - idx;        
        for(int i = 0; i <= size; ++i){
            StringBuilder nextAbbr = new StringBuilder(abbr.toString());
            if(i > 0){
                nextAbbr.append(Integer.toString(i));   
            }
            
            if(i < size){
                nextAbbr.append(word.charAt(idx + i)); 
            }
            
            backtracker(abbrs, nextAbbr, idx + i + 1, word);
        }
    }
    
    public List<String> generateAbbreviations(String word) {
        List<String> abbrs = new ArrayList<String>();
        backtracker(abbrs, new StringBuilder(""), 0, word);
        return abbrs;
    }    

    public static void main(String[] args){
        Solution sol;
        String word = "word";
        List<String> abbrs;

        System.out.println("word: " + word);
        
        sol = new Solution();    
        abbrs = sol.generateAbbreviations(word);
        System.out.println("abbreviations: " + abbrs);
    }
}
