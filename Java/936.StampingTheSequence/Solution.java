/* Greedy + SlideWindow: Time:O(n^2*m), Space:O(1). We covered at least one char each time, and move window n times in each time. We spend O(m) to compare
 * 1. In the first round, we move window of size stamp.length() from left to right to map the target 
 * 2. Then move window back from right to left to see if we can cover sub string.
 * 3. We replace the covered char to '#', and we ignore '#' when we do "cover" method
 * 4. After the first round, we ignore '#' in cover method
 * 5. We give up if we cannot cover in the loop or cover all chars
 * 6. Reverse indexes, since we cover "target" in reversed order
 */

import java.util.*;


public class Solution{
    private int cover(String window, String stamp, boolean allowDontCare){
        int count = 0;
        for(int i = 0; i < window.length(); ++i){
            if((!allowDontCare && window.charAt(i) == '#') || (window.charAt(i) != '#' && window.charAt(i) != stamp.charAt(i))){
                return -1;
            }
            count += (window.charAt(i) != '#')? 1: 0;
        }   
        return count;
    }
    
    public int[] movesToStamp(String stamp, String target) {
        String falsePattern = "";
        for(int i = 0; i < stamp.length(); ++i){
            falsePattern += "#";
        }
        
        StringBuilder targetSb = new StringBuilder(target);
        List<Integer> indexes = new ArrayList<>();
        int coverageCount = 0;
        boolean allowDontCare = false;
        boolean covered = true;
        while(covered && coverageCount < targetSb.length()){
            covered = false;
            int[] starts = {0, targetSb.length() - stamp.length()};
            int[] offset = {1, -1};
            for(int i = 0; i < 2; ++i){ //go from left end, and then go from right end
                for(int j = starts[i]; j <= targetSb.length() - stamp.length() && j >= 0; j += offset[i]){
                    String window = targetSb.substring(j, j + stamp.length());
                    if(!window.equals(falsePattern)){
                        int count = cover(window, stamp, allowDontCare);
                        if(count > 0){
                            coverageCount += count;
                            for(int k = j; k < j + stamp.length(); ++k){
                                targetSb.setCharAt(k, '#');
                            }
                            indexes.add(j);
                            covered = true;
                        }
                    }
                }
                allowDontCare = true;
            }
        }
        int[] ret = new int[indexes.size()];
        for(int i = indexes.size() - 1; i >= 0; --i){
            ret[indexes.size() - 1 - i] = indexes.get(i);
        }
        return (coverageCount == targetSb.length())? ret: new int[0];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String stamp = "abca";
        String target = "aabcaca";
        System.out.println("stamp:" + stamp);
        System.out.println("target:" + target);
        System.out.println("indexes:" + Arrays.toString(sol.movesToStamp(stamp, target)));
    }
}
