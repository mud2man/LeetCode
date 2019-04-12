/* Bucket sort: Time:O(N + A^2), Space:O(A)
 * 1. Habe "age2Count" to record the age-to-count mapping
 * 2. Traverse "age2Count" and accumulate "count"
 */

import java.util.*;

public class Solution{
    public int numFriendRequests(int[] ages) {
        int[] age2Count = new int[121];
        for(int age: ages){
            age2Count[age]++;
        }
        
        int count = 0;
        for(int age = 1; age < age2Count.length; ++age){
            if(age2Count[age] > 0){
                int lb = age / 2 + 7 + 1;
                count += (age >= lb)? age2Count[age] * (age2Count[age] - 1): 0;
                for(int i = lb; i < age; ++i){
                    count += age2Count[age] * age2Count[i];
                }
            }
        }
        return count;
    }
 
    public static void main(String[] args){
        int[] ages = {20, 30, 100, 100, 120};
        Solution sol = new Solution();
        System.out.println("ages: " + Arrays.toString(ages));
        System.out.println("friend requests#: " + sol.numFriendRequests(ages));
    }
}
