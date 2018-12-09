/* HashMap: Time:O(n*k), Space;O(1)
 * 1. Maintain two maps, "w2p" for forward mapping, "p2w" for reversed mapping
 * 2. Traverse characters of words, and add word into "mathches" if only if the mapping of w2p and p2w are unique
 */

import java.util.*;

public class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] w2p = new int[256];
        int[] p2w = new int[256];
        List<String> mathes = new ArrayList<>();
        for(String word: words){
            Arrays.fill(w2p, -1);
            Arrays.fill(p2w, -1);
            boolean isMatch = true;
            for(int i = 0; i < pattern.length(); ++i){
                char charW = word.charAt(i);
                char charP = pattern.charAt(i);
                if(w2p[charW] == -1 && p2w[charP] == -1){
                    w2p[charW] = charP;
                    p2w[charP] = charW;
                }
                else if(w2p[charW] == charP && p2w[charP] == charW){
                    continue;
                }
                else{
                    isMatch = false;
                    break;
                }
            }
            
            if(isMatch){
                mathes.add(word);
            }
        }
        return mathes;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] words = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        String pattern = "abb";
        System.out.println("words:" + Arrays.toString(words));
        System.out.println("pattern:" + pattern);
        System.out.println("matches:" + sol.findAndReplacePattern(words, pattern));
    }
}
