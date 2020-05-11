/* Stack: Time:O(n), Space:O(n)
 * 1. Store the pair (char, count) to stack while visiting each char from string s
 * 2. Accumulate top[1] when the current char is the same as top[0]
 * 3. Otherwise, mod top[1] by k pop top if top[1] == 0. After poping, redo if stack.peekLast()[0] == (c - 'a') or add new pair (char, 1) to stack
 * 4. Construct the string from stack 
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<int[]> stack = new LinkedList<>();
        stack.add(new int[]{-1, 0});
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            int[] top = stack.peekLast();
            if(top[0] == c - 'a'){
                top[1]++;
            }else{
                top[1] %= k;
                if(top[1] == 0){
                    stack.pollLast();
                }
                if(!stack.isEmpty() && stack.peekLast()[0] == (c - 'a')){
                    --i;
                }else{
                    stack.add(new int[]{c - 'a', 1});   
                }
            }
        }
        stack.peekLast()[1] %= k;
        StringBuilder sb = new StringBuilder("");
        while(!stack.isEmpty()){
            int[] top = stack.pollFirst();
            for(int i = 0; i < top[1]; ++i){
                sb.append((char)(top[0] + 'a'));
            }
        }
        return sb.toString();
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "deeedbbcccbdaa";
        int k = 3;
        System.out.println("s:" + s);
        System.out.println("k:" + k);
        System.out.println("after removed:" + sol.removeDuplicates(s, k));
    }
}
