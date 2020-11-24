/* Dynamic Programming: Time:O(m*n), Space:O(m*n)
 * 1. zeroDiffDp[y][x] = number of substring pairs without difference ending at s.charAt(y) and t.charAt(x)
 * 2. oneDiffDp[y][x] = number of substring pairs with one different letter ending at s.charAt(y) and t.charAt(x)
 * 3. Accumulate count while update oneDiffDp[y][x]
 */

package main

import (
    "fmt"
)

func make2dSlice(depth int, width int) [][]int {
    slice := make([][]int, depth)
    for i := range slice {
        slice[i] = make([]int, width)
    }
    return slice
}

func countSubstrings(s string, t string) int {
    zeroDiffDp := make2dSlice(len(s), len(t))
    for y := 0; y < len(s); y++{
        for x := 0; x < len(t); x++{
            if(s[y] == t[x]){
                if(y > 0 && x > 0){
                    zeroDiffDp[y][x] = zeroDiffDp[y - 1][x - 1] + 1
                }else{
                    zeroDiffDp[y][x] = 1
                }
            }
        }
    }
    oneDiffDp := make2dSlice(len(s), len(t))
    count := 0
    for y := 0; y < len(s); y++{
        for x := 0; x < len(t); x++{
            if(s[y] == t[x]){
                if(y > 0 && x > 0){
                    oneDiffDp[y][x] = oneDiffDp[y - 1][x - 1]
                }
            }else{
                if(y > 0 && x > 0){
                    oneDiffDp[y][x] = zeroDiffDp[y - 1][x - 1] + 1;
                }else{
                    oneDiffDp[y][x] = 1
                }
            }
            count = count + oneDiffDp[y][x]
        }
    }
    return count
}

func main() {
    s := "abe"
    t := "bbc"
    fmt.Println("s:", s, "t:", t)
    fmt.Println("substrings count::", countSubstrings(s, t))
}
