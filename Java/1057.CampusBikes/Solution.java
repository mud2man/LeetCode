/* Greedy plus Bucket Sort: Time:O(n*m), Space:O(n*m)
 * 1. Have "dis2WorkerBikePairs", and store the pair of worker and bike with the order(worker first and then bike)
 * 2. Iterate the distance from 1 to 2000, and iterate the pairs, since the pairs are arranged in order(worker first and then bike)
 * 3. Have "assignedWorkers" and "usedBikes" to remember the assigned worker and used bike 
 */

import java.util.*; // Stack

public class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer, List<int[]>> dis2WorkerBikePairs = new HashMap<>();
        for(int w = 0; w < workers.length; ++w){
            for(int b = 0; b < bikes.length; ++b){
                int dis = Math.abs(workers[w][0] - bikes[b][0]) + Math.abs(workers[w][1] - bikes[b][1]);
                dis2WorkerBikePairs.computeIfAbsent(dis, key -> new ArrayList<>()).add(new int[]{w, b});
            }
        }
        
        int[] assigned = new int[workers.length];
        Set<Integer> assignedWorkers = new HashSet<>();
        Set<Integer> usedBikes = new HashSet<>();
        for(int dis = 1; dis <= 2000 && assignedWorkers.size() < workers.length; ++dis){
            List<int[]> workerBikePairs = dis2WorkerBikePairs.getOrDefault(dis, new ArrayList<>());
            for(int[] workerBikePair: workerBikePairs){
                if(!assignedWorkers.contains(workerBikePair[0]) && !usedBikes.contains(workerBikePair[1])){
                    assigned[workerBikePair[0]] = workerBikePair[1];
                    assignedWorkers.add(workerBikePair[0]);
                    usedBikes.add(workerBikePair[1]);
                }   
            }
        }
        return assigned;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] workers = {{0, 0}, {1, 1}, {2, 0}};
        int[][] bikes = {{1 ,0}, {2, 2}, {2, 1}};
        System.out.println("workers");
        for(int[] worker: workers){
            System.out.println(Arrays.toString(worker));
        }
        System.out.println("bikes");
        for(int[] bike: bikes){
            System.out.println(Arrays.toString(bike));
        }
        System.out.println("assigned workers" + Arrays.toString(sol.assignBikes(workers, bikes)));
    }
}
