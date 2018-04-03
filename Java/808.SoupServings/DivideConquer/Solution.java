/* Divide and Conquer: Time:O(4^n) Space:O(4^n)
 */

import java.util.*; // Stack

public class Solution {
    private double helper(int a, int b, HashMap<String, Double> map){
        if(a <= 0 && b <= 0){
            return 0.5;
        }
        else if(a <= 0){
            return 1.0;
        }
        else if(b <= 0){
            return 0.0;
        }
        
        String key = Integer.toString(a) + "," + Integer.toString(b);
        if(map.containsKey(key)){
            return map.get(key); 
        }
        
        double prob = 0; 
        prob += 0.25 * helper(a - 100, b, map);
        prob += 0.25 * helper(a - 75, b - 25, map);
        prob += 0.25 * helper(a - 50, b - 50, map);
        prob += 0.25 * helper(a - 25, b - 75, map);
        map.put(key, prob);
        return prob;
    }
    
    public double soupServings(int N) {
        if(N == 0){
            return 0.5;
        }
        else if(N >= 5551){
            return 1.0;
        }
        else{
            return helper(N, N, new HashMap<String, Double>());
        }
    }

    public static void main(String[] args){
        int N = 50;;
        Solution sol;
        sol = new Solution();
        System.out.println("N: " + N);
        System.out.println("Probability: " + sol.soupServings(N));
    }
}
