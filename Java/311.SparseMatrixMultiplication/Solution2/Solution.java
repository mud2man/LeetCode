/* Map: Time:O(n*p*q), Space:O(n*p + p*q)
 * 1. Traverse all element of row y in A, and transform it to mapA
 * 2. Traverse all elements of column x in B, and transform it to mapB
 * 3. Only when both mapA.get(y) and mapB.get(x) are non-empty, do multiply
 * 4. Traverse the entry of row (mapA.get(y)), and do multiply when mapB.get(x) contains index 
 */

import java.util.*;

public class Solution{
    public int[][] multiply(int[][] A, int[][] B) {
        int lengthA = A.length;
        int widthA = A[0].length;
        int lengthB = B.length;
        int widthB = B[0].length;
        int[][] AB = new int[lengthA][widthB];
        List<Map<Integer, Integer>> mapA = new ArrayList<>();
        List<Map<Integer, Integer>> mapB = new ArrayList<>();
        
        for(int y = 0; y < lengthA; ++y){
            mapA.add(new HashMap<>());
            for(int x = 0; x < widthA; ++x){
                if(A[y][x] != 0){
                    mapA.get(y).put(x, A[y][x]);
                }
            }
        }
        for(int x = 0; x < widthB; ++x){
            mapB.add(new HashMap<>());
            for(int y = 0; y < lengthB; ++y){
                if(B[y][x] != 0){
                    mapB.get(x).put(y, B[y][x]);
                }
            }
        }  

        for(int y = 0; y < AB.length; ++y){
            for(int x = 0; x < AB[0].length; ++x){
                if(mapA.get(y).size() > 0 && mapB.get(x).size() > 0){
                    Map<Integer, Integer> row = mapA.get(y);
                    Map<Integer, Integer> col = mapB.get(x);
                    int val = 0;
                    for(Map.Entry<Integer, Integer> entry: row.entrySet()){
                        int index = entry.getKey();
                        val +=col.containsKey(index)? row.get(index) * col.get(index): 0;
                    }
                    AB[y][x] = val;
                }else{
                    AB[y][x] = 0;
                }
            }
        }
        return AB;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] A = {{1, 0, 0},
                     {-1, 0, 3}};
        int[][] B = {{7, 0, 0},
                     {0, 0, 0},
                     {0, 0, 1}};

        System.out.println("A[][]: ");    
        for(int i = 0; i < A.length; ++i){
            System.out.println(Arrays.toString(A[i]));
        }
        
        System.out.println("B[][]: ");    
        for(int i = 0; i < B.length; ++i){
            System.out.println(Arrays.toString(B[i]));
        }

        int[][] result = sol.multiply(A, B);
        System.out.println("result[][]: ");    
        for(int i = 0; i < result.length; ++i){
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
