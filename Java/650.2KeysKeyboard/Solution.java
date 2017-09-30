/* Math: O(n)
 * 1. Assume there is optimal solution, and the minimum steps is k = min_steps(a0) + min_steps(a1), where n = a0 * a1
 * 2. Then a1 must be a prime number, otherwise we can find a smaller number of steps to render a1.
 * 3. For example, assume a1 = 6, min_steps(6) = 6, however min_steps(2 * 3) = 2*1 + 3*1 = 5
 * 4. Therefore, the minimum solutiom comes from factorized result 
 * 5. Now assume n can be factorized as 2 * (3^2) * (5^3)
 * 6. The minimum number of steps is 2*1 + 3*2 + 5*3 = 24, because it equals to min_steps(2) + min_steps((3^2) * (5^3))
 * 7. And the order doesn't matter,  min_steps(2 * (3^2) * (5^3) = min_steps((3^2) * (5^3 * 2)
 */

import java.util.*;


public class Solution{
    public int minSteps(int n) {
        int stepNum = 0;
        
        int factor = 2;
        while(n > 1){
            int count = 0;
            while(n % factor == 0){
                count++;
                n = n / factor;
            }
            stepNum = stepNum + factor * count;
            factor++;
        }
        
        return stepNum;
    }   

    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 3;
        
        System.out.println("n: " + n);
        System.out.println("minimum number of steps: " + sol.minSteps(n));
    }
}
