/* Use HashMap: O(n*m)
 * 1. Strore multiplee * n, where 0 < n < 10, into HashMap
 * 2. Use the data in HashMap to add
 * ex: 123 * 454 => HashMap=[4:492, 5:651]
 *     product = HashMap.get(4) + 10*HashMap.get(5) + 100*HashMap.get(4)
 */

import java.util.*;
import java.math.*;

public class Solution{
    public int[] simpleMultiply(int digit, int[] multiplee){
        int[] product;
        int carry;
        int a;
        int i;
        
        product = new int[multiplee.length + 1];
        
        carry = 0;
        for(i = 0 ; i < multiplee.length; ++i){
            a = multiplee[i] * digit + carry;
            product[i] = a % 10;
            carry = a / 10;
        }
        
        if(carry > 0){
            product[i] = carry;     
        }
        return product;
    }
    
    public void add(int shift, int[] adder, int[] sum){
        int carry;
        int i;
        int a;
        
        carry = 0;
        for(i = 0; i < adder.length; ++i){
            a = adder[i] + sum[shift + i] + carry;
            sum[shift + i] = a % 10;
            carry = a / 10;
        }
        
        if(carry > 0){
            sum[i + shift] = carry + sum[i + shift];     
        }
    }
    
    public String multiply(String num1, String num2) {
        int[] multiplee;
        int[] product; 
        int i;
        int digit;
        HashMap<Integer, int[]> subProducts;
        String productStr;
        int msbIdx;
        String multipleeStr;
        String multiplerStr;
        
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        
        productStr = new String("");
        subProducts = new HashMap<Integer, int[]>();
        product = new int[num1.length() + num2.length()];
        
        //multipler = num2, multiplee = num1
        if(num1.length() > num2.length()){
            multipleeStr = num1;
            multiplerStr = num2;
        }
        else{
            multipleeStr = num2;
            multiplerStr = num1;
        }
        
        multiplee = new int[multipleeStr.length()];
        for(i = 0; i < multipleeStr.length(); ++i){
            multiplee[i] = multipleeStr.charAt(multipleeStr.length() - i - 1) - '0';
        }
            
        for(i = 0; i < multiplerStr.length(); ++i){
            digit = multiplerStr.charAt(multiplerStr.length() - i - 1) - '0';
            if(digit == 0){
                continue;
            }
            if(!subProducts.containsKey(digit)){
                subProducts.put(digit, simpleMultiply(digit, multiplee));
            }
            add(i, subProducts.get(digit), product);
        }
        
        msbIdx = product.length - 1;
        while(product[msbIdx] == 0){
            --msbIdx;
        }
        
        for(i = msbIdx; i >=0; --i){
            productStr = productStr.concat(Integer.toString(product[i]));
        }
        
        return productStr;
    }

	public static void main(String[] args){
		Solution sol;
		String num1  = "123";
		String num2  = "454";
		String product  = "";

		sol = new Solution();
		
		System.out.println("num1: " + num1);
		System.out.println("num2: " + num2);
		System.out.println("product: " + sol.multiply(num1, num2));
	}
}
