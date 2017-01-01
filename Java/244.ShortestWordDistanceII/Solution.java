/* Use merge sort: O(n)
 * 1. Create a HashMap to record all the positions of every distinct word
 * 2. Use merge sort to find the shortest distence, given the positions list of the two words
 */

import java.util.*;

public class Solution{
	private HashMap<String, List<Integer>> posList;

    public Solution(String[] words) {
        int idx;
        
        posList = new HashMap<String, List<Integer>>();
        
        for(idx = 0; idx < words.length; ++idx){
            if(!posList.containsKey(words[idx])){
                posList.put(words[idx], new ArrayList<Integer>(Arrays.asList(idx)));
            }
            else{
                posList.get(words[idx]).add(idx);
            }
        }
    }
    
    int shortestDis(List<Integer> word1Pos, List<Integer> word2Pos){
        int minDis;
        int idx1;
        int idx2;
        int currIdx;
        
        minDis = Integer.MAX_VALUE;
        
        idx1 = 0;
        idx2 = 0;
        while((idx1 < word1Pos.size()) && (idx2 < word2Pos.size())){
            minDis = Math.min(minDis, Math.abs(word1Pos.get(idx1) - word2Pos.get(idx2))); 
            
            if(word1Pos.get(idx1) > word2Pos.get(idx2)){
                ++idx2;
            }
            else{
                ++idx1;
            }
        }
        return minDis; 
    }

    public int shortest(String word1, String word2) {
        List<Integer> word1Pos;
        List<Integer> word2Pos;
        
        word1Pos = posList.get(word1);
        word2Pos = posList.get(word2);
        
        return shortestDis(word1Pos, word2Pos);
    }

	public static void main(String[] args){
		Solution sol;
		String[] words = {"practice", "makes", "perfect", "coding", "makes"};
		String word1;
		String word2;		

		sol = new Solution(words);
		
		System.out.println("words: " + Arrays.toString(words));
		
		word1 = "practice";
		word2 = "coding";
		System.out.println("distence between " + word1 + " and " + word2 + ": " + sol.shortest(word1, word2));
		
		word1 = "makes";
		word2 = "coding";
		System.out.println("distence between " + word1 + " and " + word2 +  ": " + sol.shortest(word1, word2));
	}
}
