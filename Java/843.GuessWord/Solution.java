/* Map: O(n^2). LeetCode's solution is nonsense 
 * 1. Have a map with key = word, value is a list, where list.get(i) is the set of words with i matched chars
 * 2. Have a set "candidates" to store possible word
 * 3. Every time pick a word from the candidate, and do intersection with map.get(word).get(matchCount) to reduce the candidates
 */

import java.util.*;

public class Solution{
    interface Master {
         public int guess(String word);
    }

    private int getMatchCount(String source, String target){
        int count = 0;
        for(int i = 0; i < source.length(); ++i){
            if(source.charAt(i) == target.charAt(i)){
                count++;
            }
        }
        return count;
    }
    
    public void findSecretWord(String[] wordlist, Master master) {
        Map<String, List<Set<String>>> map = new HashMap<>();
        for(String s: wordlist){
            map.put(s, new ArrayList<Set<String>>());
            for(int i = 0; i < 5; ++i){
                map.get(s).add(new HashSet<String>());
            }
        }
        
        for(int i = 0; i < wordlist.length - 1; ++i){
            String source = wordlist[i];
            for(int j = i + 1; j < wordlist.length; ++j){
                String target = wordlist[j];
                int matchCount = getMatchCount(source, target);
                map.get(source).get(matchCount).add(target);
                map.get(target).get(matchCount).add(source);
            }
        }
        
        int min = wordlist.length;
        String word = wordlist[0];
        Set<String> guessed = new HashSet<>();
        guessed.add(word);
        int matchCount = master.guess(word);
        if(matchCount == 6){
            return;
        }
        
        LinkedHashSet<String> candidates = new LinkedHashSet<>(map.get(word).get(matchCount));
        for(int i = 0; i < 9; ++i){
            Iterator<String> itr = candidates.iterator();
            word = itr.next();
            guessed.add(word);
            matchCount = master.guess(word);
            if(matchCount == 6){
                return;
            }
            else{
                Set<String> words = map.get(word).get(matchCount);
                itr = candidates.iterator();
                while(itr.hasNext()){
                    String candidate = itr.next();
                    if(!words.contains(candidate) || guessed.contains(candidate)){
                        itr.remove();
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        System.out.println("no example");
    }
}
