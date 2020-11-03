/* Sort: Time:O(n^2logn), Space:O(n), where n is max(mat.length, mat[0].length)
 * 1. Retrieve each diagnoals
 * 2. Sort them and reassign them to mat
 */

package main

import (
    "fmt"
    "sort"
)

func min(x int, y int) int {
    if(x > y){
        return y
    }else{
        return x
    }
}

func diagonalSort(mat [][]int) [][]int {
    for y := len(mat) - 1; y >= 0; y-- {
        length, diagnoal := min(len(mat) - y, len(mat[0])), make([]int, 0, 0)
        for x := 0; x < length; x++ {
            diagnoal = append(diagnoal, mat[y + x][x])
        }
        sort.Ints(diagnoal)
        for x := 0; x < length; x++ {
            mat[y + x][x] = diagnoal[x]
        }
    }

    for x := 1; x < len(mat[0]); x++ {
        length, diagnoal := min(len(mat[0]) - x, len(mat)), make([]int, 0, 0)
        for y := 0; y < length; y++ {
            diagnoal = append(diagnoal, mat[y][x + y])
        }
        sort.Ints(diagnoal)
        for y := 0; y < length; y++ {
            mat[y][x + y] = diagnoal[y]
        }
    }
    return mat
}

func main() {
    mat := [][]int{{3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}}
    fmt.Println("before:", mat)
    fmt.Println("after:", diagonalSort(mat))
}
