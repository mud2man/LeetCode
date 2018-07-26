/* Heap: Time:O(nlogn), Space:O(n)
 * 1. Have class "Worker" and sort the list workers with unitWage = wage / quality
 * 2. Traverse the workers, and update the current wage sum given the current worker's unitWage
 * 3. Because workers is sorted by unitWage, the later work is the bottleneck compared the previous worker
 * 4. And we need to delete the worker with most quality (keep the information by maximum heap), since it's the heaviest factor
 * 5. The deleted heaviest worker will never in the optimal solution, after considering the rest bottleneck workers
 * 6. So we can just exclude it in every iteration
 */

import java.util.*;

public class Solution{
    private class Worker{
        int quality;
        int wage;
        double unitWage;
        Worker(int q, int w, double u){quality = q; wage = w; unitWage = u;}
    }
    
    private class UnitWageComparator implements Comparator<Worker>{
        public int compare(Worker x, Worker y){
            if(x.unitWage - y.unitWage >= 0){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
    
    private class MaxHeapComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return y - x;
        }
    }
    
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        List<Worker> workers = new ArrayList<>();
        for(int i = 0; i < quality.length; ++i){
            workers.add(new Worker(quality[i], wage[i], (double)wage[i] / (double)quality[i]));
        }
        Collections.sort(workers, new UnitWageComparator());
        
        int qualitySum = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new MaxHeapComparator());
        for(int i = 0; i < K; ++i){
            qualitySum += workers.get(i).quality;
            maxHeap.add(workers.get(i).quality);
        }
        double minWageSum = qualitySum * workers.get(K - 1).unitWage;
        
        for(int i = K; i < quality.length; ++i){
            qualitySum = qualitySum - maxHeap.poll() + workers.get(i).quality;
            double newWageSum = (double)(qualitySum) * workers.get(i).unitWage;
            minWageSum = (newWageSum < minWageSum)? newWageSum: minWageSum;
            maxHeap.add(workers.get(i).quality);
        }
        return minWageSum;
    }
  
    public static void main(String[] args){
        int[] quality = {10, 20, 5};
        int[] wage = {70, 50, 30};
        int K = 2;
        Solution sol = new Solution();

        System.out.println("quality:" + Arrays.toString(quality));
        System.out.println("wage:" + Arrays.toString(wage));
        System.out.println("K:" + K);
        System.out.println("minimum cost: " + sol.mincostToHireWorkers(quality, wage, K));
    }
}
