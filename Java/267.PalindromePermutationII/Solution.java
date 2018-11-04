/* Backtrack: Time:O((n / 2)!), Space:O(n)
 * 1. Construct a map with key is char and value is count
 * 2. Do sanity checks as step3 and 4
 * 3. If s.length() is even, all the counts must be even
 * 4. If s.length() is odd, only one count must be odd
 * 5. Get the left part of palindrome, and the the middile character if it exist
 * 6. Do permutation of the left part
 * 7. In termination, append the mirror of left part and append the palindrome as left + m + right
 */         

import java.util.*;

public class Solution {
    private void permutate(Deque<Character> remain, String m, List<String> ps, String path){
        if(remain.isEmpty()){
            StringBuilder right = new StringBuilder(path);
            ps.add(path + m + right.reverse());
            return;
        }
        
        char prev = (char)(remain.peekFirst() - 1);
        int size = remain.size();
        for(int i = 0; i < size; ++i){
            char head = remain.pollFirst();
            if(head != prev){
                permutate(remain, m, ps, path + String.valueOf(head));
                prev = head;
            }
            remain.add(head);
        }
    }
    
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }
        
        StringBuilder pool = new StringBuilder("");
        String m = "";
        boolean sawOdd = false;
        for(Map.Entry<Character, Integer>entry: map.entrySet()){
            int count = entry.getValue();
            if(count % 2 == 1 && s.length() % 2 == 0){
                return new ArrayList<>();
            }
            else if(count % 2 == 1 && s.length() % 2 == 1){
                if(sawOdd == true){
                    return new ArrayList<>();
                }
                sawOdd = true;
                m = String.valueOf(entry.getKey());
            }
            
            for(int j = 0; j < count / 2; ++j){
                pool.append(entry.getKey());
            }
        }
        
        if(s.length() % 2 == 1 && !sawOdd){
            return new ArrayList<>();
        }
        
        List<Character> remain = new LinkedList<>();
        for(int i = 0; i < pool.length(); ++i){
            remain.add(pool.charAt(i));
        }
        Collections.sort(remain);
        List<String> ps = new ArrayList<>();
        permutate(new LinkedList<>(remain), m, ps, "");
        return ps;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aabb";
        System.out.println("s: " + s);
        System.out.println("palindromes: " + sol.generatePalindromes(s));
    }
}
