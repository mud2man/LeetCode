/* Map: Time:O(n*k), Space:O(n*k), n:length of S, k:longest length of target
 * 1. Have a map "index2Pair", key is indexes[i], pair is {source[i], target[i]}
 * 2. Have a pointer "prevIdx" to remember the last untranslated index
 * 3. Scan idx from 0 to S.length(), do translate if index2Pair.containsKey(idx) and S.startsWith(source, idx)
 * 4. Update prevIdx properly
 */

import java.util.*;

public class Solution{
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, String[]> index2Pair = new HashMap<>();
        for(int i = 0; i < indexes.length; ++i){
            index2Pair.put(indexes[i], new String[]{sources[i], targets[i]});
        }

        int prevIdx = 0;
        String ret = "";
        for(int idx = 0; idx < S.length(); ++idx){
            if(index2Pair.containsKey(idx)){
                String source = index2Pair.get(idx)[0];
                String target = index2Pair.get(idx)[1];
                ret += S.substring(prevIdx, idx);
                if(S.startsWith(source, idx)){
                    ret += target;
                }else{
                    ret += S.substring(idx, idx + source.length());
                }
                prevIdx = idx + source.length();
            }
        }
        ret += S.substring(prevIdx);
        return ret;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "abcd";
        int[] indexes = {0, 2};
        String[] sources = {"a","cd"};
        String[] targets = {"eee","ffff"};
        System.out.println("S: " + S);
        System.out.println("indexes: " + Arrays.toString(indexes));
        System.out.println("sources: " + Arrays.toString(sources));
        System.out.println("targets: " + Arrays.toString(targets));
        System.out.println("result: " + sol.findReplaceString(S, indexes, sources, targets));
    }
}
