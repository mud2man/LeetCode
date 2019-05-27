/* Hash: Time:O(n*m) Space:O(n*m), where n = string#, m = length of the longest string
 * 1. Find the identity of every string by shifting the first character to 'a'. e.g., "ba" => "az", "bcd" => "abc"
 * 2. Use hashmap to catagorize these strings by the identity
 */

import java.util.*;

public class Solution{
    private String getIdentity(String s){
        char firstChar = s.charAt(0);
        int shift = 26 - (firstChar - 'a');
        StringBuilder identity = new StringBuilder("");
        for(char c: s.toCharArray()){
            char identityChar = (char)((((c - 'a') + shift) % 26) + 'a');           
            identity.append(identityChar);
        }
        return identity.toString();
    }
    
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> shiftSequencesGroupMap = new HashMap<String, List<String>>();
        for(String s: strings){
            String identity = getIdentity(s);
            shiftSequencesGroupMap.putIfAbsent(identity, new ArrayList<String>());
            shiftSequencesGroupMap.get(identity).add(s);
        }
        
        List<List<String>> shiftSequencesGroup = new ArrayList<List<String>>();
        for (Map.Entry<String, List<String>> entry : shiftSequencesGroupMap.entrySet()) {
            List<String> shiftSequences = entry.getValue();
            shiftSequencesGroup.add(shiftSequences);
        }
        return shiftSequencesGroup;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        
        System.out.println("strings: " + strings);
        System.out.println("shifted strings: ");
        for(List<String> strList: sol.groupStrings(strings)){
            System.out.println(strList);
        }
    }
}
