/* Greedy: Time:O(n), Space:O(n)
 * 1. Delete sb.charAt(index) if sb.charAt(index + 1) < sb.charAt(index)g
 * 2. Delete the the remaining digits from the back of sb
 * 3. Delete the leading zeors, if they exist
 * 4. We can prove by we can always get a smaller number by deleting the first decending digit, rather than others. So we know the first decending digit must be deleted
 */

import java.util.*; // Stack

public class Solution {
    public String removeKdigits(String num, int k) {
        // Delete sb.charAt(index) if sb.charAt(index + 1) < sb.charAt(index)
        Deque<Character> digits = new LinkedList<>();
        digits.add('0');
        for(Character c: num.toCharArray()){
            while(k > 0 && digits.peekLast() > c){
                digits.pollLast();
                --k;
            }
            digits.add(c);
        }
  
        // Delete the the remaining digits from the back of sb
        while(k > 0 && !digits.isEmpty()){
            digits.pollLast();
            --k;
        } 
        // Delete the leading zeors, if they exist
        while(!digits.isEmpty() && digits.peekFirst() == '0'){
            digits.pollFirst();
        }
        String reducedNum = "";
        for(Character c: digits){
            reducedNum += Character.toString(c);
        }
        return (reducedNum.length() == 0)? "0": reducedNum;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String num = "1432219";
        String rNum;
        int k = 3;
        System.out.println("num: " + num);
        System.out.println("rNum: " + sol.removeKdigits(num, k));
	}
}
