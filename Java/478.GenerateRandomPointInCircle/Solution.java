/* Math: Time:O(1), Space:O(1)
 * 1. We can present point by circular coordinate {x, y} = {radius * cos(theta), radius * sin(theta)}
 * 2. The angle theta is uniformly distributed between 0 and 2 * PI
 * 3. The propability of r is propotional to 2*PI*x where 0 < x < 1, because the area is composed of infinite of circumference
 * 4. So the propability of r = 0.4 is twice of the propability of r = 0.2
 * 5. But we need to understand the theory of inverse transform sampling generating sample numbers at random from any probability distribution 
 *    given its cumulative distribution functioni. https://en.wikipedia.org/wiki/Inverse_transform_sampling
 */

import java.util.*;

public class Solution{
    double r;
    double x;
    double y;
    Random random;
    
    public Solution(double radius, double x_center, double y_center) {
        r = radius;
        x = x_center;
        y = y_center;
        random = new Random();
    }
    
    public double[] randPoint() {
        double theta = 2 * Math.PI * random.nextDouble();
        double radius = r * Math.sqrt(random.nextDouble());
        double[] pos = {radius * Math.cos(theta) + x, radius * Math.sin(theta) + y};
        return pos;
    }
	
    public static void main(String[] args){
        double r = 1.0;
        double x = 0.0;
        double y = 0.0;
        Solution sol = new Solution(r, x, y);
        System.out.println("r:" + r + ", x:" + x + ", y:" + y);
        System.out.println("randPoint: " + Arrays.toString(sol.randPoint()));
        System.out.println("randPoint: " + Arrays.toString(sol.randPoint()));
        System.out.println("randPoint: " + Arrays.toString(sol.randPoint()));
	}
}
