/* Dynamic programming: O(max{cols*sentence.size(), rows))
 * 1. Caculate the number of words a single row can contains based on the word index
 * 2. Accumulate the number of words with the incremental of rows
 */

import java.util.*; // Stack

public class Solution {
    private class SentencesInRow{
        int sentenceCount;
        int nextWordId;
        SentencesInRow(int s, int n){sentenceCount = s; nextWordId = n;}
    }
    
    private boolean setSentenceCount(SentencesInRow[] sentencesInRows, int[] lengths, int cols){
        int size = lengths.length;
            
        for(int startWordId = 0; startWordId < size; ++startWordId){
            int x = 0;
            int count = 0;
            int wordId = startWordId;
            
            if(lengths[wordId] > cols){
                return false;
            }
            
            while(x < cols){
                x = x + lengths[wordId] - 1;
                if(x < cols && wordId == (size - 1)){
                    count++; 
                }
                wordId = (x < cols) ? (wordId + 1) % size: wordId;
                x = x + 2;
            }
            sentencesInRows[startWordId] = new SentencesInRow(count, wordId);
        }
        return true;
    }
    
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] lengths = new int[sentence.length];
        for(int i = 0; i < sentence.length; ++i){
            lengths[i] = sentence[i].length();
        }
        
        SentencesInRow[] sentencesInRow = new SentencesInRow[sentence.length];
        if(!setSentenceCount(sentencesInRow, lengths, cols)){
            return 0;
        }
        
        int count = 0;
        int wordId = 0;
        for(int rowId = 0; rowId < rows; ++rowId){
            count += sentencesInRow[wordId].sentenceCount;
            wordId = sentencesInRow[wordId].nextWordId;
        }
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        String[] sentence = {"a", "bcd", "e"};
        int rows = 3;
        int cols = 6;
        
        System.out.println("sentence:" + Arrays.toString(sentence));
        System.out.println("rows:" + rows);
        System.out.println("cols:" + cols);

        sol = new Solution();

        System.out.println("number of sentence: " + sol.wordsTyping(sentence, rows, cols));
    }
}
