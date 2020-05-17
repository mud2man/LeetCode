/* Sort: Time:O(nlogn), Space:O(n)
 * 1. Implement comparator with higer prioity on WordAndIndex.len, then WordAndIndex.index
 * 2. Append WordAndIndex.word
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    private class WordAndIndex{
        String word;
        int index;
        int len;
        WordAndIndex(String s, int i){word = s; index = i; len = word.length();}
    }
    
    public String arrangeWords(String text) {
        String[] words = text.split(" ");
        List<WordAndIndex> wordAndIndices = new ArrayList<>();
        for(int i = 0; i < words.length; ++i){
            wordAndIndices.add(new WordAndIndex(words[i], i));
        }
        Collections.sort(wordAndIndices, (x, y) -> ((x.len != y.len)? (x.len - y.len): (x.index - y.index)));
        StringBuilder arranged = new StringBuilder("");
        for(int i = 0; i < wordAndIndices.size(); ++i){
            String word = wordAndIndices.get(i).word;
            if(i == 0){
                arranged.append(Character.toUpperCase(word.charAt(0)));
                arranged.append(word.substring(1, word.length()));
            }else{
                arranged.append(word.toLowerCase());
            }
            if(i < wordAndIndices.size() - 1){
                arranged.append(" ");
            }
        }
        return arranged.toString();
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String text = "Leetcode is cool";
        System.out.println("arrange words:" + sol.arrangeWords(text));
    }
}
