/* BFS: O(n*k*26), where k = length of word
 * 1. Have a Hashset wordSet to store the un-visited node
 * 2. Use BFS to visit the wordSet, and put the neighbors into the end of BFS queue
 * 3. If encounter endWord, return distance
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private List<String> getNeighbors(Set<String> wordSet, String currNode){
        StringBuilder sb = new StringBuilder(currNode);
        int length = currNode.length();
        List<String> neighbors = new LinkedList<String>();
        for(int i = 0; i < length; ++i){
            for(char c = 'a'; c <= 'z'; c++){
                char tmp =  sb.charAt(i);
                if(tmp == c){
                    continue;
                }
                sb.setCharAt(i, c);
                String neighbor = sb.toString();
                if(wordSet.contains(neighbor)){
                    neighbors.add(neighbor);
                    wordSet.remove(neighbor);
                }
                sb.setCharAt(i, tmp);
            }
        }
        return neighbors;
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<String>(wordList);
        if(!wordSet.contains(endWord)){
            return 0;
        }
        
        Deque<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        int distance = 1;
        while(!queue.isEmpty()){
            Deque<String> nextQueue = new LinkedList<>();
            while(!queue.isEmpty()){
                String currNode = queue.pollFirst();
                if(currNode.equals(endWord)){
                    return distance;
                }
                nextQueue.addAll(getNeighbors(wordSet, currNode));
            }
            queue = nextQueue;
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
