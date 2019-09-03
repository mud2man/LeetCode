/* BFS: O(n*k*26), where k = length of word
 * 1. Have a Hashset wordSet to store the un-visited node
 * 2. Use BFS to visit the wordSet, and put the neighbors into the end of BFS queue
 * 3. If encounter endWord, return distance
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Deque<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        int distance = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                String currNode = queue.pollFirst();
                if(currNode.equals(endWord)){
                    return distance;
                }
                StringBuilder sb = new StringBuilder(currNode);
                int length = currNode.length();
                for(int j = 0; j < length; ++j){
                    for(char c = 'a'; c <= 'z'; c++){
                        char tmp =  sb.charAt(j);
                        if(tmp == c){
                            continue;
                        }
                        sb.setCharAt(j, c);
                        String neighbor = sb.toString();
                        if(wordSet.contains(neighbor)){
                            queue.add(neighbor);
                            wordSet.remove(neighbor);
                        }
                        sb.setCharAt(j, tmp);
                    }
                }
            }
            distance++;
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
