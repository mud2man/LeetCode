/* Sort: Time:O(nlogn), Space:O(n)
 * 1. Have a list of "Car", which contains the position and arrival time. 
 * 2. Sort ths list "cars" based on position
 * 3. Merger the cars if it blocks by leading car according the arrival time, and update count
 */         

import java.util.*;

public class Solution {
    private class Car{
        int pos;
        double time;
        Car(int p, double t){pos = p; time = t;}
    }
    
    private class CarComparator implements Comparator<Car>{
        public int compare(Car x, Car y){
            return (y.pos - x.pos);
        }    
    }
    
    public int carFleet(int target, int[] position, int[] speed) {
        List<Car> cars = new ArrayList<Car>();
        for(int i = 0; i < position.length; ++i){
            cars.add(new Car(position[i], (double)(target - position[i]) / (double)speed[i]));
        }
        
        Collections.sort(cars, new CarComparator());
               
        int count = 0;
        int idx = 0;
        while(idx < cars.size()){
            double time = cars.get(idx).time;
            while(idx < (cars.size() - 1) && time >= cars.get(idx + 1).time){
                idx++;
            }
            count++;
            idx++;
        }
        return count; 
    }
  
    public static void main(String[] args){
        Solution sol= new Solution();
        int target = 12; 
        int[] position = {10, 8, 0, 5, 3}; 
        int[] speed = {2, 4, 1, 1, 3}; 
        System.out.println("target: " + target);
        System.out.println("position: " + Arrays.toString(position));
        System.out.println("speed: " + Arrays.toString(speed));
        System.out.println("car fleets #: " + sol.carFleet(target, position, speed));
    }
}
