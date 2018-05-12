/* Stack: Time:O(n), Space:O(n)
 * 1. Have a list remainAsteroids to keep the remaining asteriod
 * 2. If asteroids[i] > 0, add it to the end
 * 3. Otherwise, start crush operations
 */

import java.util.*;

public class Solution{
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> remainAsteroids = new LinkedList<Integer>();
        
        for(int asteroid: asteroids){
            if(asteroid > 0){
                remainAsteroids.add(asteroid);
            }
            else{
                while(!remainAsteroids.isEmpty() && remainAsteroids.peekLast() > 0){
                    if((-asteroid) > remainAsteroids.peekLast()){
                        remainAsteroids.pollLast();
                    }
                    else if ((-asteroid) == remainAsteroids.peekLast()){
                        remainAsteroids.pollLast();
                        asteroid = 0;
                        break;
                    }
                    else{
                        asteroid = 0;
                        break;
                    }
                }
                
                if(asteroid != 0){
                    remainAsteroids.add(asteroid);
                }
            }
        }
        
        int[] ret = new int[remainAsteroids.size()];
        int index = 0;
        for(int remainAsteroid: remainAsteroids){
            ret[index++] = remainAsteroid;
        }
        return ret;
    }

    public static void main(String[] args){
        Solution sol;
        int[] asteroids = {5, 10, -5};
        sol = new Solution();

        System.out.println("asteroids: " + Arrays.toString(asteroids));
        System.out.println("remaining: " + Arrays.toString(sol.asteroidCollision(asteroids)));
    }
}
