/* Hash: Time:O(n*m*k), Space:O(n), where n = s length, m = word length, k = word number. Can use boolean array for a shorter answer
 * 1. Find all intervals and have a treemap to store the intervals
 * 2. Merge overlapped intervals and generate the final interval list "intervalsList"
 * 3. Generate the final bold tage string withe the "intervalsList"
 */

import java.util.*;

public class Solution{
    public String addBoldTag(String s, String[] dict) {
        TreeMap<Integer, Integer> intervalsMap = new TreeMap<Integer, Integer>();
        
        //find all intervals
        for(String word: dict){
            int fromIndex = 0;
            int startIndex = s.indexOf(word, fromIndex);
            while(startIndex != -1){
                int endIndex = -1;
                if(intervalsMap.containsKey(startIndex)){
                    endIndex = intervalsMap.get(startIndex);
                }
                intervalsMap.put(startIndex, Math.max(endIndex, startIndex + word.length() - 1));
                fromIndex = startIndex + 1;
                startIndex = s.indexOf(word, fromIndex);
            }
        }
        
        Map.Entry<Integer, Integer> firstEntry = intervalsMap.firstEntry();
        if(firstEntry == null){
            return s;
        }
        
        //merge overlapped intervals
        List<int[]> intervalsList = new ArrayList<int[]>();
        int[] interval = new int[]{firstEntry.getKey(), firstEntry.getValue()};
        for(Map.Entry<Integer, Integer> entry: intervalsMap.entrySet()){
            int start = entry.getKey();
            int end = entry.getValue();
            if(interval[1] >= (start - 1)){
                interval[1] = Math.max(interval[1], end);
            }
            else{
                intervalsList.add(interval);
                interval = new int[]{start, end};
            }
        }
        intervalsList.add(interval);
        
        //generare the final solution
        String boldTag = "";
        int start = 0;
        for(int[] i: intervalsList){
            boldTag += s.substring(start, i[0]);
            boldTag += "<b>";
            boldTag += s.substring(i[0], i[1] + 1);
            boldTag += "</b>";
            start = i[1] + 1;
        }
        
        if(start < s.length()){
            boldTag += s.substring(start, s.length());
        }
        
        return boldTag;
    }

    public static void main(String[] args){
        Solution sol;
        String[] dict = {"aaa","aab","bc"};
        String s = "aaabbcc";

        sol = new Solution();    
        System.out.println("dict: " + Arrays.toString(dict));
        System.out.println("s: " + s);
        System.out.println("bold string: " + sol.addBoldTag(s, dict));
    }
}
