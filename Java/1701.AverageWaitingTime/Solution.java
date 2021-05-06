/* Array: Time:O(n) Space:O(1)
 * 1. Scan customers from left and keep updating chiefAvailableTime and accumulate totalMins
 * 2. Return totalMins / (double)customers.length
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    public double averageWaitingTime(int[][] customers) {
        double totalMins = 0;
        int chiefAvailableTime = customers[0][0];
        for(int[] customer: customers){
            if(chiefAvailableTime > customer[0]){
                totalMins += (double)(chiefAvailableTime - customer[0]);
                chiefAvailableTime += customer[1];
            }else{
                chiefAvailableTime = customer[0] + customer[1];
            }
            totalMins += customer[1];
        }
        return totalMins / (double)customers.length;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] customers = {{1, 2}, {2, 5}, {4, 3}};
        System.out.println("customers:");
        for(int[] customer: customers) {
            System.out.print(Arrays.toString(customer) + ", ");
        }
        System.out.println("\navg waiting time:" + sol.averageWaitingTime(customers));
    }
}
