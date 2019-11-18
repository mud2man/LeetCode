/* Greedy: Time:O(n), Space:O(1).
 * 1. We maintain topAndSlopeLength = {the candy# of top, the kength of decending length}
 * 2. The minimum of ratings[0, i] is the minimum of ratings[0, i - 1] plus the minimum of ratings[i]
 * 3. We increase topAndSlopeLength[0] and topAndSlopeLength[1] by 1 if topAndSlopeLength[1] + 1 == topAndSlopeLength[0]
 * 4. Otherwise, topAndSlopeLength[1]++
 * 5. For other cases, we update topAndSlopeLength by {prevCandy, 0}(ratings[i] > ratings[i - 1]) or {1, 0}(ratings[i] == ratings[i - 1])
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public int candy(int[] ratings) {
        int count = 1;
        int[] topAndSlopeLength = {1, 0};
        int prevCandy = 1;
        for(int i = 1; i < ratings.length; ++i){
            if(ratings[i] > ratings[i - 1]){
                count += (prevCandy + 1);
                prevCandy++;
                topAndSlopeLength = new int[]{prevCandy, 0};
            }else if(ratings[i] < ratings[i - 1]){
                if(topAndSlopeLength[1] + 1 == topAndSlopeLength[0]){
                    count += (topAndSlopeLength[0] + 1);
                    topAndSlopeLength[0]++;
                    topAndSlopeLength[1]++; 
                }else{
                    count += (topAndSlopeLength[1] + 1);
                    topAndSlopeLength[1]++;
                }
                prevCandy = 1;
            }else{
                count++;
                topAndSlopeLength = new int[]{1, 0};
                prevCandy = 1;
            }
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] ratings = {5, 3, 2, 4};
        System.out.println("ratings: " + Arrays.toString(ratings));
        System.out.println("minimum amount of candies: " + sol.candy(ratings));
    }
}
