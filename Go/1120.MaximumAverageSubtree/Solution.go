/* DFS: Time:O(n), Space:O(h)
 * 1. Visit nodes with "dfs" where return count-sum pair and update maxAvg
 */

package main

import (
    "fmt"
    "math"
)

type TreeNode struct {
    Val int
    Left *TreeNode
    Right *TreeNode
}

func dfs(maxAvg *float64, root *TreeNode) (int, int) {
    if(root == nil){
        return 0, 0
    }
    currCount, currSum := 1, root.Val
    leftCount, leftSum := dfs(maxAvg, root.Left)
    currCount += leftCount
    currSum += leftSum
    rightCount, rightSum := dfs(maxAvg, root.Right)
    currCount += rightCount
    currSum += rightSum
    *maxAvg = math.Max(*maxAvg, float64(currSum) / float64(currCount))
    return currCount, currSum
}

func maximumAverageSubtree(root *TreeNode) float64 {
    maxAvg := 0.0
    dfs(&maxAvg, root)
    return maxAvg
}

func main() {
    root := TreeNode{Val:5}
    root.Left = &TreeNode{Val:6}
    root.Right = &TreeNode{Val:1}
    fmt.Println("max average:", maximumAverageSubtree(&root))
}
