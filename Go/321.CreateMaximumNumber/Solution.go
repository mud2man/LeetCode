/* Greedy: Time:O(n), Space:O(1)
 * 1. Iterate seq with depth and do classifying by depth % 2
 * 2. Assign type[i] with the classifier 
*/

package main

import ("fmt")

func getMaxNums(nums1, nums2 []int) []int{
    maxNums := make([]int, len(nums1) + len(nums2), len(nums1) + len(nums2))
    for i, j := 0, 0 ; i < len(nums1) || j < len(nums2); {
        num1 := -1
        if(i < len(nums1)){
           num1 =  nums1[i]
        }
        num2 := -1
        if(j < len(nums2)){
           num2 =  nums2[j]
        }
        if(num1 > num2){
            maxNums[i + j] = num1
            i++
        }else if(num1 < num2){
            maxNums[i + j] = num2
            j++
        }else{
            if(isBigger(nums1, i, nums2, j)){
                maxNums[i + j] = num1
                i++
            }else{
                maxNums[i + j] = num2
                j++
            }
        }
    }
    return maxNums
}

func isBigger(nums1 []int, i int, nums2 []int, j int) bool{
    for i < len(nums1) || j < len(nums2){
        if(j == len(nums2)){
            return true
        }else if(i == len(nums1)){
            return false
        }else if(nums1[i] > nums2[j]){
            return true
        }else if(nums1[i] < nums2[j]){
            return false
        }else{
            i++
            j++
        }
    }
    return true
}

func getMaxSubNums(nums []int, length int) []int{
    stack := make([]int, length, length)
    top := -1
    remain := len(nums) - length
    for _, num := range nums{
        for top >= 0 && stack[top] < num && remain > 0{
            top--
            remain--
        }
        if(top < length - 1){
            top++
            stack[top] = num
        }else{
            remain--
        }
    }
    return stack
}

func maxNumber(nums1 []int, nums2 []int, k int) []int{
    max := make([]int, k, k)
    for len1 := 0; len1 <= k && len1 <= len(nums1); len1++{
        len2 := k - len1
        if(len2 <= len(nums2)){
            maxSubNums1 := getMaxSubNums(nums1, len1)
            maxSubNums2 := getMaxSubNums(nums2, len2)
            curr := getMaxNums(maxSubNums1, maxSubNums2)
            if(isBigger(curr, 0, max, 0)){
                max = curr
            }
        }
    }
    return max
}

func main() {
    nums1 := []int{3, 4, 6, 5}
    nums2 := []int{9, 1, 2, 5, 8, 3}
    k := 5
    fmt.Println("nums1:", nums1)
    fmt.Println("nums2:", nums2)
    fmt.Println("k:", k)
    fmt.Println("max nums:", maxNumber(nums1, nums1, k))
}
