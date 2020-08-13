/* Monotonous stack: Time:O(n), Space:O(n)
 * 1. Construct monotonous-decreasing stack from left
 * 2. Pick the end point A[i] from right and poll stack until the top of stack is larger than A[i]
 * 3. Update maxWidth while polling stack
 */     

import java.util.*; // Stack

public class Solution {
    public int maxWidthRamp(int[] A) {
        Deque<int[]> monotonousStack = new LinkedList<>();
        for(int i = 0; i < A.length; ++i){
            if(monotonousStack.isEmpty() || monotonousStack.peekLast()[0] > A[i]) {
                monotonousStack.add(new int[]{A[i], i});
            }
        }
        
        int maxWidth = 0;
        for(int i = A.length - 1; i >= 0; --i){
            while(!monotonousStack.isEmpty() && monotonousStack.peekLast()[0] <= A[i]){
                maxWidth = Math.max(maxWidth, i - monotonousStack.pollLast()[1]);
            }
        }
        return maxWidth;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {6, 0, 8, 2, 1, 5};
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("max width:" + sol.maxWidthRamp(A));
    }
}
