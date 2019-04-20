/* Monotonous: Time:O(n), Space: O(n), where n is the number of digit
 * 1. Transfered the digits of num to a list of pair = {digit, index}
 * 2. Traverse digits from MSB, and keep a monotonous increasing stack regarding digits 
 * 3. Have a "top", where top[0] > stack's laset element but top[0] < stack's second element
 * 4. Update top if current pair[0] >= top, and also pop stack while keeping step3
 * 5. Then swap top and the last element of stack
 *
 * ex: num = 987959
 * time[0]: stack = {{9, 0}}, top = null
 * time[1]: stack = {{9, 0}, {8, 1}}, top = null
 * time[2]: stack = {{9, 0}, {8, 1}, {7, 2}}, top = null
 * time[3]: stack = {{9, 0}, {8, 1}}, top = {9, 3}
 * time[4]: stack = {{9, 0}, {8, 1}}, top = {9, 3}
 * time[5]: stack = {{9, 0}, {8, 1}}, top = {9, 5}
 * time[6]: num = 997958
 */         

import java.util.*;

public class Solution {
    public int maximumSwap(int num) {
        LinkedList<Integer> digits = new LinkedList<>();
        while(num > 0){
            digits.addFirst(num % 10);
            num = num / 10;
        }
        
        int[] top = null;
        LinkedList<int[]> stack = new LinkedList<>();
        for(int i = 0; i < digits.size(); ++i){
            int[] pair = new int[]{digits.get(i), i};
            if(top == null){
                if(!stack.isEmpty() && stack.peekLast()[0] < pair[0]){
                    top = pair;
                } 
                else{
                    stack.add(pair);
                }
            }
            else{
                top = (pair[0] >= top[0])? pair: top;
            }

            int[] temp = null;
            while(top != null && !stack.isEmpty() && stack.peekLast()[0] < top[0]){
                temp = stack.pollLast();
            }
            if(temp != null){
                stack.add(temp);
            }
        }

        if(!stack.isEmpty() && top != null){
            digits.set(stack.peekLast()[1], top[0]);
            digits.set(top[1], stack.peekLast()[0]);
        }
        int swapedNum = 0;
        for(int digit: digits){
            swapedNum = swapedNum * 10 + digit;
        }
        return swapedNum; 
    }
 
    public static void main(String[] args){
        int num = 987959;
        Solution sol = new Solution();
        System.out.println("num before swap: " + num);
        System.out.println("nums after swap: " + sol.maximumSwap(num));
    }
}
