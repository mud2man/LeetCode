/* Use Dynamic Programing: O(n*m), m = primes.length
 * 1. Declare the pointer in the ugly number list, every pointer refer to the associated prime
 * 2. u(n) = min(u(ptr0)*p0, u(ptr1)*p1, ...)
 */

import java.util.*;

public class Solution{

	/* The set of ugly numbers and positions */
	private List< Integer > uglys;
	private List< Integer > ptrs;
	
    Solution(){
		uglys = new ArrayList< Integer >();
		ptrs = new ArrayList< Integer >();
	}
	
	public int nthSuperUglyNumber(int n, int[] primes) {
		int size;
		int i;
		int j;
		int min;
		int ptr;

		size = primes.length;

		for(i = 0; i < size; i++){
			ptrs.add(0);
		}
		uglys.add(1);

		for(i = 1; i < n; i++){

			/* Find the next ugly number */
			min = primes[0] * uglys.get(ptrs.get(0));

			for(j = 1; j < size; j++){
				min = Math.min(min, primes[j] * uglys.get(ptrs.get(j)));
			}

			uglys.add(min);

			/* Update all positions associated with all primes */
			for(j = 0; j < size; j++){
				if((uglys.get(ptrs.get(j)) * primes[j]) == min){
					ptr = ptrs.get(j);
					ptrs.set(j, ++ptr);
				}
			}
		}

		return uglys.get(n - 1);
	}

	public void dump() {
		System.out.println("uglys: ");
		System.out.println(uglys);
		System.out.println("ptrs: ");
		System.out.println(ptrs);
	}

	public static void main(String[] args){
		int i;
		Solution sol;
		int[] primes = {2, 7, 13, 19};
		

		System.out.println("Ugly numbers: ");

		for(i = 1; i < 20; i++){
			sol = new Solution();
			System.out.print(sol.nthSuperUglyNumber(i, primes) +", ");

			if(i%5 == 4){
				System.out.println();
			}
		}
	}
}
