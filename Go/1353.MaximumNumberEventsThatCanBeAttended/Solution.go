/* Heap: Time:O(n*logn), Space:O(n)
 * 1. Put the unique starts into sortedStarts and traverse lastTryAttendDay interval by interval (interval = sortedStarts.get(i), sortedStarts.get(i + 1))
 * 2. During the interval traversal, pop out the outdated event and pick the event with earliest end if minHeap is not empty
 * 3. Step 2 is valid, since minHeap only keeps the valid interval
 */

package main

import (
    "container/heap"
    "fmt"
    "sort"
)

type MinHeap []int

func (heap MinHeap) Len() int {
    return len(heap)
}

func (heap MinHeap) Less(i, j int) bool {
    return heap[i] < heap[j]
}

func (heap MinHeap) Swap(i, j int) {
    heap[i], heap[j] = heap[j], heap[i]
}

func (heap *MinHeap) Pop() interface{} {
    prev := *heap
    tail := prev[len(prev) - 1]
    *heap = prev[0 : len(prev) - 1]
    return tail
}

func (heap *MinHeap) Push(x interface{}) {
    *heap = append(*heap, x.(int))
}

func maxEvents(events [][]int) int {
    starts := make(map[int]bool)
    start2Ends := make(map[int][]int)
    for _, event := range events {
        starts[event[0]] = true
        if (start2Ends[event[0]] == nil){
            start2Ends[event[0]] = make([]int, 0, 0)
        }
        start2Ends[event[0]] = append(start2Ends[event[0]], event[1])
    }
    sortedStarts := make([]int, 0, 0)
    for start := range starts {
        sortedStarts = append(sortedStarts, start)
    }
    sort.Ints(sortedStarts)

    count := 0;
    lastTryAttendDay := sortedStarts[0]
    minHeap := &MinHeap{}
    heap.Init(minHeap)
    for i := 1; i <= len(sortedStarts); i++ {
        end := 0
        if(i == len(sortedStarts)) {
            end = sortedStarts[i - 1] + len(events) + 1
        }else{
            end = sortedStarts[i]
        }
        for _, candidateEnd := range start2Ends[lastTryAttendDay] {
            heap.Push(minHeap, candidateEnd)
        }
        for lastTryAttendDay < end && minHeap.Len() > 0 {
            popOudated(minHeap, lastTryAttendDay)
            if(minHeap.Len() > 0) {
                count++
                heap.Pop(minHeap)
            }
            lastTryAttendDay++
        }
        lastTryAttendDay = end
    }
    return count
}

func popOudated(minHeap *MinHeap, lastTryAttendDay int) {
    for minHeap.Len() > 0 {
        root := heap.Pop(minHeap)
        if(root.(int) >= lastTryAttendDay){
            heap.Push(minHeap, root.(int))
            break
        }
    }
}

func main() {
    events := [][]int{{1, 2}, {2, 3}, {3, 4}, {1, 2}}
    fmt.Println("events:", events)
    fmt.Println("max number of attendable event:", maxEvents(events))
}
