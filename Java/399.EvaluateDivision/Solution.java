/* Floyd Warshall: Time:O(n^3), Space:O(n)
 * 1. Construct the graph indexMap and paths
 * 2. Apply Floyd Warshall algorithm to search all the paths between every pairs
 */         

import java.util.*;

public class Solution {
    private void floydWarshall(double[][] paths){
        for(int k = 0; k < paths.length; ++k){
            for(int i = 0; i < paths.length; ++i){
                for(int j = 0; j < paths.length; ++j){
                    if(paths[i][k] != -1.0 && paths[k][j] != -1.0){
                        paths[i][j] = paths[i][k] * paths[k][j];
                    }
                }
            }
        }
    }
    
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, Integer> indexMap = new HashMap<String, Integer>();
        int size = 0;
        for(String[] equation: equations){
            if(!indexMap.containsKey(equation[0])) {indexMap.put(equation[0], size++);}
            if(!indexMap.containsKey(equation[1])) {indexMap.put(equation[1], size++);}
        }
        
        double[][] paths = new double[size][size];
        for(int y = 0; y < size; ++y){
            for(int x = 0; x < size; ++x){
                paths[y][x] = (y == x)? 1.0: -1.0;
            }
        }
        
        for(int i = 0; i < equations.length; ++i){
            int source = indexMap.get(equations[i][0]);
            int destination = indexMap.get(equations[i][1]);
            paths[source][destination] = values[i];
            paths[destination][source] = 1 / values[i];
        }
        
        floydWarshall(paths);
        
        double[] results = new double[queries.length];
        for(int i = 0; i < queries.length; ++i){
            if(!indexMap.containsKey(queries[i][0]) || !indexMap.containsKey(queries[i][1])){
                results[i] = -1.0;
            }
            else{
                int source = indexMap.get(queries[i][0]);
                int destination = indexMap.get(queries[i][1]);
                results[i] = paths[source][destination];
            }
        }
        
        return results;
    }
     
    public static void main(String[] args){
        Solution sol;
        String[][] equations = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        
        sol = new Solution();

        System.out.println("equations: ");
        for(String[] equation: equations){
            System.out.println(Arrays.toString(equation));
        }

        System.out.println("values: " + Arrays.toString(values));

        System.out.println("queries: ");
        for(String[] query: queries){
            System.out.println(Arrays.toString(query));
        }

        System.out.println("results: " + Arrays.toString(sol.calcEquation(equations, values, queries)));
    }
}
