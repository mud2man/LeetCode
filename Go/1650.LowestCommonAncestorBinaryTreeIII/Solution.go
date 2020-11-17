/* Two pointers: Time:O(h), Space:O(1)
 * 1. Use the same logic of LeetCode#160, since the the distance of reaching LCA on second time is the same
 */

package main

import (
    "fmt"
)

 type Node struct {
    Val int
    Left *Node
    Right *Node
    Parent *Node
 }

func lowestCommonAncestor(p *Node, q *Node) *Node {
    ptr0 := p
    ptr1 := q
    for ptr0 != ptr1 {
        if(ptr0.Parent == nil){
            ptr0 = q
        }else{
            ptr0 = ptr0.Parent
        }

        if(ptr1.Parent == nil){
            ptr1 = p
        }else{
            ptr1 = ptr1.Parent
        }
    }
    return ptr0
}


func main() {
    root := &Node{5, nil, nil, nil}
    p := &Node{2, nil, nil, nil}
    q := &Node{-3, nil, nil, nil}
    root.Left = p
    p.Parent = root
    root.Right = q
    q.Parent = root
    fmt.Println("LCA of", p.Val ,"and", q.Val, "is:", lowestCommonAncestor(p, q).Val)
}
