/* Use hashMap and sorting: O(n)
 * 1. Create a hashMap "freqMap" to record the frequency of every character
 * 2. Sort the hashMap and output the answer string
 */

import java.util.*; // Stack

public class Solution {
    public String frequencySort(String s) {
        int i;
        char c;
        HashMap<Character, Integer> freqMap;
        List<Map.Entry<Character, Integer>> freqList;
        StringBuilder sb;
        
        freqMap = new HashMap<Character, Integer>();
        sb = new StringBuilder("");
        
        for(i = 0; i < s.length(); ++i){
            c = s.charAt(i);
            if(freqMap.containsKey(c)){
                freqMap.put(c, freqMap.get(c) + 1);
            }
            else{
                freqMap.put(c, 1);
            }
        }
        
        freqList = new LinkedList<Map.Entry<Character, Integer>>(freqMap.entrySet());
        Collections.sort(freqList, new Comparator<Map.Entry<Character, Integer>>()
                         {
                             public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2 )
                             {
                                 return (o2.getValue()).compareTo(o1.getValue());
                             }
                         } );
        
        for(Map.Entry<Character, Integer> entry: freqList){
            for(i = 0; i < entry.getValue(); ++i){
                sb.append(entry.getKey());
            }
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args){
		Solution sol;
		String s;

		s = "aAbb";
		sol = new Solution();
		
		System.out.println("before sorting: " + s);
		System.out.println("after sorting: " + sol.frequencySort(s));
	}
}
