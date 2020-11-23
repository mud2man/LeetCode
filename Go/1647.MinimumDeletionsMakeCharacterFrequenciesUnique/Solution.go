/* Greedy: Time:O(n), Space:O(1)
 * 1. Get "char2Counts" and sort counts
 * 2. Travers the sorted counts and correct the duplicated to the nearest available count by subtracing at most 26
 * 3. Assume there are two number a and b need to be corrected, the deletion number is the same between (a -> c) & (a -> d) and (a -> d)&(b -> c)
 */

package main

import (
    "fmt"
    "sort"
)

func minDeletions(s string) int {
    char2Count := make([]int, 26, 26)
    for _,letter := range s {
        char2Count[letter - 'a']++
    }
    sort.Ints(char2Count)

    deleteNum := 0
    used := make(map[int]bool)
    for _,count := range char2Count {
        if(count == 0) {
            continue
        }
        if(used[count]){
            isFound := false
            for i := 1; i < 26 && count - i > 0; i++ {
                if(!used[count - i]){
                    deleteNum = deleteNum + i
                    used[count - i] = true
                    isFound = true
                    break
                }
            }
            if(!isFound){
                deleteNum = deleteNum + count
            }
        }
        used[count] = true
    }
    return deleteNum
}

func main() {
    s := "ceabaacb"
    fmt.Println("s:", s)
    fmt.Println("minimum deletion:", minDeletions(s))
}
