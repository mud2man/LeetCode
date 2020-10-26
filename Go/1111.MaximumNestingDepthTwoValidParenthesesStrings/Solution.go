/* Greedy: Time:O(n), Space:O(1)
 * 1. Iterate seq with depth and do classifying by depth % 2
 * 2. Assign type[i] with the classifier 
*/

package main

import ("fmt")

func maxDepthAfterSplit(seq string) []int {
    var types = make([]int, len(seq), len(seq))
    var depth = 0
    for i, _ := range seq {
        if(seq[i] == '('){
            depth++
            types[i] = depth % 2
        }else{
            types[i] = depth % 2
            depth--
        }
    }
    return types
}

func main() {
    var seq = "(()())"
    fmt.Println("seq:", seq)
    fmt.Println("after split:", maxDepthAfterSplit(seq))
}
