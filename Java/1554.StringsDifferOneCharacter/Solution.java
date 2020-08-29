/* Rabin-Karp: Time:O(n*m), Space:O(n*m)
 * 1.Calculate the hash of substring(0, length() - 1) for each string
 * 2.Update the hash in O(1) while shift right the window one unit
 * 3.When duplicated hash happen, we do fully comparisom to make sure
 */     

import java.util.*; // Stack

public class Solution {
    private boolean helper(List<Integer> candidateIdxes, String[] dict, int deleteIdx){
        Set<String> words = new HashSet<>();
        for(int candidateIdx: candidateIdxes){
            StringBuilder sb = new StringBuilder("");
            for(int i = 0; i < dict[0].length(); ++i){
                if(i != deleteIdx){
                    sb.append(dict[candidateIdx].charAt(i));
                }
            }
            words.add(sb.toString());
        }
        return (candidateIdxes.size() > words.size());
    }
    
    public boolean differByOne(String[] dict) {
        int base = 100_000_000;
        int[] hashCodes = new int[dict.length];
        int msb = 1;
        for(int i = 0; i < dict[0].length() - 2; ++i){
            msb = (msb * 26) % base;
        }
        for(int i = 0; i < dict.length; ++i){
            int hashCode = 0;
            String word = dict[i];
            for(int j = word.length() - 2; j >= 0; --j){
                hashCode = (hashCode * 26 + (int)(word.charAt(j) - 'a')) % base;
            }
            hashCodes[i] = hashCode;
        }
        
        for(int deleteIdx = 0; deleteIdx < dict[0].length(); ++deleteIdx){
            Map<Integer, List<Integer>> hash2Indexes = new HashMap<>();
            for(int i = 0; i < dict.length; ++i){
                int addIdx = (deleteIdx + (dict[0].length() - 1)) % dict[0].length();
                char addChar = dict[i].charAt(addIdx);
                hashCodes[i] = (hashCodes[i] / 26 + msb * (int)(addChar - 'a')) % base;
                hash2Indexes.computeIfAbsent(hashCodes[i], key -> new ArrayList<>()).add(i);
            } 
            for(Map.Entry<Integer, List<Integer>> entry: hash2Indexes.entrySet()){
                if(entry.getValue().size() >= 2 && helper(entry.getValue(), dict, deleteIdx)){
                    return true;
                }
            }
        }
        return false;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] dict = {"abcd", "acbd", "aacd"};
        System.out.println("dict:" + Arrays.toString(dict));
        System.out.println("has strings differ by one character:" + sol.differByOne(dict));
    }
}
