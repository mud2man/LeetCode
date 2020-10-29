/* DFS: Time:O(n), Space:O(n)
 * 1. Construct parent2Children and use dfs to return the latency from any given root of sub-tree
 */

package main

import ("fmt")

func max(x, y int) int {
    if x > y {
        return x
    }
    return y
}

func dfs(parent int, parent2Children map[int][]int, informTime []int) int {
    if(parent2Children[parent] == nil){
        return 0
    }
    var children []int = parent2Children[parent]
    var latency = 0
    for _, child := range children {
        latency = max(latency, dfs(child, parent2Children, informTime) + informTime[parent])
    }
    return latency
}

func numOfMinutes(n int, headID int, manager []int, informTime []int) int {
    var parent2Children map[int][]int = make(map[int][]int)
    for subordinate := range manager {
        parent2Children[manager[subordinate]] = append(parent2Children[manager[subordinate]], subordinate);
    }
    return dfs(headID, parent2Children, informTime)
}

func main() {
    var n = 6
    var headID = 2
    var manager = []int{1, 2, 3, 4, 5, 6, -1}
    var informTime = []int{0, 0, 1, 0, 0, 0}
    fmt.Println("n:", n, "headId:", headID, "manager:", manager, "informTime:", informTime)
    fmt.Println("number of minutes:", numOfMinutes(n, headID, manager, informTime))
}
