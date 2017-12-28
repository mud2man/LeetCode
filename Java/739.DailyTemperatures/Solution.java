/* Hash: Time:O(n), Space:O(k), we can use monotonous stack to handle more general question 
 * 1. Construct a hash table temperatureToDays, where temperatureToDays[i] is the eariliest day have temperature i
 * 2. Traverse temperatures from the rightest position
 * 3. days[i] = the minimum day among temperatureToDays[j], where temprature < j < temperatureToDays.length
 * 
 * ex: temperatures = {73, 74, 75, 71, 69, 72, 76, 73}
 *     temperatureToDays =  {..., 4, max, 3, 5, 0, 1, 2, 6 ...}, where 4 is on the index 69
 */

import java.util.*;

public class Solution{
    public int[] dailyTemperatures(int[] temperatures) {
        int[] temperatureToDays = new int[101];
        
        for(int i = 0; i < temperatureToDays.length; ++i){
            temperatureToDays[i] = Integer.MAX_VALUE;
        }
        
        int[] days = new int[temperatures.length];
        for(int i = temperatures.length - 1; i >= 0; --i){
            int temprature = temperatures[i];
            temperatureToDays[temprature] = i;
            int minDay = Integer.MAX_VALUE;
            for(int j = temprature + 1; j < temperatureToDays.length; ++j){
                minDay = Math.min(minDay, temperatureToDays[j]);
            }
            days[i] = (minDay < Integer.MAX_VALUE)? (minDay - i): 0;
        }
        
        return days;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        
        System.out.println("temperatures:" + Arrays.toString(temperatures));
        System.out.println("days:" + Arrays.toString(sol.dailyTemperatures(temperatures)));
    }
}
