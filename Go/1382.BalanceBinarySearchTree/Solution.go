/* BST: Time:O(n), Space:O(n)
 * 1. Put tree nodes into sortedList
 * 2. Insert the val into the new tree by selecting the mid value of sortedList
 */

package main

import ("fmt")

type TreeNode struct {
    Val int
    Left *TreeNode
    Right *TreeNode
}

func inOrderVisit(root *TreeNode, sortedList *[]*TreeNode){
    if(root == nil){
        return
    }
    inOrderVisit(root.Left, sortedList)
    *sortedList = append(*sortedList, root)
    inOrderVisit(root.Right, sortedList)
}

func getNode(lb int, hb int, sortedList []*TreeNode) *TreeNode {
    if(lb > hb){
        return nil
    }else{
        var mid int = (hb + lb) / 2
        var newNode TreeNode = TreeNode{Val: sortedList[mid].Val}
        newNode.Left = getNode(lb, mid - 1, sortedList)
        newNode.Right = getNode(mid + 1, hb, sortedList)
        return &newNode
    }
}

func balanceBST(root *TreeNode) *TreeNode {
    var sortedList []*TreeNode = make([]*TreeNode, 0, 0)
    inOrderVisit(root, &sortedList)
    return getNode(0, len(sortedList) - 1, sortedList)
}

func main() {
    var root TreeNode = TreeNode{Val:1}
    root.Right = &TreeNode{Val:2}
    root.Right.Right = &TreeNode{Val:3}
    root.Right.Right.Right = &TreeNode{Val:4}
    fmt.Println("No example")
}
