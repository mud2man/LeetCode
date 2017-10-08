/* Two pointers: O(n)
 * 1. Have two pointers, one is currCandidate, the other one is nextCandidate
 * 2. If nextCandidate reach to n, the we check if currCandidate is celebrity by isCelebrity
 * 
 * proof: Assume currCandidate = 0, nextCandidate = 3
 *               
 *    0   1   2   3   4   5
 *       /   /   / \____
 *      X   X   /   X   X
 *     /___/___/     \   \
 *    0   1   2   3   4   5
 *
 *    Because 0 knows 3, so 0 is not celebrity. And because 0 doesn't know 1, and 2, 1 and 2 are not celebrity, too.
 *    However, if there is a celebrity. Let's say it's 3. 3 is always can be reach by nextCandidate, because 2 is known by everyone.
 *    If 3 is the celebrity, then nextCandidate can be reach to the end, when currCandidate is 3.
 *    And on final step, we need us isCelebrity to check if 3 is known by everyone, and don't know others.
 */

import java.util.*;

public class Solution {
    public int[][] relationship = {{0, 0, 0, 1, 0, 0},
                                   {1, 0, 1, 1, 1, 1},
                                   {1, 0, 1, 1, 1, 1},
                                   {0, 0, 0, 0, 0, 0},
                                   {1, 1, 1, 1, 0, 1},
                                   {1, 1, 1, 1, 1, 0}};
    
    private boolean knows(int a, int b){
        return (relationship[a][b] == 1);  
    }

    private boolean isCelebrity(int candidate, int n){
        int followerCount = 0;
        for(int i = 0; i < n; ++i){
            if(i == candidate){
                continue;
            }
            else{
                if(knows(i, candidate)){
                    followerCount++;
                }
                if(knows(candidate, i)){
                    return false;
                }
            }
        }
        return (followerCount == (n - 1));
    }
    
    public int findCelebrity(int n) { 
        int currCandidate = 0;
        for(int nextCandidate = 1; nextCandidate < n; ++nextCandidate){
            if(knows(currCandidate, nextCandidate)){
                currCandidate = nextCandidate;
            }
        }

        return isCelebrity(currCandidate, n)? currCandidate: -1;
    }

    public static void main(String[] args){
        Solution sol;
        int n = 6;

        sol = new Solution();
        System.out.println("relationship: ");
        for(int[] row: sol.relationship){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("celebrity: " + sol.findCelebrity(n));
    }
}
