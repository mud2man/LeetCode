/* Time:O(nlogn), Space:O(n)
 * 1. Create a initial array matches = {1, 2, 3, ... n} 
 * 2. Merge matches until the number of matches is 1
 */

import java.util.*;

public class Solution{
    private ArrayList<String> merge(ArrayList<String> matches){
        ArrayList<String> mergedMatches = new ArrayList<String>();
        int size = matches.size();
        for(int i = 0; i < (size / 2); ++i){
            String mergedMatch = "(";
            mergedMatch += matches.get(i);
            mergedMatch += ",";
            mergedMatch += matches.get(size - i - 1);
            mergedMatch += ")";
            mergedMatches.add(mergedMatch);
        }
        return mergedMatches;
    }
    
    public String findContestMatch(int n) {
        ArrayList<String> matches = new ArrayList<String>();
        
        for(int i = 1; i <= n; ++i){
            matches.add(Integer.toString(i));
        }
        
        while(matches.size() > 1){
            matches = merge(matches);
        }
        
        return matches.get(0);
    }

    public static void main(String[] args){
        Solution sol;
        int n = 5;

        sol = new Solution();    
        System.out.println("n: " + n);
        System.out.println("matches: " + sol.findContestMatch(n));
    }
}
