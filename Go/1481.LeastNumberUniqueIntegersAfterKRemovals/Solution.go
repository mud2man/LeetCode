/* Greedy: Time:O(nlogn), Space:O(n)
 * 1. Construct num2Count and sort the counts using increasing order
 * 2. Subtract k with counts.get(i) until k <= 0
 */

package main

import ("fmt"
        "sort")

func findLeastNumOfUniqueInts(arr []int, k int) int {
    var num2Count map[int]int = make(map[int]int)
    for _, num := range arr{
        num2Count[num]++
    }
    var counts []int
    for _, val := range num2Count{
        counts = append(counts, val)
    }
    sort.Ints(counts)
    for idx, count := range counts {
        if(count == k) {
            return len(counts) - idx - 1
        }else if(count > k){
            return len(counts) - idx
        }else{
            k = k - count
        }
    }
    return 0
}

func main() {
    var arr = []int{5, 5, 4}
    var k = 1
    fmt.Println("arr:", arr, ", k", k)
    fmt.Println("least number of uniques:", findLeastNumOfUniqueInts(arr, k))
}
