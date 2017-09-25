/* BFS: O(n^2*k), where k = length of word, because each pair only compare once
 * 1. Create currBeginWords to store the current beginning words
 * 2. Iterate every words in wordList, and compare them with the word in currBeginWords
 * 3. If only one letter changed, put it into nextBeginWords and remove it from wordList. 
 * 4. Also compare if it equals to endWord, if so, return
 * 5. Repeat step 2, 3, and 4, until currBeginWords is empty
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    boolean isClosed(String sourceWord, String targetWord){
        int count = 0;
        for(int i = 0; i < sourceWord.length(); ++i){
            if(sourceWord.charAt(i) != targetWord.charAt(i)){
                count++;
                if(count > 1){
                    return false;
                }
            }
        }
        return true;
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        LinkedList<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        
        int length = 0;
        while(!queue.isEmpty()){
            System.out.println(queue);
            int size = queue.size();
            length++;
            for(int i = 0; i < size; ++i){
                String source = queue.pollFirst();
                if(source.equals(endWord)){
                    return length;
                }
                else{
                    Iterator<String> itr = wordList.iterator();
                    while( itr.hasNext() ){
                        String target = itr.next();
                        if(isClosed(source, target)){
                            queue.add(target);
                            itr.remove();
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordArray = {"hot", "dot", "dog",
                              "lot", "log", "cog"};
        List<String> wordList = new ArrayList<>(Arrays.asList(wordArray));

        System.out.println("wordList: " + wordList);
        System.out.println("shortestLength: " + sol.ladderLength(beginWord, endWord, wordList));
    }
}
