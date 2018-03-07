/* BFS: Time:O(n*m), Space:O(n), where n is the size of "words", m is the length of "S"
 * 1. Have a list of index "indexList" to record the current character position of "word" in words
 * 2. Add "count" with the frequency index[3] when index[1] reach to the end of word
 */

import java.util.*;

public class Solution{
    public int numMatchingSubseq(String S, String[] words) {
        List<int[]> indexList = new LinkedList<int[]>();
        HashMap<String, Integer> indexMap = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; ++i){
            if(!indexMap.containsKey(words[i])){
                indexList.add(new int[]{i, 0, words[i].length(), 1});
                indexMap.put(words[i], indexList.size() - 1);
            }
            else{
                indexList.get(indexMap.get(words[i]))[3]++;
            }
        }
        
        int count = 0;
        for(int i = 0; i < S.length(); ++i){
            char c = S.charAt(i);
            Iterator<int[]> iterator = indexList.iterator();
            while(iterator.hasNext()){
                int[] index = iterator.next();
                if(words[index[0]].charAt(index[1]) == c){
                    index[1]++;
                }
                
                if(index[1] == index[2]){
                    iterator.remove();
                    count += index[3];
                }
            }
        }
        
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        String[] words = {"a", "bb", "acd", "ace"};
        String S = "abcde";
        
        sol = new Solution();
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("S: " + S);
        System.out.println("number of matching subsequences: " + sol.numMatchingSubseq(S, words));
    }
}
