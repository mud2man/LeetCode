/* Two Pointers: Time:O(n + m), Space:O(1)
 * 1. Track on both arrays with index i and j
 * 2. Caculate and merge the last product with {product, minFreq} 
 */

package main

import (
    "fmt"
    "math"
)

func findRLEArray(encoded1 [][]int, encoded2 [][]int) [][]int {
    i := 0
    j := 0
    var products [][]int
    for i < len(encoded1) && j < len(encoded2) {
        minFreq := int(math.Min(float64(encoded1[i][1]), float64(encoded2[j][1])))
        product := encoded1[i][0] * encoded2[j][0]
        if(len(products) == 0 || products[len(products) - 1][0] != product){
            products = append(products, []int{product, minFreq})
        }else{
            products[len(products) - 1][1] = products[len(products) - 1][1] + minFreq
        }
        encoded1[i][1] = encoded1[i][1] - minFreq
        if(encoded1[i][1] == 0){
            i = i + 1
        }
        encoded2[j][1] = encoded2[j][1] - minFreq
        if(encoded2[j][1] == 0){
            j = j + 1
        }
    }
    return products
}

func main() {
    encoded1 := [][]int{{1, 3}, {2, 3}};
    encoded2 := [][]int{{6, 3}, {3, 3}};
    fmt.Println("encoded1:", encoded1)
    fmt.Println("encoded2:", encoded2)
    fmt.Println("products:", findRLEArray(encoded1, encoded2))
}
