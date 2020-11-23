/* Greedy: Time:O(n), Space:O(1)
 * 1. Get "char2Counts" and sort counts
 * 2. Travers the sorted counts and correct the duplicated to the nearest available count by subtracing at most 26
 * 3. Assume there are two number a and b need to be corrected, the deletion number is the same between (a -> c) & (a -> d) and (a -> d)&(b -> c)
 */

import java.util.*; // Stack


public class Solution {
    public int minDeletions(String s) {
        int[] char2Counts = new int[26];
        for(char c: s.toCharArray()){
            char2Counts[c - 'a']++;
        }
        
        List<Integer> counts = new ArrayList<>();
        for(int count: char2Counts){
            if(count > 0){
                counts.add(count);
            }
        }
        Collections.sort(counts);
        Set<Integer> used = new HashSet<>();
        int deleteNum = 0;
        for(int count: counts){
            if(used.contains(count)){
                boolean isFound = false;
                for(int i = 1; i < 26 && count - i > 0; i++){
                    if(!used.contains(count - i)){
                        deleteNum += i;
                        count = count - i;
                        isFound = true;
                        break;
                    }
                }
                deleteNum += isFound? 0: count;
            }
            used.add(count);
        }
        return deleteNum;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "ceabaacb"; 
        System.out.println("s:" + s);
        System.out.println("minimun deletions:" + sol.minDeletions(s));
    }
}
