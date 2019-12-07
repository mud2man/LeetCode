/* Backtrack:O(n!), Space:O(n!)
 * 1. Parse the string and store them into list of words
 * 2. Backtrack to collect all the combinations
 */

import java.util.*;

public class Solution{
    private void backtrack(List<List<String>> listOfWords, int idx, List<String> combinations, String path){
        if(idx == listOfWords.size()){
            combinations.add(path);
            return;
        }
        for(String word: listOfWords.get(idx)){
           backtrack(listOfWords, idx + 1, combinations, path + word); 
        }
    }
    
    public String[] expand(String S) {
        List<List<String>> listOfWords = new ArrayList<>();
        int idx = 0;
        while(idx < S.length()){
            int leftBraceIdx = S.indexOf('{', idx);
            if(leftBraceIdx != -1){
                String word = S.substring(idx, leftBraceIdx);
                if(word.length() > 0){
                    listOfWords.add(Arrays.asList(word));
                }
                int rightBraceIdx = S.indexOf('}', leftBraceIdx);
                word = S.substring(leftBraceIdx + 1, rightBraceIdx);
                String[] words = word.split(",");
                List<String> listOfWord = new ArrayList<>();
                for(String w: words){
                    listOfWord.add(w);
                }
                Collections.sort(listOfWord);
                listOfWords.add(listOfWord);
                idx = rightBraceIdx + 1;
            }else{
                String word = S.substring(idx);
                listOfWords.add(Arrays.asList(word));
                break;
            }
        }
        List<String> combinations = new ArrayList<>();
        backtrack(listOfWords, 0, combinations, "");
        String[] ret = new String[combinations.size()];
        for(int i = 0; i < combinations.size(); ++i){
            ret[i] = combinations.get(i);
        }
        return ret;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "{a,b}c{d,e}f";
        System.out.println("S: " + S);
        System.out.println("combinations: " + Arrays.toString(sol.expand(S)));
    }
}
