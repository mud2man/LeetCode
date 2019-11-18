/* Greedy and two pointers: Time:O(n), Space:O(n). However we can remove O(n) space easily
 * 1. Set the initial candy 1 for the first person candies[0]
 * 2. Find the longest decending slope starting from index "start" ending with index "end"
 * 3. Assume candies[0 ~ start - 1], its sum is already minimum. 
 * 4. We update candies[i] with max(candies[start], minimum candy of candies[start ~ end - 1]), which mantain 2 rules and keeps candies[0 ~ end - 1] minimum
 * 5. Set the initial candies for candies[end]
 *
 * ex: envelopes = {5, 3, 2, 4}
 *     loop[0]: dp = {1, 0, 0, 0}
 *     loop[1]: dp = {3, 2, 1, 2}
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public int candy(int[] ratings) { 
        int[] candies = new int[ratings.length];
        int start = 0;
        int sum = 0;
        candies[0] = 1;
        while(start < ratings.length){
            //find the decending slope and update candies[start]
            int end = start + 1;
            while(end < ratings.length && ratings[end] < ratings[end - 1]){
                ++end;
            }
            int candy = end - start;
            candies[start] = Math.max(candies[start], candy);
            sum += candies[start];
            
            // update the candies[i], where start < i < end
            ++start;
            while(start < end){
                candies[start++] = --candy;
                sum += candy;
            }
            
            //set the initial candies for candies[end]
            if(end < ratings.length){
                candies[end] = (ratings[end] > ratings[end - 1])? candies[end - 1] + 1: 1;
            }
        }
        return sum;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] ratings = {5, 3, 2, 4};
        System.out.println("ratings: " + Arrays.toString(ratings));
        System.out.println("minimum amount of candies: " + sol.candy(ratings));
    }
}
