/* Sliding window: Time:O(n), Space:O(1)
 * 1. Update maxPlus as we shift X-wide sliding window from left to right by -(grumpy[i - X] * customers[i - X])+(grumpy[i] * customers[i])
 * 2. Accumulate base as we shift X-wide sliding window
 * 3. The answer is base + maxPlus
 */

package main

import (
    "fmt"
    "math"
)

func maxSatisfied(customers []int, grumpy []int, X int) int {
    base := 0
    plus := 0
    for i := 0; i < X; i++ {
        base += (1 - grumpy[i]) * customers[i]
        plus += grumpy[i] * customers[i]
    }

    maxPlus := plus
    for i := X; i < len(customers); i++ {
        base += (1 - grumpy[i]) * customers[i]
        plus -= grumpy[i - X] * customers[i - X]
        plus += grumpy[i] * customers[i]
        maxPlus = int(math.Max(float64(plus), float64(maxPlus)))
    }
    return base + maxPlus
}

func main() {
    customers := []int{1, 0, 1, 2, 1, 1, 7, 5};
    grumpy := []int{0, 1, 0, 1, 0, 1, 0, 1};
    X := 3
    fmt.Println("customers:", customers)
    fmt.Println("grumpy:", grumpy)
    fmt.Println("X:", X)
    fmt.Println("max satisfied:", maxSatisfied(customers, grumpy, X))
}
