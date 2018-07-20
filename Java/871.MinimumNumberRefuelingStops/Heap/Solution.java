/* Heap: Time:O(nlogn), Space:O(n).
 * 1. Traverse stations, and pass by the station if remainFuel >= 0, and put the fuel volume into max heap
 * 2. Otherwise, poll the top from the max heap, until remain fuel >= 0, and put the fuel on the station into max heap
 * 3. After traverse, poll the fuel from heap. If we can reach the target, return the refuel count
 * 4. Otherwise, return -1
 */

import java.util.*;

public class Solution{
    private class MaxHeapComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return y - x;
        }
    }
    
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        
        int remainFuel = startFuel;
        int prevPosition = 0;
        int refuelCount = 0;
        for(int[] station: stations){
            remainFuel -= (station[0] - prevPosition);
            if(remainFuel >= 0){
                maxHeap.add(station[1]);
            }
            else{
                while(!maxHeap.isEmpty()){
                    if(remainFuel >= 0){
                        break;
                    }
                    remainFuel += maxHeap.poll();
                    refuelCount++;
                }
                
                if(remainFuel < 0){
                    return -1;
                }
                maxHeap.add(station[1]);
            }
            prevPosition = station[0];
        }
        
        remainFuel -= (target - prevPosition);
        while(!maxHeap.isEmpty()){
            if(remainFuel >= 0){
                return refuelCount;
            }
            remainFuel += maxHeap.poll();
            refuelCount++;
        }

        return (remainFuel >= 0)? refuelCount: -1;
    }
 
    public static void main(String[] args){
        int target = 100;
        int startFuel = 10;
        int[][] stations = {{10, 60}, {20, 30}, {30, 30},{60, 40}};
        Solution sol = new Solution();

        System.out.println("target:" + target + ", startFuel:" + startFuel);
        System.out.println("stations:");
        for(int[] station: stations){
            System.out.println(Arrays.toString(station));
        }
        System.out.println("minimum refuel: " + sol.minRefuelStops(target, startFuel, stations));
    }
}
