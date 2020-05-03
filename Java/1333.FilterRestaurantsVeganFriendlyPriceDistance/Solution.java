/* Sort: Time:O(nlogn)
 * 1. Filter and sort
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> filteredIds = Arrays.stream(restaurants)
            .filter(s -> (veganFriendly == 0 || s[2] == 1) && s[3] <= maxPrice&& s[4] <= maxDistance)
            .sorted((x, y) -> ((x[1] != y[1])? y[1] - x[1]: y[0] - x[0]))
            .map(r -> r[0])
            .collect(Collectors.toList());
        return filteredIds;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int veganFriendly = 1;
        int maxPrice = 50;
        int maxDistance = 10;
        int[][] restaurants = {{1,4,1,40,10},{2,8,0,50,5},{3,8,1,30,4},{4,10,0,10,3},{5,1,1,15,1}};
        System.out.println("veganFriendly:" + veganFriendly);
        System.out.println("maxPrice:" + maxPrice);
        System.out.println("maxDistance:" + maxDistance);
        for(int[] restaurant: restaurants){
            System.out.println(Arrays.toString(restaurant));
        }
        System.out.println("filtered:" + sol.filterRestaurants(restaurants, veganFriendly, maxPrice, maxDistance));
    }
}
