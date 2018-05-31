/* Hash: Time:O(n*k), Space:O(1), where n is the number of words, and k is the length of the longest word in words 
 * 1. Transfer word to character map wordArray, where wordArray[i] = x means the word has x ('a' + i)
 * 2. Traverse words and keep the shortest complete word
 */

import java.util.*;

public class Solution{
    private void getArray(String word, int[] array){
        for(int i  = 0; i < 26; ++i){
            array[i] = 0;
        }
        
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(Character.isLetter(c)){
                c = Character.toLowerCase(c);
                array[c - 'a']++;
            }
        }
    }
    
    private boolean isComplete(int[] licenseArray, int[] wordArray){
        for(int i = 0; i < 26; ++i){
            if(licenseArray[i] > wordArray[i]){
                return false;
            }
        }
        return true;
    }
    
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] licenseArray = new int[26];
        int[] wordArray = new int[26];
        
        getArray(licensePlate, licenseArray);
        String shortestWord = ""; 
        for(String word: words){
            getArray(word, wordArray);
            if(isComplete(licenseArray, wordArray)){
                if(shortestWord.length() == 0 || shortestWord.length() > word.length()){
                    shortestWord = word;
                }
            }
        }
        return shortestWord;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] words = {"step", "steps", "stripe", "stepple"};
        String licensePlate = "1s3 PSt";
        
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("licensePlate: " + licensePlate);
        System.out.println("shortest complete word: " + sol.shortestCompletingWord(licensePlate, words));
    }
}
