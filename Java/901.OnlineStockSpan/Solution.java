/* Monotonic Stack: Time:O(n), Space:O(n)
 * 1. Keep a stack with decreasing order in terms of price, and the elemnt is {price, count}
 * 2. When a new price coming, pop and merger top until the price of top is higher than the current proce
 */

import java.util.*;

public class Solution{
    Deque<int[]> prices;
    
    public Solution() {
        prices = new LinkedList<>();
    }
    
    public int next(int price) {
        int[] newTop = {price, 1};
        while(!prices.isEmpty()){
            int[] top = prices.peekLast();
            if(top[0] <= newTop[0]){
                prices.pollLast();
                newTop[1] += top[1];
            }
            else{
                break;
            }
        }
        prices.add(newTop);
        return newTop[1];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        
        System.out.println("prices[]: " + Arrays.toString(prices));
        for(int price: prices){
            System.out.println("span: " + sol.next(price));
        }   
    }
}
