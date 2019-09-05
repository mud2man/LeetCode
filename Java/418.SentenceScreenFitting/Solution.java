/* Dynamic programming: O(max{cols*sentence.size(), rows))
 * 1. Caculate the number of words a single row can contains based on the word index
 * 2. Accumulate the number of words with the incremental of rows
 */

import java.util.*; // Stack

public class Solution {
    private boolean setSentenceCount(int[][] dp, int[] lengths, int cols){
        for(int startWordId = 0; startWordId < lengths.length; ++startWordId){
            int x = 0;
            int count = 0;
            int wordId = startWordId;
            
            if(lengths[wordId] > cols){
                return false;
            }
            while(x < cols){
                x = x + lengths[wordId] - 1;
                count += (x < cols && wordId == (lengths.length - 1))? 1: 0;
                wordId = (x < cols) ? (wordId + 1) % lengths.length: wordId;
                x = x + 2;
            }
            dp[startWordId] = new int[]{count, wordId};
        }
        return true;
    }
    
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] lengths = new int[sentence.length];
        for(int i = 0; i < sentence.length; ++i){
            lengths[i] = sentence[i].length();
        }
        
        int[][] dp = new int[sentence.length][2];
        if(!setSentenceCount(dp, lengths, cols)){
            return 0;
        }
        
        int count = 0;
        for(int rowId = 0, wordId = 0; rowId < rows; ++rowId){
            count += dp[wordId][0];
            wordId = dp[wordId][1];
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] sentence = {"a", "bcd", "e"};
        int rows = 3;
        int cols = 6;
        
        System.out.println("sentence:" + Arrays.toString(sentence));
        System.out.println("rows:" + rows);
        System.out.println("cols:" + cols);
        System.out.println("number of sentence: " + sol.wordsTyping(sentence, rows, cols));
    }
}
