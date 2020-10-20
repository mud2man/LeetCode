/* Hash: Time:O(1), Space:O(n)
 * 1. Implement LinkedHashSet "uniques" to remember the unique number
 * 2. Use Map "duplicates" to remember the duplicates
 * 3. Update LinkedHashSet and Map when add was called
 */

package main

import "fmt"

type Node struct {
    val int
    prev *Node
    next *Node
}

func (head *Node) addVal (val int) *Node {
    var newNode *Node = &Node{val, nil, nil};
    newNode.prev = newNode
    newNode.next = newNode
    if(head != nil){
        newNode.next = head
        newNode.prev = head.prev
        head.prev.next = newNode
        head.prev = newNode
    }
    return newNode
}

func (head *Node) deleteNode (node *Node) {
    var prev *Node = node.prev
    var next *Node = node.next
    prev.next = next
    next.prev = prev
}

type LinkedHashSet struct {
    val2Node map[int]*Node
    head *Node
}

func (linkedHashSet *LinkedHashSet) peekFirst() int {
    if(linkedHashSet.head == nil){
        return -1
    }else{
       return linkedHashSet.head.val
    }
}

func (linkedHashSet *LinkedHashSet) contains(val int) bool {
    if(linkedHashSet.head == nil){
        return false
    }else{
        var node *Node = linkedHashSet.val2Node[val]
        if(node != nil){
            return true
        }else{
            return false
        }
    }
}

func (linkedHashSet *LinkedHashSet) deleteVal(val int) {
    var deeteNode *Node = linkedHashSet.val2Node[val]
    linkedHashSet.head.deleteNode(deeteNode)
    if(deeteNode == linkedHashSet.head){
        if(linkedHashSet.head.next == linkedHashSet.head){
            linkedHashSet.head = nil
        }else{
            linkedHashSet.head = linkedHashSet.head.next
        }
    }
    delete(linkedHashSet.val2Node, val)
}

func (linkedHashSet *LinkedHashSet) addVal(val int) {
    linkedHashSet.val2Node[val] = linkedHashSet.head.addVal(val)
    if(linkedHashSet.head == nil){
        linkedHashSet.head = linkedHashSet.val2Node[val]
    }
}

type FirstUnique struct {
    uniques *LinkedHashSet
    duplicates map[int]bool
}

func Constructor(nums []int) FirstUnique {
    var this FirstUnique = FirstUnique{nil, nil}
    this.uniques = &LinkedHashSet{make(map[int]*Node), nil}
    this.duplicates = make(map[int]bool)
    for _, num := range nums {
        this.Add(num)
    }
    return this
}

func (this *FirstUnique) ShowFirstUnique() int {
    return this.uniques.peekFirst()
}

func (this *FirstUnique) Add(value int)  {
    if(this.duplicates[value]) {
        return;
    }else if(!this.uniques.contains(value)){
        this.uniques.addVal(value)
    }else{
        this.uniques.deleteVal(value)
        this.duplicates[value] = true
    }
}

func main() {
    var nums = []int{2, 3, 5}
    var firstUnique FirstUnique = Constructor(nums)
    fmt.Println("nums:", nums)
    fmt.Println("ShowFirstUnique:", firstUnique.ShowFirstUnique())

    var value = 5
    fmt.Println(fmt.Sprintf("Add(%d)", value))
    firstUnique.Add(value)
    fmt.Println("ShowFirstUnique:", firstUnique.ShowFirstUnique())

    value = 2
    fmt.Println(fmt.Sprintf("Add(%d)", value))
    firstUnique.Add(value)
    fmt.Println("ShowFirstUnique:", firstUnique.ShowFirstUnique())

    value = 3
    fmt.Println(fmt.Sprintf("Add(%d)", value))
    firstUnique.Add(value)
    fmt.Println("ShowFirstUnique:", firstUnique.ShowFirstUnique())
}
