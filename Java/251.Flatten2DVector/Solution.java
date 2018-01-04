/* Iterator: Time:O(1), Space:O(1)
 * 1. Have two iterators iteratorY and iteratorX
 * 2. Have helper "jumpNext" to set iteratorX to the next available position
 * 3. Determine if has next by (iteratorX != null && iteratorX.hasNext())
 */

import java.util.*;

public class Solution implements Iterator<Integer> {
    Iterator<List<Integer>> iteratorY;
    Iterator<Integer> iteratorX;
    
    private void jumpNext(){
        if(iteratorX != null && iteratorX.hasNext()){
            return;
        }
        
        while(iteratorY.hasNext()){
            List<Integer> innerList = iteratorY.next();
            iteratorX = innerList.iterator();
            if(iteratorX.hasNext()){
                break;   
            }
        }
    }
    
    public Solution(List<List<Integer>> vec2d) {
        iteratorY = vec2d.iterator();
        iteratorX = (iteratorY.hasNext())? iteratorY.next().iterator(): null;
    }

    @Override
    public Integer next() {
        return iteratorX.next();
    }

    @Override
    public boolean hasNext() {
        if(iteratorX != null && iteratorX.hasNext()){
            return true;
        }
        else{
            jumpNext();
            return (iteratorX != null && iteratorX.hasNext());
        }
    }

    public static void main(String[] args){
        Solution sol;
        List<List<Integer>> vec2d = new ArrayList<List<Integer>>();
        vec2d.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
        vec2d.add(new ArrayList<Integer>(Arrays.asList(3)));
        vec2d.add(new ArrayList<Integer>(Arrays.asList(4, 5, 6)));

        sol = new Solution(vec2d);    
        System.out.println("vec2d: " + vec2d);
        while(sol.hasNext()){
            System.out.println(sol.next());
        }
    }
}
