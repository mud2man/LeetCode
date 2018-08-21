/* Map + Slide Window: Time:O(n*k), Space:O(m*k), where n = s.length, k = word.length, m = words.length
 * 1. Have a map word2Id to store the mappting from word to id 
 * 2. Have a map diff to store the words need be covered
 * 3. Have a array "dp", where dp[i] store the id of word started with index i, -1 if there is no such word
 * 4. Given shift (0 to (wordLen - 1)), maintain a window with size of "dicLen", and traverse to right of s
 * 5. Update "localdDiff" after traverse, if localDiff is empty, it means we coverd each wordd at once in "words".
 * 6. Since every traverse take n/wordLen times and we take wordLen traverse, so the last loop is O(n)
 * 7. Hence, time complexity is bound by the second loop, which takes O(n*k)
 */

import java.util.*;

public class Solution {
    private void updateDiff(int id, Map<Integer, Integer> diffMap, int diffVal){
        if(id != -1){
            if(diffMap.containsKey(id)){
                diffMap.put(id, diffMap.get(id) + diffVal);
                if(diffMap.get(id) == 0){
                    diffMap.remove(id);
                }
            }
            else{
                diffMap.put(id, diffVal);
            }
        }
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> indices = new ArrayList<>();
        Map<String, Integer> word2Id = new HashMap<>();
        Map<Integer, Integer> diff = new HashMap<>();
        int dicLen = words.length;
        if(dicLen == 0){
            return indices;
        }
        int wordLen = words[0].length();
        int id = 0;
        for(String word: words){
            if(word.length() != wordLen){
                return indices;
            }
            if(word2Id.containsKey(word)){
                diff.put(word2Id.get(word), diff.get(word2Id.get(word)) + 1);
            }
            else{
                word2Id.put(word, id);
                diff.put(id++, 1);
            }
        }
        
        int[] dp = new int[s.length()];
        for(int i = 0; i < s.length(); ++i){
            if(i + wordLen <= s.length()){
                String word = s.substring(i, i + wordLen);
                if(word2Id.containsKey(word)){
                    dp[i] = word2Id.get(word);
                }
                else{
                    dp[i] = -1;
                }
            }
            else{
                dp[i] = -1;
            }
        }

        for(int shift = 0; shift < wordLen; ++shift){
            Map<Integer, Integer> localDiff = new HashMap<>(diff);
            for(int i = 0; i < wordLen * dicLen && (i + shift) < s.length(); i += wordLen){
                id = dp[i + shift];
                updateDiff(id, localDiff, -1);
            }
            
            for(int start = shift; (start + wordLen * dicLen) <= s.length(); start += wordLen){
                if(localDiff.isEmpty()){
                    indices.add(start);
                }
                if((start + wordLen * dicLen) >= s.length()){
                    break;
                }
                
                int addId = dp[start];
                updateDiff(addId, localDiff, 1);
                int removeId = dp[start + wordLen * dicLen];
                updateDiff(removeId, localDiff, -1);
            }
        }
        return indices;
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
