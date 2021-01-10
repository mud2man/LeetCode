
/* Greedy and UnionFind: Time:O(nlogn), Space:O(n)
 * 1. Have a list of edges = {house1, house2, cost}, where the edge of well is {house1, house2, cost}
 * 2. Sort edges by cost
 * 3. Use "hasWater" to record if node has water. i.e. hasWater[root] = true, means node root has water
 * 4. Ther are 2 cases for a valid edge: 1)edges[1] == edges[2] and !hasWater[root] 2)edges[1] != edges[2] and neither root0 nor root1 has water
 * 5. One valid edge can contribute one node to have water. So we find the first n valid edges
 * 6. The first n valid edges can have the minimum cost to distribute water
 */
package main

import ("fmt"
        "sort")

func getRoot(node int, roots []int) int {
    if(roots[node] == node){
        return node
    }
    roots[node] = roots[roots[node]] //compression
    return getRoot(roots[node], roots)
}

func minCostToSupplyWater(n int, wells []int, pipes [][]int) int {
    edges := make([][]int, 0, 0)
    for i,well := range wells {
        edges = append(edges, []int{i, i, well})
    }
    for _,pipe := range pipes {
        edges = append(edges, []int{pipe[0] - 1, pipe[1] - 1, pipe[2]})
    }
    sort.Slice(edges, func(i, j int) bool {
        return edges[i][2] < edges[j][2]
    })

    hasWater := make([]bool, n, n)
    ranks := make([]int, n, n)
    roots := make([]int, n, n)
    for i := 0; i < n; i++ {
        roots[i] = i
    }

    needWaterCount := n;
    cost := 0
    for i := 0; i <  len(edges) && needWaterCount > 0; i++ {
        if(edges[i][0] == edges[i][1]){
            root := getRoot(edges[i][0], roots)
            if(!hasWater[root]){
                hasWater[root] = true
                cost += edges[i][2]
                needWaterCount--
            }
        }else{
            root0 := getRoot(edges[i][0], roots)
            root1 := getRoot(edges[i][1], roots)
            if(root0 != root1 && (!hasWater[root0] || !hasWater[root1])){
                if(ranks[root0] >= ranks[root1]){
                    roots[root1] = root0
                    if(ranks[root0] == ranks[root1]){
                        ranks[root0]++
                    }
                    if(hasWater[root1]){
                        hasWater[root0] = true
                    }
                }else{
                    roots[root0] = root1
                    if(hasWater[root0]){
                        hasWater[root1] = true
                    }
                }
                cost += edges[i][2]
                needWaterCount--
            }
        }
    }
    return cost
}

func main() {
    n := 3
    wells := []int{1, 2, 2}
    pipes := [][]int{{1, 2, 1}, {2, 3, 1}}
    fmt.Println("n:", n, ", wells:", wells, "pipes:", pipes)
    fmt.Println("min cost:", minCostToSupplyWater(n, wells, pipes))
}
