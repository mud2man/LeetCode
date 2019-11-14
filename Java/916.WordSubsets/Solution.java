/* Hash: Time:O(A + B), Space:O(1)
 * 1. Caculate the minimum of universal of B "universalB"
 * 2. Iterate A, add a into A if its count cover universalB
 */

import java.util.*; // Stack

public class Solution {
    private int[] getCount(String s){
        int[] count = new int[26];
        for(char c: s.toCharArray()){
            count[c - 'a']++;
        }
        return count;
    }
    
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] universalB = new int[26];
        for(String b: B){
            int[] countB = getCount(b);
            for(int i = 0; i < 26; ++i){
                universalB[i] = Math.max(universalB[i], countB[i]);
            }
        }
        
        List<String> universals = new ArrayList<>();
        for(String a: A){
            int[] countA = getCount(a);
            boolean isUniversal = true;
            for(int i = 0; i < 26; ++i){
                if(countA[i] < universalB[i]){
                    isUniversal = false;
                    break;
                }
            }
            if(isUniversal){
                universals.add(a);
            }
        }
        return universals;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] A = {"amazon", "apple", "facebook", "google", "leetcode"};
        String[] B = {"e", "oo"};
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("B:" + Arrays.toString(B));
        System.out.println("universal words" + sol.wordSubsets(A, B));
    }
}
