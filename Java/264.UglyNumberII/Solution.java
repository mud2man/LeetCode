/* Use Dynamic Programing: O(nlgn)
 * 1. Caculate every ugly number from 0-th to n-th
 * 2. Assume the i-th number is x, let a = (x/2) + 1, b = (y/3) + 1, c = (z/5) + 1
 * 3. Find d, e, and f from 0-th to (n-1)-th, such that a > (the previous element of d)
 *	  and a <= d, deduce e and f using the same method
 * 4. Find d, e ,and f via binary search
 * 5. x = min(a*2, b*3, c*5)
 */

import java.util.*; // Stack

public class Solution{

	/* The group of ugly numbers */
	private List< Integer > uglys;
	
    Solution(){
		uglys = new ArrayList< Integer >();
		uglys.add(1);
		uglys.add(2);
		uglys.add(3);
		uglys.add(4);
		uglys.add(5);
	}

	public int binarySearch(int lb, int ub, int key) {
	
		int mb;

		if(ub <= (lb + 1)){
			if((uglys.get(lb) >= key) && (uglys.get(lb - 1) < key)){
				return uglys.get(lb);
			}
			
			if((uglys.get(ub) >= key) && (uglys.get(ub - 1) < key)){
				return uglys.get(ub);
			}
		}

		mb = (lb + ub) / 2;

		if((uglys.get(mb) >= key) && (uglys.get(mb - 1) < key)){
			return uglys.get(mb);
		}
		else if (uglys.get(mb - 1) >= key){
			return binarySearch(lb , mb - 1, key);
		}
		else{
			return binarySearch(mb , ub, key);
		}
	}

	public int min(int a, int b, int c){
		
		int min;

		min = (b < a)? b: a;
		min = (c < min)? c: min;

		return min;
	}

	public int nthUglyNumber(int n) {

		int idx;
		int key2;
		int key3;
		int key5;

		for(idx = 5; idx < n; idx++){
			key2 = (uglys.get(idx - 1) / 2) + 1;	
			key3 = (uglys.get(idx - 1) / 3) + 1;	
			key5 = (uglys.get(idx - 1) / 5) + 1;

			key2 = binarySearch(0, idx - 1, key2);
			key3 = binarySearch(0, idx - 1, key3);
			key5 = binarySearch(0, idx - 1, key5);
			
			uglys.add(min(key2*2, key3*3, key5*5));
		}

		return uglys.get(n-1);
	}

	public static void main(String[] args){

		int ugly;
		int nth;
		Solution sol;
		
		sol = new Solution();
		nth = Integer.parseInt(args[0]);
		ugly = sol.nthUglyNumber(nth);
		
		System.out.println(nth + "-th ugly: " +  ugly);
	}
}
