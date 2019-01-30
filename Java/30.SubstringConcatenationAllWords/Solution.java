/* Map + Slide Window: Time:O(n*k), Space:O(m*k), where n = s.length, k = word.length, m = words.length
 * 1. Have a map "wordMap" to record the word count in words
 * 2. Call find "wordLen" times with shifted string s, e.g., s = "barfoothefoobarman", call "find" given "barfoothefoobar", "arfoothefoobar", "rfoothefoobar"
 * 3. In find, add "addWord" from the tail of window and delete "deleteWord" from the head of window, and update "wordMap" and "matchCount"
 * 4. Add index(i + offset + wordLen - windowLen) to indexs if matchCount == wordCount
 */

import java.util.*;

public class Solution {
    private void find(String s, int offset, Map<String, int[]> wordMap, List<Integer> indexs, int wordLen, int wordCount){
        int matchCount = 0;
        int windowLen = wordLen * wordCount;
        for(int i = 0; i < s.length(); i += wordLen){
            String addWord = ((i + wordLen) <= s.length())? s.substring(i, i + wordLen): null;
            String deleteWord = ((i - windowLen) >= 0)? s.substring(i - windowLen, i - windowLen + wordLen): null;
            if(addWord != null && deleteWord != null && addWord.equals(deleteWord)){
                if(matchCount == wordCount){
                    indexs.add(i + offset + wordLen - windowLen);
                }
                continue;
            }

            if(addWord != null && wordMap.containsKey(addWord)){
                wordMap.get(addWord)[1]++;
                matchCount += (wordMap.get(addWord)[0] >= wordMap.get(addWord)[1])? 1: 0;
            }
            if(deleteWord != null && wordMap.containsKey(deleteWord)){
                wordMap.get(deleteWord)[1]--;
                matchCount -= (wordMap.get(deleteWord)[0] > wordMap.get(deleteWord)[1])? 1: 0;
            }
            if(matchCount == wordCount){
                indexs.add(i + offset + wordLen - windowLen);
            }
        }
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, int[]> wordMap = new HashMap<>();
        for(String word: words){
            wordMap.putIfAbsent(word, new int[2]);
            wordMap.get(word)[0]++;
        }
        
        List<Integer> indexs = new ArrayList<>();
        int wordLen = (words.length > 0)? words[0].length(): 0;
        for(int i = 0; i < wordLen; ++i){
            for(Map.Entry<String, int[]> entry: wordMap.entrySet()){
                entry.getValue()[1] = 0;
            }
            find(s.substring(i), i, wordMap, indexs, wordLen, words.length);
        }
        return indexs;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "barfoothefoobarman"; 
        String[] words = {"foo", "bar"};
        System.out.println("s: " + s);
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("indices: " + sol.findSubstring(s, words));
    }
}
