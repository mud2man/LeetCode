/* Topological sort: Time:O(n), Space: O(n), where n is number of characters of string array
 * 1. Add edges from all the continuous two words. e.g., t->f for "wrt" and "wrf"
 * 2. Use topological sort to find the order
 */         

import java.util.*;

public class Solution {
    private void findEdge(String parent, String child, Map<Character, Set<Character>> ajdacencyList, int[] indegree){
        for(int i = 0; i < Math.min(parent.length(), child.length()); ++i){
            char source = parent.charAt(i);
            char target = child.charAt(i);
            if(source != target){
                ajdacencyList.putIfAbsent(source, new HashSet<>());
                if(!ajdacencyList.get(source).contains(target)){
                    ajdacencyList.get(source).add(target);
                    indegree[source - 'a'] = (indegree[source - 'a'] == -1)? 0: indegree[source - 'a'];
                    indegree[target - 'a'] = (indegree[target - 'a'] == -1)? 1: indegree[target - 'a'] + 1;
                }
                break;
            }
        }
        for(int j = 0; j < child.length(); ++j){
            indegree[child.charAt(j) - 'a'] = (indegree[child.charAt(j) - 'a'] == -1)? 0: indegree[child.charAt(j) - 'a'];
        }
    }
    
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> ajdacencyList = new HashMap<>();
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);
        for(int i = 0; i < words[0].length(); ++i){
            indegree[words[0].charAt(i) - 'a'] = 0;
        }
        for(int i = 0; i < (words.length - 1); ++i){
            findEdge(words[i], words[i + 1], ajdacencyList, indegree);
        }
        
        // find nodes with indegree 0 as sources
        Deque<Character> queue = new LinkedList<>();
        int nodeCount = 0;
        for(int i = 0; i < indegree.length; ++i){
            if(indegree[i] == 0){
                queue.add((char)(i + 'a'));
            }
            nodeCount = (indegree[i] != -1)? nodeCount + 1: nodeCount;
        }
        
        // topological sort
        StringBuilder order = new StringBuilder("");
        int visitedCount = 0;
        while(!queue.isEmpty()){
            char currentNode = queue.pollFirst();
            visitedCount++;
            order.append(currentNode);
            Set<Character> children = ajdacencyList.getOrDefault(currentNode, new HashSet<>());
            for(char child: children){
                indegree[child - 'a']--;
                if(indegree[child - 'a'] == 0){
                    queue.add(child);
                }
            }
        }
        return (visitedCount == nodeCount)? order.toString(): "";
    }
 
    public static void main(String[] args){
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        Solution sol = new Solution();
        System.out.println("words:" + Arrays.toString(words));
        System.out.println("order:" + sol.alienOrder(words));
    }
}
