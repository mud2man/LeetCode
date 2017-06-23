/* Math: O(logn)
 * 1. Multiply the base itself until the next-time exponent is larger than the input exponent
 * 2. Multiply the ramaining exponent by call "helper" recursively
 */

#include <iostream>
#include <cmath> 
#include <climits> 

using namespace std;

class Solution 
{
public:

double myPow(double x, int n);
double helper(double x, int n);

private:

};/*End of class Solution */
double Solution::helper(double x, int n){
    double tmp;
    int div, m;
    
    if(n == 1){
        return x;
    }
    else if(n == 0){
        return 1;
    }
    
    div = 1;
    tmp = x;
    for(m = n; m >= 2; m = m / 2){
        div = div * 2;
        tmp = tmp * tmp;
    }
    
    return tmp * helper(x, n - div);
}

double Solution::myPow(double x, int n){
    if(n > 0){
        return helper(x, n);
    }
    else if(n < 0){
        return helper(1/x, -n);
    }
    else{
        return 1;
    }
}

int main(){
	double base;
	int exp;
	double ans;
	Solution sol;

	base = 2.5;
	exp = 2;
	ans = sol.myPow(base, exp);
	cout << "pow(" << base << ", " << exp <<") = " << ans << endl;
	return 0;
}
