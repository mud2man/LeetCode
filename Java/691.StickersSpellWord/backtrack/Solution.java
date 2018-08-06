/* Slide window + min heap: Time:O(n^m), where n. LeetCode has a dynamic programming solution 
 * 1. Enoce the remaining chars as a string by "hashcode", and have a map "solutions" to store the minimum stickers with key = remain
 * 2. Apply backtrack to find all the combination, and return solution if the "remain" in map "solution"
 */

import java.util.*;

public class Solution{
    private int getMatch(int[] target, int[] candidate){
        int match = 0;
        for(int i = 0; i < 26; ++i){
            int min = Math.min(target[i], candidate[i]);
            match += min;
        }
        return match;
    }
    
    private String hashcode(int[] word){
        StringBuilder code = new StringBuilder("");
        for(int i = 0; i < 26; ++i){
            code.append(word[i] + ".");
        }
        return code.toString();
    }
    
    private int dfs(int[] remain, List<int[]> candidates, Map<String, Integer> solutions){
        String code = hashcode(remain);
        if(solutions.containsKey(code)){
            int times = solutions.get(code);
            return (times == -1)? -1: times;
        }
        
        int count = 0;
        for(int i = 0; i < 26; ++i){
            count += remain[i];
        }
        if(count == 0){
            return 0;
        }
        
        List<int[]> nextCandidates = new LinkedList<int[]>(); 
        for(int[] candidate: candidates){
            if(getMatch(candidate, remain) > 0){
                nextCandidates.add(candidate);
            }
        }
        
        int minTime = Integer.MAX_VALUE;
        for(int[] candidate: nextCandidates){
            int[] diff = new int[26];
            for(int i = 0; i < 26; ++i){
                diff[i] = Math.min(candidate[i], remain[i]);
                remain[i] -= diff[i];
            }
                
            int time = dfs(remain, candidates, solutions);
            if(time != -1){
                minTime = Math.min(minTime, time);
            }
                
            for(int i = 0; i < 26; ++i){
                remain[i] += diff[i];
            }
        }
        
        if(minTime != Integer.MAX_VALUE){
            solutions.put(code, 1 + minTime);
            return minTime + 1;
        }
        else{
            solutions.put(code, -1);
            return -1;
        }
    } 
    
    public int minStickers(String[] stickers, String target) {
        List<int[]> candidates = new LinkedList<int[]>();
        for(String sticker: stickers){
            int[] candidate = new int[26];
            for(int i = 0; i < sticker.length(); ++i){
                candidate[sticker.charAt(i) - 'a']++;
            }
            candidates.add(candidate);
        }
        
        int[] remain = new int[26];
        for(int i = 0; i < target.length(); ++i){
            remain[target.charAt(i) - 'a']++;
        }
        
        Map<String, Integer> solutions = new HashMap<String, Integer>();
        return dfs(remain, candidates, solutions);
    }
 
    public static void main(String[] args){
        String[] stickers = {"with", "example", "science"};
        String target = "thehat";
        Solution sol = new Solution();

        System.out.println("stickers: " + Arrays.toString(stickers));
        System.out.println("target: " + target);
        System.out.println("minimum stickers#: " + sol.minStickers(stickers, target));
    }
}
