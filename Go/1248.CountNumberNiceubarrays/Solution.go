/* Two pointers: Time:O(n), Space:O(1)
 * 1. Have head and tail pointer to record the window containing k odd numbers
 * 2. Each round, move tail until reach an odd number and accumulate rightCount
 * 3. Move head until reach an odd number and accumulate leftCount
 * 3. Accumulate count by (rightCount * leftCount)
 */


package main

import ("fmt")

func numberOfSubarrays(nums []int, k int) int {
    oddCount := 0
    tail := 0
    for(tail < len(nums) && oddCount < k){
        if(nums[tail] % 2 == 1){
            oddCount++
        }
        tail++
    }
    tail--
    if(oddCount < k){
        return 0
    }

    head := 0
    count := 0
    for(tail < len(nums)){
        //move tail
        rightCount := 1
        for(tail + 1 < len(nums) && nums[tail + 1] % 2 == 0){
            tail++;
            rightCount++;
        }
        tail++;

        //move head
        leftCount := 1
        for(head < tail && nums[head] % 2 == 0){
            head++;
            leftCount++;
        }
        head++
        count += (rightCount * leftCount)
    }
    return count
}

func main() {
    nums := []int{1, 1, 2, 1, 1}
    k := 3
    fmt.Println("nums:", nums, "k:", k)
    fmt.Println("count:", numberOfSubarrays(nums, k))
}
