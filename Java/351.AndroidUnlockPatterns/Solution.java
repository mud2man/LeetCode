/* Backtracking + Hash Table: O(#subpath)
 * 1. Use a set of prime number primes = {1, 2, 3, 5, 7, 9, 11, 13, 17, 19}, to generate the key of hash table
 * 2. The hash table store constraints, i.e. (constraint of 1 and 3) = 2, (constraint of 1 and 9) = 5,...
 * 3. The hash table = [primes[1]*primes[3]=10: 2, primes[1]*primes[9]=38: 5,...]
 * 4. Use path[] to record if the position visited
 * 5. Use the above data structure to backtrack all possible
 * 6. Since the matrix is symmatric, we can only visit position 1, position 2, and position 5
 * 7. Total count = (count(position 1) + count(position 2)) * 4 + count(position 5)
 */

import java.util.*;
public class Solution{
    private int[] primes = {1, 2, 3, 5, 7, 9, 11, 13, 17, 19};
    private int count;
    private HashMap<Integer, Integer> constraints;
    
    public void helper(int[] path, int m, int n, int currPos){
        int constraint;
        int nextPos;
        int size;
        int i;
        
        size = path[0];
        
        if(size >= m){
            count++;
        }
        
        if(size == n){
            return;
        }
        
        for(i = 1; i < 10; ++i){
            if(path[i] == 1){
                continue;
            }
            
            nextPos = i;
            constraint = -1;

            if(constraints.containsKey(primes[currPos] * primes[nextPos])){
                constraint = constraints.get(primes[currPos] * primes[nextPos]);
            }
            
            if((constraint == -1) || (path[constraint] == 1)){
                path[nextPos] = 1;
                path[0]++;
                helper(path, m, n, nextPos);
                path[nextPos] = 0;
                path[0]--;
            }
        }
    }
    
    public int numberOfPatterns(int m, int n) {
        int[] path;
        int i;
        
        path = new int[10];
        constraints = new HashMap<Integer, Integer>();
        
        //Build the constraint map
        constraints.put(10, 2);
        constraints.put(26, 4);
        constraints.put(38, 5);
        constraints.put(51, 5);
        constraints.put(65, 5);
        constraints.put(95, 6);
        constraints.put(77, 5);
        constraints.put(247, 8);
        
        for(i = 0 ; i < 10; i++){
            path[i] = 0;
        }
        
        count = 0;
        for(i = 1; i <= 2; ++i){
            path[i] = 1;
            path[0]++;
            helper(path, m, n, i);
            path[i] = 0;
            path[0]--;
        }
        
        count = count*4 ;
        
        path[5] = 1;
        path[0]++;
        helper(path, m, n, 5);
        path[5] = 0;
        path[0]--;
        
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int n;
        int m;

        sol = new Solution();
        m = 1;
        n = 1;

        System.out.println("#path: " + sol.numberOfPatterns(m, n) + ", min:" + m +", max:" + n);
    }
}
