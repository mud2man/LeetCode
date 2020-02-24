/* Map: Time:O(n), Space:O(m)
 * 1. Have a map "product2Price" and do discount when (i % n) == 0
 */

import java.util.*;

public class Cashier{
    int n;
    int discount;
    int[] products;
    int[] prices;
    Map<Integer, Integer> product2Price;
    int i;
    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = discount;
        this.i = 0;
        product2Price = new HashMap<>();
        for(int i = 0; i < products.length; ++i){
            product2Price.put(products[i], prices[i]);
        }
    }
    
    public double getBill(int[] product, int[] amount) {
        double cost = 0.0;
        for(int i = 0; i < product.length; ++i){
           cost += (double)(amount[i] * product2Price.get(product[i]));
        }
        i = (i + 1) % n;
        if(i == 0){
            cost = cost - cost * (double)discount / 100.0; 
        }
        return cost;
    }
  
    public static void main(String[] args){
        int n = 3;
        int discount = 50;
        int[] products = {1, 2, 3, 4, 5, 6, 7};
        int[] prices = {100, 200, 300, 400, 300, 200, 100};
        Cashier sol = new Cashier(n, discount, products, prices);
        System.out.println("n: " + n);
        System.out.println("discount: " + discount);
        System.out.println("products: " + Arrays.toString(products));
        System.out.println("prices: " + Arrays.toString(prices));
        System.out.println("cashier.getBill([1,2],[1,2]): " + sol.getBill(new int[]{1, 2}, new int[]{1, 2}));
        System.out.println("cashier.getBill([3,7],[10,10]): " + sol.getBill(new int[]{3, 7}, new int[]{10, 10}));
        System.out.println("cashier.getBill([1,2,3,4,5,6,7],[1,1,1,1,1,1,1]): " + sol.getBill(new int[]{1,2,3,4,5,6,7}, new int[]{1,1,1,1,1,1,1}));
        
    }
}
