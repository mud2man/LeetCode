/* Topological sort: Time:O(n), Space: O(n), where n is number of characters of string array
 * 1. Add edges from all the continuous two words. e.g., t->f for "wrt" and "wrf"
 * 2. Use topological sort  to find the order
 */         

import java.util.*;

public class Solution {
    private void findEdge(String parent, String child, Map<Integer, Set<Integer>> ajdacencyList, int[] indegree){
        int i;
        for(i = 0; i < Math.min(parent.length(), child.length()); ++i){
            int source = parent.charAt(i) - 'a';
            int target = child.charAt(i) - 'a';
            if(source != target){
                ajdacencyList.putIfAbsent(source, new HashSet<Integer>());
                if(!ajdacencyList.get(source).contains(target)){
                    ajdacencyList.get(source).add(target);
                    indegree[source] = (indegree[source] == -1)? 0: indegree[source];
                    indegree[target] = (indegree[target] == -1)? 1: indegree[target] + 1;
                }
                break;
            }
            else{
                indegree[source] = (indegree[source] == -1)? 0: indegree[source];
            }
        }
        for(int j = i; j < child.length(); ++j){
            indegree[child.charAt(j) - 'a'] = (indegree[child.charAt(j) - 'a'] == -1)? 0: indegree[child.charAt(j) - 'a'];
        }
    }
    
    public String alienOrder(String[] words) {
        Map<Integer, Set<Integer>> ajdacencyList = new HashMap<Integer, Set<Integer>>();
        int[] indegree = new int[26];
        
        if(words.length == 1){
            return words[0];
        }

        Arrays.fill(indegree, -1); 
        for(int i = 0; i < words[0].length(); ++i){
            indegree[words[0].charAt(i) - 'a'] = 0;
        }
 
        for(int i = 0; i < (words.length - 1); ++i){
            findEdge(words[i], words[i + 1], ajdacencyList, indegree);
        }
        
        // find nodes with indegree 0 as sources
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int nodeCount = 0;
        for(int i = 0; i < indegree.length; ++i){
            if(indegree[i] == 0){
                queue.add(i);
            }
            nodeCount = (indegree[i] != -1)? nodeCount + 1: nodeCount;
        }
        
        // topological sort
        StringBuilder order = new StringBuilder("");
        int visitedCount = 0;
        while(!queue.isEmpty()){
            int currentNode = queue.pollFirst();
            visitedCount++;
            order.append((char)('a' + currentNode));
            Set<Integer> children = (ajdacencyList.containsKey(currentNode))? ajdacencyList.get(currentNode): new HashSet();
            for(int child: children){
                indegree[child]--;
                if(indegree[child] == 0){
                    queue.add(child);
                }
            }
        }
        return (visitedCount == nodeCount)? order.toString(): "";
    }

    public static void main(String[] args){
        Solution sol;
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        
        sol = new Solution();
        System.out.println("words:" + Arrays.toString(words));
        System.out.println("order:" + sol.alienOrder(words));
    }
}
