/* Dynamic programming + monotonous stack: Time:O(n), Space:O(n). 
 * 1. Collect left indexes with highest but low than arr[i] for 0 <= i <= arr.length - 1 by using monotonous decreasing stack
 * 2. Collect right ones by visit arr reversingly
 * 3. Remeber the longest path in dp[i] for index i
 * 4. Call dfs to get the longest path starting from arr[i] and update max
 */

package main

import (
    "fmt"
    "math"
)

func getIdx2HighestLowIdxs(arr []int, start int, end int, shift int, d int) map[int][][2]int{
    decreaseStack := make([][2]int, 0, 0)
    idx2HighestLowIdxs := make(map[int][][2]int)
    for idx := start; idx != end; idx += shift {
        if(len(decreaseStack) > 0 && int(math.Abs(float64(decreaseStack[0][0] - idx))) > d){
            decreaseStack = decreaseStack[1:] //pop
        }
        for len(decreaseStack) > 0 && arr[idx] > decreaseStack[len(decreaseStack) - 1][1] {
            top := decreaseStack[len(decreaseStack) - 1]
            decreaseStack = decreaseStack[0:len(decreaseStack) - 1]
            if(idx2HighestLowIdxs[idx] == nil || idx2HighestLowIdxs[idx][0][1] < top[1]){
                idx2HighestLowIdxs[idx] = make([][2]int, 0, 0)
                idx2HighestLowIdxs[idx] = append(idx2HighestLowIdxs[idx], top)
            }else if(idx2HighestLowIdxs[idx][0][1] == top[1]){
                idx2HighestLowIdxs[idx] = append(idx2HighestLowIdxs[idx], top)
            }
        }
        decreaseStack = append(decreaseStack, [2]int{idx, arr[idx]})
    }
    return idx2HighestLowIdxs
}

func dfs(idx int, dp []int, idx2LeftHighestLowIdxs map[int][][2]int, idx2RightHighestLowIdxs map[int][][2]int) int{
    if(dp[idx] > 0){
        return dp[idx]
    }
    max := 1
    if(idx2LeftHighestLowIdxs[idx] != nil){
        for _,leftHighestLowIdx := range idx2LeftHighestLowIdxs[idx] {
            max = int(math.Max(float64(max), float64(dfs(leftHighestLowIdx[0], dp, idx2LeftHighestLowIdxs, idx2RightHighestLowIdxs) + 1)))
        }
    }
    if(idx2RightHighestLowIdxs[idx] != nil){
        for _,rightHighestLowIdx := range idx2RightHighestLowIdxs[idx] {
            max = int(math.Max(float64(max), float64(dfs(rightHighestLowIdx[0], dp, idx2LeftHighestLowIdxs, idx2RightHighestLowIdxs) + 1)))
        }
    }
    dp[idx] = max
    return max
}

func maxJumps(arr []int, d int) int {
    idx2LeftHighestLowIdxs := getIdx2HighestLowIdxs(arr, 0, len(arr), 1, d)
    idx2RightHighestLowIdxs := getIdx2HighestLowIdxs(arr, len(arr) - 1, -1, -1, d)
    dp := make([]int, len(arr), len(arr))
    max := 0
    for i := range arr {
        max = int(math.Max(float64(max), float64(dfs(i, dp, idx2LeftHighestLowIdxs, idx2RightHighestLowIdxs))))
    }
    return max
}

func main() {
    arr := []int{6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12}
    d := 2
    fmt.Println("arr:", arr)
    fmt.Println("d:", d)
    fmt.Println("max jumps:", maxJumps(arr, d))
}
