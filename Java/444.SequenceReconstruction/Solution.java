/* Topological sort: O(n)
 * 1. Construct adjacency list and indegree map by the input "seqs"
 * 2. A valid supersequence is formed by the longest path
 * 3. Find the longest path by topological sort
 * 4. During topological sort, the number of successor must be 1. Otherwise, the supersequence is not unique
 * 5. Compare supersequence and org in the end
 */

import java.util.*;

public class Solution{
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int size = org.length + 1;
        HashMap<Integer, Integer> inDegree = new HashMap<Integer, Integer>();
        List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < size; ++i){
            adjacencyList.add(new ArrayList<Integer>());
        }
        
        //construct adjacency list
        for(List<Integer> seq: seqs){
            int prevNode = 0;
            for(int node: seq){
                if(node < 1 || node >= size){
                    return false;
                }
                
                if(!inDegree.containsKey(node)){
                    inDegree.put(node, 0);
                }
                    
                if(prevNode != 0){
                    inDegree.put(node, inDegree.get(node) + 1); 
                }
                
                adjacencyList.get(prevNode).add(node);
                prevNode = node;
            }
        }
       
        //get the starting node
        LinkedList<Integer> nextNodes = new LinkedList<Integer>();
        List<Integer> superSequence = new ArrayList<Integer>();
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(entry.getValue() == 0){
                nextNodes.add(entry.getKey());
            }
        }
        
        //topological sort
        while(!nextNodes.isEmpty()){
            if(nextNodes.size() > 1){
                return false;
            }
            
            int currentNode = nextNodes.poll();
            superSequence.add(currentNode);
            List<Integer> sucessors = adjacencyList.get(currentNode);
            for(int sucessor: sucessors){
                inDegree.put(sucessor, inDegree.get(sucessor) - 1);
                if(inDegree.get(sucessor) == 0){
                    nextNodes.add(sucessor);
                }
            }
        }

        if(superSequence.size() != org.length){
            return false;
        }
        
        for(int i = 0; i < org.length; ++i){
            if(superSequence.get(i) != org[i]){
                return false;
            }
        }
        
        return true;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        List<List<Integer>> seqs = new ArrayList<List<Integer>>();
        seqs.add(Arrays.asList(1, 2));
        seqs.add(Arrays.asList(1, 3));
        seqs.add(Arrays.asList(2, 3));
        int[] org = {1, 2, 3};

        System.out.println("org: " + Arrays.toString(org));
        System.out.println("seqs: " + seqs);
        System.out.println("reconstructionable: " + sol.sequenceReconstruction(org, seqs));
    }
}
