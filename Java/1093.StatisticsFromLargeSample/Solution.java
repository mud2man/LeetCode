/* Math: Time:O(1), Space:O(1).
 * 1. We can determine min, max, mean and mode in the first round
 * 2. We do branch to get median given if the total number is even or odd 
 */

import java.util.*;

public class Solution {
    public double[] sampleStats(int[] count) {
        double sum = 0;
        int totoalNumber = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        int maxCount = 0;
        double mode = 0;
        for(int i = 0; i < 256; ++i){
            if(count[i] > 0){
                sum += i * count[i];
                totoalNumber += count[i];
                min = Math.min(min, i);
                max = Math.max(max, i);
                mode = (maxCount < count[i])? (double)i: mode;
                maxCount = Math.max(maxCount, count[i]);
            }
        }
        double mean = sum / (double)totoalNumber;
        
        if(totoalNumber % 2 == 0){
            int target = totoalNumber / 2;
            totoalNumber = 0;
            int idx = 0;
            while(totoalNumber < target){
                totoalNumber += count[idx++];
            }
            if(totoalNumber > target){
                return new double[]{min, max, mean, (double)(idx - 1), mode};
            }else{
                int left = idx - 1;
                while(count[idx] == 0){
                    idx++;
                }
                int right = idx;
                return new double[]{min, max, mean, (double)(left + right) / 2.0, mode};
            }
        }else{
            int target = totoalNumber / 2 + 1;
            totoalNumber = 0;
            int idx = 0;
            while(totoalNumber < target){
                totoalNumber += count[idx++];
            }
            return new double[]{min, max, mean, (double)(idx - 1), mode};
        }
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] count = {0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println("count: " + Arrays.toString(count));
        System.out.println("sample: " + Arrays.toString(sol.sampleStats(count)));
    }
}
