/* Math: O(1), However Leetcode has a much shorter solution
 * 1. Sort points by x axis first, and then y axisk
 * 2. If it's a square, diagnoals must be orthogonal, diagnoals have same lengths, 4 edges have samae lengths and length > 0
 */

import java.util.*;

public class Solution{
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] points = new int[][]{p1, p2, p3, p4};
        Arrays.sort(points, (x, y) -> {return (x[1] != y[1])? x[1] - y[1]: x[0] - y[0];});
        int[] diagnolVector1 = {points[3][0] - points[0][0], points[3][1] - points[0][1]};
        int[] diagnolVector2 = {points[2][0] - points[1][0], points[2][1] - points[1][1]};
        //check if two diagnoals orthogonal
        if((diagnolVector1[0] * diagnolVector2[0] + diagnolVector1[1] * diagnolVector2[1]) != 0){ 
            return false;
        //check if two diagnoals have same length
        }else if((diagnolVector1[0] *diagnolVector1[0] + diagnolVector1[1] * diagnolVector1[1]) != 
                 (diagnolVector2[0] *diagnolVector2[0] + diagnolVector2[1] * diagnolVector2[1])){
            return false;
        }else{
            int[][] edges = new int[4][2];
            edges[0] = new int[]{points[0][0] - points[1][0], points[0][1] - points[1][1]};
            edges[1] = new int[]{points[1][0] - points[3][0], points[1][1] - points[3][1]};
            edges[2] = new int[]{points[3][0] - points[2][0], points[3][1] - points[2][1]};
            edges[3] = new int[]{points[2][0] - points[0][0], points[2][1] - points[0][1]};
            int[] lengths = new int[4];
            lengths[0] = edges[0][0] * edges[0][0] + edges[0][1] * edges[0][1];
            lengths[1] = edges[1][0] * edges[1][0] + edges[1][1] * edges[1][1]; 
            lengths[2] = edges[2][0] * edges[2][0] + edges[2][1] * edges[2][1]; 
            lengths[3] = edges[3][0] * edges[3][0] + edges[3][1] * edges[3][1];
            return (lengths[0] == lengths[1] && lengths[1] == lengths[2] && 
                    lengths[2] == lengths[3] && lengths[3] == lengths[0] && lengths[0] > 0);
        }
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
