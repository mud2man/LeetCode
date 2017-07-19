/* Backtrack:
 * 1. Pick up the first palindrome from left to right, and call "backtrack" again
 * 2. Take a string ababc as an example ex: a|babc, ab|abc, aba|bc ...
 * 3. Check if the substring is equal to to string, if yes, terminate; else go to step1
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    public boolean isPalindrome(String s){
        for(int i = 0; i < (s.length() / 2); ++i){
            if(s.charAt(i) != s.charAt(s.length() - i - 1)){
                return false;
            }
        }
        return true;
    } 
    
    public void backtrack(List<List<String>> partitions, List<String> partition, String s){
        if(s.length() == 0){
            partitions.add(partition);
            return;
        }
        for(int i = 1; i <= s.length(); ++i){
            String subS = s.substring(0, i);
            if(isPalindrome(subS)){
                List<String> childPartition = new ArrayList<String>(partition);
                childPartition.add(subS);
                backtrack(partitions, childPartition, s.substring(i));
            }
        }
    }
    
    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<List<String>>();
        backtrack(partitions, new ArrayList<String>(), s);
        return partitions;
    }
    
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aab";
        List<List<String>> partitions;
        
        System.out.println("s: " + s);
        partitions = sol.partition(s);
        System.out.println("partitions: ");
        for(List<String> partition: partitions){
            System.out.println(partition);
        }
    }
}
