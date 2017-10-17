/* BFS + backtrack: time: max(O(n * stringLength * 26), O(path#)), space:O(n^2)
 * 1. Use BFS to traverse, and get the neighbors by character iteration
 * 2. Only put the non-visted node into the end of queue
 * 3. Only get the neighbors only with the same current distance or the one never visited 
 * 4. Build the predecessorMap from neighbor-currNode pair
 * 5. In the end, use backtreack to find all the paths by predecessorMap, and starting from endWord
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private void backtarcker(List<List<String>> sequences, HashMap<String, List<String>> predecessorMap, 
                              LinkedList<String> sequence, String endWord){
        
        if(!predecessorMap.containsKey(endWord)){
            return;
        }
        else if(predecessorMap.get(endWord).isEmpty()){
            LinkedList<String> newSequence = new LinkedList<String>(sequence);
            newSequence.addFirst(endWord);
            sequences.add(newSequence);
        }
        else{
            //push
            sequence.addFirst(endWord);
            for(String prevWord: predecessorMap.get(endWord)){
                backtarcker(sequences, predecessorMap, sequence, prevWord);
            }
            //pop
            sequence.pollFirst();
        }
    }
    
    private List<String> getNeighbors(String currNode, Set<String> dictionary, HashMap<String, Integer> distanceMap, 
                                      int distance){
        
        List<String> neighbors = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(currNode);
        for(char c = 'a'; c <= 'z'; ++c){
            for(int i = 0; i < sb.length(); ++i){
                if(sb.charAt(i) == c){
                    continue;
                }
                char tmp = sb.charAt(i);
                sb.setCharAt(i, c);
                String currWord = sb.toString();
                if(dictionary.contains(currWord)){
                    if(!distanceMap.containsKey(currWord) || distanceMap.get(currWord) == (distance + 1)){
                        neighbors.add(currWord);
                    }
                }
                sb.setCharAt(i,tmp);
            }
        }
        return neighbors;
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, Integer> distanceMap = new HashMap<String, Integer>();
        HashMap<String, List<String>> predecessorMap = new HashMap<String, List<String>>();
        predecessorMap.put(beginWord, new LinkedList<String>());
        
        Set<String> dictionary = new HashSet<String>(wordList);
        dictionary.remove(beginWord);
        if(!dictionary.contains(endWord)){
            return new LinkedList<List<String>>();
        }
        
        boolean isFound = false;
        LinkedList<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        int distance = 0;
        while(!queue.isEmpty() && !isFound){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                String currNode = queue.pollFirst();
                if(currNode.equals(endWord)){
                    isFound = true;
                    break;
                }
                else{
                    List<String> neighbors = getNeighbors(currNode, dictionary, distanceMap, distance);
                    for(String neighbor: neighbors){
                        //not visited this neighbor before
                        if(!distanceMap.containsKey(neighbor)){
                            queue.add(neighbor);
                        }
                        
                        distanceMap.put(neighbor, distance + 1);
                        
                        if(!predecessorMap.containsKey(neighbor)){
                            predecessorMap.put(neighbor, new LinkedList<String>());
                        }
                        predecessorMap.get(neighbor).add(currNode);
                    }
                }
            }
            distance++;
        }

        List<List<String>> sequences = new LinkedList<List<String>>();
        LinkedList<String> sequence = new LinkedList<String>();
        backtarcker(sequences, predecessorMap, sequence, endWord);
        return sequences;
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

