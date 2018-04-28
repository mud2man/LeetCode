/* Math: O(1), However Leetcode has a much shorter solution
 * 1. Get all the combinations of pointers by backtrack
 * 2. Check if the pointers are square by inner product, and distance
 */

import java.util.*;

public class Solution{
    private boolean helper(LinkedList<int[]> order){
        long[][] vectors = new long[4][];
        
        for(int i = 0; i < 4; ++i){
            int start = i;
            int end = (i + 1) % 4;
            vectors[i] = new long[]{(long)order.get(end)[0] - (long)order.get(start)[0], 
                                    (long)order.get(end)[1] - (long)order.get(start)[1]};
        }
        
        for(int i = 0; i < 4; ++i){
            int start = i;
            int end = (i + 1) % 4;
            long innerProduct = vectors[start][0] * vectors[end][0] + vectors[start][1] * vectors[end][1];
            long distanceStart = vectors[start][0] * vectors[start][0] + vectors[start][1] * vectors[start][1];
            long distanceEnd = vectors[end][0] * vectors[end][0] + vectors[end][1] * vectors[end][1];
            if(distanceStart != distanceEnd || innerProduct != 0){
                return false;
            }
        }
        return true;
    }
    
    private boolean backtrack(int[][] points, boolean[] used, LinkedList<int[]> order){
        if(order.size() == 4){
            return helper(order);
        }
        else{
            for(int i = 0; i < 4; ++i){
                if(used[i] == false){
                    order.add(points[i]);
                    used[i] = true;
                    if(backtrack(points, used, order) == true){
                        return true;
                    }
                    order.pollLast();
                    used[i] = false;
                }
            }
            return false;
        }
    }
    
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashSet<String> identies = new HashSet<String>();
        int[][] points = new int[4][];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        LinkedList<int[]> order = new LinkedList<int[]>();
        boolean[] used = new boolean[4];
        
        for(int i = 0; i < 4; ++i){
            String identity = Integer.toString(points[i][0]) + "#" + Integer.toString(points[i][1]);
            if(identies.contains(identity)){
               return false; 
            }
            else{
                identies.add(identity);
            }
        }
        
        return backtrack(points, used, order);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] p1 = {0, 0};
        int[] p2 = {1, 1};
        int[] p3 = {1, 0};
        int[] p4 = {0, 1};
        
        System.out.println("p1: " + Arrays.toString(p1));
        System.out.println("p2: " + Arrays.toString(p2));
        System.out.println("p3: " + Arrays.toString(p3));
        System.out.println("p4: " + Arrays.toString(p4));
        System.out.println("is square: " + sol.validSquare(p1, p2, p3, p4));
    }
}
