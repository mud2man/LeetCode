/* HashSet + sliding window: O(n) 
 * 1. Have a hashset onceDNAs storing the seen segment, twiceDNAs storing the answer
 * 2. Use sliding window to get the segment in the string "s" from left to right
 * 3. If the onceDNAs contains this segment, put it in twiceDNAs
 * 4. Otherwise, put it in onceDNAs
 */

import java.util.*;

public class Solution {
   public List<String> findRepeatedDnaSequences(String s) {
        Set<String> twiceDNAs = new HashSet<String>();
        Set<String> onceDNAs = new HashSet<String>();
        
        if(s.length() < 10){
            return new ArrayList(twiceDNAs);
        }
        
        StringBuilder slideWindow = new StringBuilder("?" + s.substring(0, 9));
        for(int idx = 9; idx < s.length(); ++idx){
            slideWindow.deleteCharAt(0);
            slideWindow.append(s.charAt(idx));
            String segment = slideWindow.toString();
            if(onceDNAs.contains(segment)){
                twiceDNAs.add(segment);
            }
            else{
                onceDNAs.add(segment);
            }
        }
        return new ArrayList(twiceDNAs);
    }
 
    public static void main(String[] args)
    {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        Solution sol;
        List<String> repeatedSeqs;
        
        System.out.print("string: ");
        System.out.println(s);

        sol = new Solution();
        repeatedSeqs = sol.findRepeatedDnaSequences(s);

        System.out.println("Repeated sub-sequences: ");
        System.out.println(repeatedSeqs);
        
    }
}
