/* BFS + DFS: time: max(O(n * stringLength * 26), O(path#)), space:O(n^2)
 * 1. Use BFS to create graph, where edge is between two closed words, and direction is from right to left
 * 2. Use DFS to collect all paths from endWord to beginWord
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private boolean bfs(String beginWord, String endWord, Set<String> unvisited, Map<String, Set<String>> right2Lefts){
        Deque<String> queue = new LinkedList<>();
        queue.add(beginWord);
        boolean hit = false;
        while(!queue.isEmpty() && !hit){
            int size = queue.size();
            Set<String> rights = new HashSet<>();
            for(int i = 0; i < size; ++i){
                String left = queue.pollFirst();
                //find rights
                StringBuilder sb = new StringBuilder(left);
                for(int j = 0; j < left.length(); ++j){
                    char c = sb.charAt(j);
                    for(int k = 0; k < 26; ++k){
                        if(c != 'a' + k){
                            sb.setCharAt(j, (char)('a' + k));
                            String right = sb.toString();
                            if(unvisited.contains(right)){
                                if(!rights.contains(right)){
                                    queue.add(right);
                                }
                                rights.add(right);
                                right2Lefts.computeIfAbsent(right, key -> new HashSet<>()).add(left);
                                hit = right.equals(endWord)? true: hit;   
                            }
                        }
                    }
                    sb.setCharAt(j, c);
                }
            }
            unvisited.removeAll(rights);
        }
        return hit;
    }
    
    private void dfs(String begin, String cur, Deque<String> path, Map<String, Set<String>> right2Lefts, List<List<String>> ret){
        path.addFirst(cur);
        if(cur.equals(begin)){
            ret.add(new LinkedList<>(path));
            path.pollFirst();
            return;
        }
        
        Set<String> lefts = right2Lefts.get(cur);
        for(String left: lefts){
            dfs(begin, left, path, right2Lefts, ret);
        }
        path.pollFirst();
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, Set<String>> right2Lefts = new HashMap<>();
        List<List<String>> ladders = new ArrayList<>();
        Set<String> unvisited = new HashSet<>(wordList);
        if(bfs(beginWord, endWord, unvisited, right2Lefts)){
            dfs(beginWord, endWord, new LinkedList<>(), right2Lefts, ladders);
        }
        return ladders;
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

