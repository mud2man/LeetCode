/* Stack: Time:O(n), Space:O(n)
 * 1. Have 2 pointers pushIdx, and popIdx
 * 2. In the loop, we check if the top of stack is equal to popped[popIdx]. If so we do pop
 * 3. Otherwise, we do push
 * 4. In the loop, either pop or push has to be happended
 */

import java.util.*;

public class Solution{
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int pushIdx = 0;
        int popIdx = 0;
        while(pushIdx < pushed.length || popIdx < popped.length){
            if(!stack.isEmpty() && stack.peekLast() == popped[popIdx]){
                stack.pollLast();
                popIdx++;
            }
            else{
                if(pushIdx < pushed.length){
                    stack.addLast(pushed[pushIdx++]);
                }
                else{
                   return false; 
                }
            }
        }
        return stack.isEmpty();
    }
  
    public static void main(String[] args){
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};
        Solution sol = new Solution();
        System.out.println("pushed: " + Arrays.toString(pushed));
        System.out.println("popped: " + Arrays.toString(popped));
        System.out.println("is Valid: " + sol.validateStackSequences(pushed, popped));
    }
}
