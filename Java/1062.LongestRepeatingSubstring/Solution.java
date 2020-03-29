/* Binary search + Rabin-Karp: Time:O(nlogn)(average), Space:O(n)
 * 1. Use binary search to approach the longest duplicate substring by calling Rabin-Karp search
 * 2. Given S and len, rabinKarpSearch will construct a map "hash2startIdxs" to record the key:hash value:startIdx pairs
 * 3. We need to limit the range of hash by modulo of base
 * 4. If we hit the duplicated hash, then we compare the two strings S.substring(start0, start0 + len) and S.substring(start1, start1 + len)
 * 5. We use rolling hash to get the hash. e.g.,hash("abc") = 26^2*0 + 26^1*1 + 2, hash("bcd") = 26^2*1 + 26^1*2 + 3
 */

import java.util.*;


public class Solution{
    private boolean rabinKarpSearch(String S, int len){
        Map<Long, List<Integer>> hash2startIdxs = new HashMap<>();
        long hash = 0;
        long base = (long)Integer.MAX_VALUE;
        long maxCoefficient = 1;
        for(int i = 0; i < len - 1; ++i){
            char c = S.charAt(i);
            hash = (hash * 26 + (long)(c - 'a')) % base;
            maxCoefficient = (maxCoefficient * 26) % base;
        }
        for(int end = len - 1; end < S.length(); ++end){
            char endChar = S.charAt(end);
            char startChar = (end > len - 1)? S.charAt(end - len): 'a';
            hash = (((hash - (long)(startChar - 'a') * maxCoefficient) % base + base) * 26 + (long)(endChar - 'a')) % base;
            if(hash2startIdxs.containsKey(hash)){
                List<Integer> starts = hash2startIdxs.get(hash);
                String target = S.substring(end - len + 1, end + 1);
                for(int start: starts){
                    String source = S.substring(start, start + len);
                    if(source.equals(target)){
                        return true;
                    }
                }
            }else{
                hash2startIdxs.put(hash, new ArrayList<>());
            }
            hash2startIdxs.get(hash).add(end - len + 1);
        }
        return false;
    }
    
    public int longestRepeatingSubstring(String S) {
        int l = 1;
        int r = S.length() - 1;
        String longestDuplicate = "";
        while(l <= r){
            int mid = (l + r) / 2;
            if(rabinKarpSearch(S, mid)){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return r;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "abbaba";
        System.out.println("S:" + S);
        System.out.println("longest repeating substring length:" + sol.longestRepeatingSubstring(S));
    }
}
