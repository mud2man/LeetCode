/* Math: O(n)
 * 1. Outer dot value can evaluate the direction
 * 2. Caculate every outer dot for every angle, and determine if this current direction consistent with the previous directions
 */

import java.util.*; // Stack

public class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        int[] vector0 = new int[2];
        int[] vector1 = new int[2];
        int prevDirection = 0;
        
        for(int index = 2; index < (points.size() + 2); ++index){
            int currIndex = index % points.size();
            int prevIndex = (index - 1) % points.size();
            int secondPrevIndex = (index - 2) % points.size();
            
            vector0[0] = points.get(prevIndex).get(1) - points.get(secondPrevIndex).get(1);
            vector0[1] = points.get(prevIndex).get(0) - points.get(secondPrevIndex).get(0);
            vector1[0] = points.get(currIndex).get(1) - points.get(prevIndex).get(1);
            vector1[1] = points.get(currIndex).get(0) - points.get(prevIndex).get(0);
            int outerDot = vector0[0] * vector1[1] - vector0[1] * vector1[0];
            
            int currentDirection;
            if(outerDot > 0){
                currentDirection = 1;
            }
            else if(outerDot < 0){
                currentDirection = -1;
            }
            else{
                currentDirection = 0;
            }
            
            if(prevDirection == 0){
                prevDirection = currentDirection;
            }
            else{
                if(currentDirection != 0){
                    if(prevDirection != currentDirection){
                        return false;
                    }
                }
            }
        }
        return true;    
    }

    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> points;
        
        sol = new Solution();
        points = new ArrayList<List<Integer>>();

        points.add(new ArrayList<Integer>(Arrays.asList(0, 0)));
        points.add(new ArrayList<Integer>(Arrays.asList(0, 10)));
        points.add(new ArrayList<Integer>(Arrays.asList(10, 10)));
        points.add(new ArrayList<Integer>(Arrays.asList(10, 0)));
        points.add(new ArrayList<Integer>(Arrays.asList(5, 5)));
        
        for(List<Integer> point: points){
            System.out.print("(" + point.get(0) + ", " + point.get(1) + "), ");
        }
        System.out.println("");

        System.out.println("isConvex: " + sol.isConvex(points));
    }
}
