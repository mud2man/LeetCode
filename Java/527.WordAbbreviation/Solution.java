/* Hash: Time:O(n*m), Space:O(n*m). LeetCode has a simpler solution
 * 1. 
 */

import java.util.*;

public class Solution{
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, String> word2Abbr = new HashMap<String, String>();
        Map<String, Integer> abbr2Count = new HashMap<String, Integer>();
        Map<String, String> abbr2Word = new HashMap<String, String>();
        String s = "";
        String ss = "";
    
        for(String word: dict){
            StringBuilder abbr = new StringBuilder(String.valueOf(word.charAt(0)));
            char lastC = word.charAt(word.length() - 1);
            int len = word.length() - 2;
            s = abbr.toString() + Integer.toString(len) + String.valueOf(lastC);
            for(int i = 1; i < word.length(); ++i){
                s = abbr.toString() + Integer.toString(len) + String.valueOf(lastC);
                if(abbr2Count.containsKey(s) && abbr2Count.get(s) > 1){
                    abbr2Count.put(s, abbr2Count.get(s) + 1);
                }
                else{
                    break;
                }
                abbr.append(word.charAt(i));
                len--;
            }
            
            if(!abbr2Count.containsKey(s)){
                abbr2Count.put(s, 1);
                abbr2Word.put(s, word);
                if(s.length() < word.length()){
                    word2Abbr.put(word, s);
                }
                else{
                    word2Abbr.put(word, word);
                }
            }
            else{
                String sibling = abbr2Word.get(s);
                for(int i = abbr.length(); i < word.length(); ++i){
                    s = abbr.toString() + Integer.toString(len) + String.valueOf(lastC);
                    abbr2Count.put(s, 2);
                    if(word.charAt(i) == sibling.charAt(i)){
                        abbr.append(word.charAt(i));
                        len--;
                    }
                    else{
                        len--;
                        s = abbr.toString() + String.valueOf(word.charAt(i)) + Integer.toString(len) + String.valueOf(lastC);
                        ss = abbr.toString() + String.valueOf(sibling.charAt(i)) + Integer.toString(len) + String.valueOf(lastC);
                        abbr2Count.put(s, 1);
                        abbr2Count.put(ss, 1);
                        abbr2Word.put(s, word);
                        abbr2Word.put(ss, sibling);
                        if(s.length() < word.length()){
                            word2Abbr.put(word, s);
                            word2Abbr.put(sibling, ss);
                        }
                        else{
                            word2Abbr.put(word, word);
                            word2Abbr.put(sibling, sibling);
                        }
                        break;
                    }
                }
            }
        }
        
        List<String> ret = new ArrayList<String>();
        for(String word: dict){
            ret.add(word2Abbr.get(word));
        }
        return ret;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        List<String> dict = new ArrayList<String>(Arrays.asList("like", "god", "internal", "interval", "me", "intension", "face", "intrusion"));
        System.out.println("dict: " + dict);
        System.out.println("abbreviation words: " + sol.wordsAbbreviation(dict));
    }
}
