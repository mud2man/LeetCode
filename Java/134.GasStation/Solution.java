/* Greedy: Time:O(n), Space:O(1)
 * 1. Because the car must pass through station#0
 * 2. Traverse back form station#0 to accumulating the fuel, and remember the station with the maximum fuel
 * 3. Assume the statition is maxIdx, which means the remaining fuel when the car starting from maxIdx and reaches station#0 has the maximum fuel 
 * 4. Start from station maxIdx, and check if fuel remains at every station
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int maxIdx = 0;
        int size = cost.length;
        for(int i = size - 1, saving = 0, maxSaving = 0; i >= 0; --i){
            saving = saving + gas[i] - cost[i];
            if(maxSaving < saving){
                maxSaving = saving;
                maxIdx = i;
            }
        }
        
        int start = maxIdx;
        for(int j = 0, saving = 0; j < size; ++j){
            saving = saving + gas[(start + j) % size] - cost[(start + j) % size];
            if(saving < 0){
                return -1;
            }
        }
        return start;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] gas = {4, 4, 2, 3};
        int[] cost = {5, 1, 3, 4};

        System.out.println("gas: " + Arrays.toString(gas));
        System.out.println("cost: " + Arrays.toString(cost));
        System.out.println("starting station#: " + sol.canCompleteCircuit(gas, cost));
    }
}
