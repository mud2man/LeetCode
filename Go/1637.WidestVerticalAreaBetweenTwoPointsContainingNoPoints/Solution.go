/* Sort: Time:O(n*lognn), Space:O(n)
 * 1. Sort by x-coordinates, and update max by comparing each x-interval
 */

package main

import (
    "fmt"
    "sort"
)

func max(x int, y int) int {
    if(x >= y){
        return x
    }else{
        return y
    }
}

func maxWidthOfVerticalArea(points [][]int) int {
    xs := make([]int, 0, 0)
    for _ , point := range points {
        xs = append(xs, point[0])
    }
    sort.Ints(xs)

    maxVerticalArea := 0
    for i := 0; i < len(xs) - 1; i++{
        maxVerticalArea = max(maxVerticalArea, xs[i + 1] - xs[i])
    }
    return maxVerticalArea
}

func main() {
    points := [][]int {{3, 1}, {9, 0}, {1, 0}, {1, 4}, {5, 3}, {8, 8}}
    fmt.Println("before:", points)
    fmt.Println("max vertival area:", maxWidthOfVerticalArea(points))
}
