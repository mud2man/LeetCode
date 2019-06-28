/* Floyd Warshall: Time:O(n^3), Space:O(n)
 * 1. Construct the graph indexMap and paths
 * 2. Apply Floyd Warshall algorithm to search all the paths between every pairs
 */         

import java.util.*;

public class Solution {
    private void floydWarshall(Double[][] dp){ 
        for(int i = 0; i < dp.length; ++i){
            for(int from = 0; from < dp.length; ++from){
                for(int to = 0; to < dp.length; ++to){
                    if(from == to){
                        continue;
                    }else{
                        if(dp[from][i] != null && dp[i][to] != null){
                            dp[from][to] = dp[from][i] * dp[i][to];
                            dp[to][from] = 1 / dp[from][to];
                        }
                    }
                }
            }
        }
    }
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int size = 0;
        Map<String, Integer> str2Index = new HashMap<>();
        for(List<String> equation: equations){
            String from = equation.get(0);
            String to = equation.get(1);
            str2Index.putIfAbsent(from, size++);
            str2Index.putIfAbsent(to, size++);
        }
        
        Double[][] dp = new Double[size][size];
        for(int i = 0; i < equations.size(); ++i){
            int fromIndex = str2Index.get(equations.get(i).get(0));
            int toIndex = str2Index.get(equations.get(i).get(1));
            dp[fromIndex][toIndex] = values[i];
            dp[toIndex][fromIndex] = 1 / values[i];
        }

        for(int i = 0; i < size; ++i){
            dp[i][i] = 1.0;
        }
        
        floydWarshall(dp);
        
        double[] answers = new double[queries.size()];
        for(int i = 0; i < queries.size(); ++i){
            if(!str2Index.containsKey(queries.get(i).get(0)) || !str2Index.containsKey(queries.get(i).get(1))){
                answers[i] = -1.0;
                continue;
            }else{
                int fromIndex = str2Index.get(queries.get(i).get(0));
                int toIndex = str2Index.get(queries.get(i).get(1));
                answers[i] =(dp[fromIndex][toIndex] != null)? dp[fromIndex][toIndex]: -1.0;
            }
        }
        return answers;
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
