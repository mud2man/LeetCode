/* Time:O(nlogn), Space:O(m), where n is the length of T, m is the length of S
 * 1. Have a map to map chracter to its position in S, and use it to compare
 */

import java.util.*;

public class Solution{
    private class Node{
        int position;
        char character;
        Node(int p, char c){position = p; character = c;}
    }
    
    private class CharComparator implements Comparator<Node>{
        public int compare(Node x, Node y){
            return x.position - y.position;
        }
    } 
    
    public String customSortString(String S, String T) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i < S.length(); ++i){
            map.put(S.charAt(i), i);
        }
        
        List<Node> tList = new ArrayList<Node>();
        for(int i = 0; i < T.length(); ++i){
            char c = T.charAt(i);
            if(map.containsKey(c)){
                tList.add(new Node(map.get(c), c));
            }
            else{
                tList.add(new Node(S.length(), c));
            }
        }
        
        Collections.sort(tList, new CharComparator());
        
        StringBuilder ret = new StringBuilder("");
        for(int i = 0; i < tList.size(); ++i){
            ret.append(tList.get(i).character);   
        }
        return ret.toString();
    }
  
    public static void main(String[] args){
        Solution sol;
        String S = "cba";
        String T = "abcd";
        sol = new Solution();
        
        System.out.println("S: " + S);
        System.out.println("T: " + T);
        System.out.println("after rearranged: " + sol.customSortString(S, T));
    }
}
