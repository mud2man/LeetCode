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
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> indexMap = new HashMap<String, Integer>();
        int size = 0;
        for(List<String> equation: equations){
            indexMap.putIfAbsent(equation.get(0), size++);
            indexMap.putIfAbsent(equation.get(1), size++);
        }
        
        double[][] paths = new double[size][size];
        for(int y = 0; y < size; ++y){
            for(int x = 0; x < size; ++x){
                paths[y][x] = (y == x)? 1.0: -1.0;
            }
        }
        
        for(int i = 0; i < equations.size(); ++i){
            int source = indexMap.get(equations.get(i).get(0));
            int destination = indexMap.get(equations.get(i).get(1));
            paths[source][destination] = values[i];
            paths[destination][source] = 1 / values[i];
        }
        
        floydWarshall(paths);
        double[] results = new double[queries.size()];
        for(int i = 0; i < queries.size(); ++i){
            String source = queries.get(i).get(0);
            String destination = queries.get(i).get(1);
            if(!indexMap.containsKey(source) || !indexMap.containsKey(destination)){
                results[i] = -1.0;
            }
            else{
                results[i] = paths[indexMap.get(source)][indexMap.get(destination)];
            }
        }
        return results;
    }
 
    public static void main(String[] args){
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"), Arrays.asList("a", "a"));
        Solution sol = new Solution();
        System.out.println("equations: " + equations);
        System.out.println("values: " + Arrays.toString(values));
        System.out.println("queries: " + queries);
        System.out.println("results: " + Arrays.toString(sol.calcEquation(equations, values, queries)));
    }
}
