/* Stack: Time:O(n), Space:O(n)
 * 1. Have a list remainAsteroids to keep the remaining asteriod
 * 2. If asteroids[i] > 0, add it to the end
 * 3. Otherwise, start crush operations
 */

import java.util.*;

public class Solution{
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> monotonousStack = new LinkedList<>();
        for(int asteroid: asteroids){
            if(asteroid > 0){
                monotonousStack.add(asteroid);
            }else{
                boolean crashed = false;
                while(!monotonousStack.isEmpty()){
                    if(monotonousStack.peekLast() < 0){
                        break;
                    }else{
                        if(monotonousStack.peekLast() > -asteroid){
                            crashed = true;
                            break;
                        }else if(monotonousStack.peekLast() == -asteroid){
                            monotonousStack.pollLast();
                            crashed = true;
                            break;
                        }else{
                            monotonousStack.pollLast();
                        }
                    }
                }
                if(!crashed){
                    monotonousStack.add(asteroid);
                }
            }
        }
        
        int[] ret = new int[monotonousStack.size()];
        for(int i = 0; i < ret.length; ++i){
            ret[i] = monotonousStack.pollFirst();
        }
        return ret;
    }
 
    public static void main(String[] args){
        int[] asteroids = {5, 10, -5};
        Solution sol = new Solution();
        System.out.println("asteroids: " + Arrays.toString(asteroids));
        System.out.println("remaining: " + Arrays.toString(sol.asteroidCollision(asteroids)));
    }
}
