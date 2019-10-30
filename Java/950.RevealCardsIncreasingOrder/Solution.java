/* Queue: Time:O(nlogn), Space:O(n) 
 * 1. We have a queue, and we put the number into the queue in reversing order
 * 2. We select number from the sorted deck, which is accending order
 * 3. Then, poll the tail of queue, and insert from head, then insert the selected number
 *
 * ex: deck = [17,13,11,2,3,5,7]
 * We sort deck to [2,3,5,7,11,13,17] (this order doesn't matter), and reorder it.
 * After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
 * We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
 * We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
 * We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
 * We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
 * We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
 * We reveal 13, and move 17 to the bottom.  The deck is now [17].
 * We reveal 17.
 */

import java.util.*;

public class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        Deque<Integer> queue = new LinkedList<>();
        queue.add(deck[deck.length - 1]);
        for(int i = deck.length - 2; i >= 0; --i){
            int card = deck[i];
            int tail = queue.pollLast();
            queue.addFirst(tail);
            queue.addFirst(card);
        }
        
        int[] ret = new int[deck.length];
        for(int i = 0; i < ret.length; ++i){
            ret[i] = queue.pollFirst();
        }
        return ret;
    }
  
    public static void main(String[] args){
        int[] deck = {17, 13, 11, 2, 3, 5, 7};
        Solution sol = new Solution();
        System.out.println("deck:" + Arrays.toString(deck));
        System.out.println("revealed deck:" + Arrays.toString(sol.deckRevealedIncreasing(deck)));
    }
}
