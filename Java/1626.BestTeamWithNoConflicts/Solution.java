/* DynamicProgramming: Time:O(n * n), Space:O(n)
 * 1. Sort by age and then by score
 * 2. ageAndScores[i][2] = max(ageAndScores[j][2] + ageAndScores[i][1], ...)
 * 3. Return the max of ageAndScores[i][2], where 0 <= i < n
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int[][] ageAndScores = new int[scores.length][3];
        for(int i = 0; i < scores.length; i++){
            ageAndScores[i][0] = ages[i];
            ageAndScores[i][1] = scores[i];
        }
        Arrays.sort(ageAndScores, (x, y) -> ((x[0] != y[0])? (y[0] - x[0]) : (y[1] - x[1])));
        int max = 0;
        for(int i = 0; i < ageAndScores.length; i++){
            int sum = ageAndScores[i][1];
            for(int j = i - 1; j >= 0; j--){
                if(!(ageAndScores[i][0] < ageAndScores[j][0] && ageAndScores[i][1] > ageAndScores[j][1])){
                    sum = Math.max(sum, ageAndScores[j][2] + ageAndScores[i][1]);
                }
            }
            ageAndScores[i][2] = sum;
            max = Math.max(max, ageAndScores[i][2]);
        }
        return max;
    }
  
    public static void main(String[] args){
        int[] scores = {1, 3, 5, 10, 15};
        int[] ages = {1, 2, 3, 4, 5};
        Solution sol = new Solution();
        System.out.println("scores:" + Arrays.toString(scores) + ", ages:" + Arrays.toString(ages));
        System.out.println("best score:" + sol.bestTeamScore(scores, ages));
    }
}
