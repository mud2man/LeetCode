/* Dynamic programming: Time:O(n * k * 26 * n), Space:O(n)
 * 1. 
 * 2. 
 */

import java.util.*; // Stack


public class Solution {
    private int getEncodeLen(int count){
        return (count <= 1)? 1: 2 + (int)(Math.log(count) / Math.log(10));
    }
    
    public int getLengthOfOptimalCompression(String s, int k) {
        Map<Integer, Map<Integer, Map<Character, Map<Integer, Integer>>>> dp = new HashMap<>();
        for(int y = 0; y <= k; y++){
            dp.put(y, new HashMap<>());
            for(int x = 0; x < s.length(); x++){
                char currChar = s.charAt(x);
                dp.get(y).put(x, new HashMap<>());
                if(x == 0){
                    dp.get(y).get(x).put(currChar, new HashMap<>());
                    dp.get(y).get(x).get(currChar).put(1, 1);
                    if(y > 0){
                        dp.get(y).get(x).put('#', new HashMap<>());
                        dp.get(y).get(x).get('#').put(0, 0);
                    }
                }else if(y == 0){
                    Map.Entry<Character, Map<Integer, Integer>> entry = dp.get(y).get(x - 1).entrySet().iterator().next();
                    char prevChar = entry.getKey();
                    Map<Integer, Integer> prevCharCount2Len = entry.getValue();
                    Map<Integer, Integer> currCharCount2Len = new HashMap<>();
                    int prevCharCount = prevCharCount2Len.entrySet().iterator().next().getKey();
                    int prevLen = prevCharCount2Len.entrySet().iterator().next().getValue();
                    if(currChar == prevChar){
                        int currCharCount = prevCharCount + 1;
                        currCharCount2Len.put(currCharCount, prevLen + (getEncodeLen(currCharCount) - getEncodeLen(prevCharCount)));
                    }else{
                        currCharCount2Len.put(1, prevLen + 1);
                    }
                    dp.get(y).get(x).put(currChar, currCharCount2Len);
                }else{
                    dp.get(y).get(x).put(currChar, new HashMap<>());
                    if(y > x){
                        dp.get(y).get(x).put('#', new HashMap<>());
                        dp.get(y).get(x).get('#').put(0, 0);
                    }
                    //not delete currChar
                    if(dp.get(y).get(x - 1).containsKey('#')){
                        dp.get(y).get(x).get(currChar).put(1, 1);
                    }
                    
                    for(Map.Entry<Character, Map<Integer, Integer>> entry: dp.get(y).get(x - 1).entrySet()){
                        char prevChar = entry.getKey();
                        Map<Integer, Integer> count2Len = entry.getValue();
                        if(prevChar == currChar){
                            for(Map.Entry<Integer, Integer> innerEntry: count2Len.entrySet()){
                                int count = innerEntry.getKey();
                                int len = innerEntry.getValue();
                                dp.get(y).get(x).get(prevChar).put(count + 1, len + getEncodeLen(count + 1) - getEncodeLen(count));
                            } 
                        }else{
                            for(Map.Entry<Integer, Integer> innerEntry: count2Len.entrySet()){
                                int count = innerEntry.getKey();
                                int currLen = innerEntry.getValue() + 1;
                                int prevLen = dp.get(y).get(x).get(currChar).getOrDefault(1, Integer.MAX_VALUE);
                                dp.get(y).get(x).get(currChar).put(1, Math.min(currLen, prevLen));
                            }
                        }
                    }
                    
                    //delete currChar
                    for(Map.Entry<Character, Map<Integer, Integer>> entry: dp.get(y - 1).get(x - 1).entrySet()){
                        char prevChar = entry.getKey();
                        Map<Integer, Integer> count2Len = entry.getValue();
                        for(Map.Entry<Integer, Integer> innerEntry: count2Len.entrySet()){
                            int count = innerEntry.getKey();
                            int prevLen = innerEntry.getValue();
                            dp.get(y).get(x).computeIfAbsent(prevChar, key -> new HashMap<>());
                            int currLen = dp.get(y).get(x).get(prevChar).getOrDefault(count, Integer.MAX_VALUE);
                            dp.get(y).get(x).get(prevChar).put(count, Math.min(currLen, prevLen));
                        }
                    }
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(Map.Entry<Character, Map<Integer, Integer>> entry: dp.get(k).get(s.length() - 1).entrySet()){
            Map<Integer, Integer> count2Len = entry.getValue();
            for(Map.Entry<Integer, Integer> innerEntry: count2Len.entrySet()){
                min = Math.min(min, innerEntry.getValue());
            }
        }
        return min;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aabbaa";
        int k = 2;
        System.out.println("s:" + s + ", k:" + k);
        System.out.println("min length:" + sol.getLengthOfOptimalCompression(s, k));
    }
}
