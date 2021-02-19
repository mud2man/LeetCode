/* Two pointers: Time:O(n*logn), Space:O(1)
 * 1. Sort "people" and match from left and right
 * 2. Pick left and right if people[left] + people[right] <= limit
 * 3. Otherwise, pick right and move right--;
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        int count = 0;
        while(left <= right){
            if(left == right){
                left++;
            }else{
                if(people[left] + people[right] <= limit){
                    left++;
                    right--;
                }else{
                    right--;
                }
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args){
        int[] people = {3, 2, 2, 1};
        int limit = 3;
        Solution sol = new Solution();
        System.out.println("people:"  + Arrays.toString(people) + ", limit:" + limit);
        System.out.println("min boats#:" + sol.numRescueBoats(people, limit));
    }
}
