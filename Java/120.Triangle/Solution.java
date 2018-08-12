/* Dynamic programming: Time:O(n), Space:O(1), where n is the number of numbers in triangle
 * 1. Let triangle.get(y).get(x) be the minimum path sum ended on node triangle.get(y).get(x)
 * 2. triangle.get(y).get(x) = min(triangle.get(y - 1).get(x), triangle.get(y - 1).get(x - 1)) + triangle.get(y).get(x)
 * 3. Find the minimum on the last row of triangle
 */

import java.util.*;

public class Solution{
    public int minimumTotal(List<List<Integer>> triangle) {
        for(int y = 1; y < triangle.size(); ++y){
            for(int x = 0; x < triangle.get(y).size(); ++x){
                int min = Integer.MAX_VALUE;
                if(x < triangle.get(y - 1).size()){
                    min = Math.min(min, triangle.get(y - 1).get(x));
                }
                if(x > 0){
                    min = Math.min(min, triangle.get(y - 1).get(x - 1));
                }
                triangle.get(y).set(x, min + triangle.get(y).get(x));
            }
        }
        
        int min = Integer.MAX_VALUE;
        int lastRowIdx = triangle.size() - 1;
        for(int x = 0; x < triangle.get(lastRowIdx).size(); ++x){
            min = Math.min(min, triangle.get(lastRowIdx).get(x));
        }
        return min;
    }
 
    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        
        sol = new Solution();
        System.out.println("triangle: " + triangle);
        System.out.println("minimum path sum: " + sol.minimumTotal(triangle));
    }
}
