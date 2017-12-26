/* Union and Find: Time:O(nlogn*k), Space:O(nk), where n is the number of word, and k is the length of word
 * 1. Have the HashMap "roots" to store the root of the current word
 * 2. Use find and union to construct "roots"
 * 3. Traverse the array words1, and compare the root between word1 and word2
 */

import java.util.*;

public class Solution{
    private String find(String target, HashMap<String, String> roots){
        if(target.equals(roots.get(target))){
            return target;
        }    
        else{
            //compression
            String grandFarther = roots.get(roots.get(target));
            roots.put(target, grandFarther);
            
            return find(grandFarther, roots);
        }
    }
    
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if(words1.length != words2.length){
            return false;
        }
        
        HashMap<String, String> roots = new HashMap<String, String>();
        for(String[] pair: pairs){
            if(!roots.containsKey(pair[0])){
                roots.put(pair[0], pair[0]);
            }
            
            if(!roots.containsKey(pair[1])){
                roots.put(pair[1], pair[1]);
            }
            
            //find
            String root0 = find(pair[0], roots);
            String root1 = find(pair[1], roots);
            
            //union
            roots.put(root0, root1);
        }
        
        for(int i = 0; i < words1.length; ++i){
            if(!words1[i].equals(words2[i])){
                String root1 = roots.containsKey(words1[i])? find(words1[i], roots): words1[i];
                String root2 = roots.containsKey(words2[i])? find(words2[i], roots): words2[i];
                
                if(!root1.equals(root2)){
                    return false;
                }
            }
        }
        
        return true;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String[] words1 = {"great", "acting", "skills"};
        String[] words2 = {"fine", "drama", "talent"};
        String[][] pairs = {{"great", "good"}, {"fine", "good"}, {"acting","drama"}, {"skills","talent"}};
        
        System.out.println("words1: " + Arrays.toString(words1));
        System.out.println("words2: " + Arrays.toString(words2));
        System.out.println("pairs: ");
        for(String[] pair: pairs){
            System.out.println(Arrays.toString(pair));
        }
        System.out.println("\nisSimilar: " + sol.areSentencesSimilarTwo(words1, words2, pairs));
    }
}
