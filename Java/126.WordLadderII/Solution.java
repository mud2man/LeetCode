/* BFS + DFS: time: max(O(n * stringLength * 26), O(path#)), space:O(n^2)
 * 1. Use BFS to create graph, where edge is between two closed words, and direction is from right to left
 * 2. Use DFS to collect all paths from endWord to beginWord
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private void bfs(String beginWord, String endWord, Set<String> unvisited, Map<String, Set<String>> right2Left){
        Set<String> queue = new HashSet<>();
        queue.add(beginWord);
        
        while(!queue.isEmpty()){
            Set<String> nextQueue = new HashSet<>();
            for(String visitedWord: queue){
                StringBuilder word = new StringBuilder(visitedWord);
                for(int i = 0; i < word.length(); ++i){
                    for(char c = 'a'; c <= 'z'; ++c){
                        word.setCharAt(i, c);
                        if(visitedWord.charAt(i) != word.charAt(i) && unvisited.contains(word.toString())){
                            right2Left.putIfAbsent(word.toString(), new HashSet<>());
                            right2Left.get(word.toString()).add(visitedWord);
                            nextQueue.add(word.toString()); 
                        }
                    }
                    word.setCharAt(i, visitedWord.charAt(i));
                }
            }
            if(nextQueue.contains(endWord)){
                return;
            }
            else{
                for(String visitedWord: nextQueue){
                    unvisited.remove(visitedWord);
                }
                queue = nextQueue;
            }
        }
    }
    
    private void dfs(String begin, String curr, Map<String, Set<String>> right2Left, Deque<String> path, List<List<String>> ladders){
        path.addFirst(curr);
        if(curr.equals(begin)){
            List<String> ladder = new LinkedList<>(path);
            ladders.add(ladder);
            path.pollFirst();
            return;
        }
        Set<String> lefts = right2Left.get(curr);
        for(String left: lefts){
            dfs(begin, left, right2Left, path, ladders);
        }
        path.pollFirst();
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> unvisited = new HashSet<>(wordList);
        Map<String, Set<String>> right2Left = new HashMap<>();
        bfs(beginWord, endWord, unvisited, right2Left);
        
        if(!right2Left.containsKey(endWord)){
            return new ArrayList<>();
        }
        else{
            List<List<String>> ladders = new LinkedList<>();
            dfs(beginWord, endWord, right2Left, new LinkedList<String>(), ladders);
            return ladders;
        }
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordArray = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = new ArrayList<>(Arrays.asList(wordArray));

        System.out.println("wordList: " + wordList);
        List<List<String>> paths = sol.findLadders(beginWord, endWord, wordList);
        System.out.println("paths: ");
        for(List<String> path: paths){
            System.out.println(path);
        }
        System.out.println("");
    }
}

