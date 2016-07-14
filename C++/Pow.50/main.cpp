/* 
 * 1. Multiply the base itself until the next-time exponent is larger than the input exponent
 * 2. Multiply the ramaining exponent by call "myPow" recursively
 */

#include <iostream>
#include <cmath> 
#include <climits> 

using namespace std;

class Solution 
{
public:

double myPow(double x, int n);

private:

};/*End of class Solution */

double Solution::myPow(double x, int n)
{
	long absexp = (unsigned long)abs(n);
	long currexp = 1;
	double total = x;

	if(n != 0)
	{
		if (absexp == 1)
		{
			total = x;
			goto end;
		}

		while(currexp*2 <= absexp)
		{
			total = total * total;
			currexp = currexp * 2;
		}

		total = total * myPow(x, absexp - currexp);
	}
	else
	{
		total = 1;
	}

end:
	if(n < 0)
	{
		total = 1/ total;
	}

	return total;
}

int main()
{
	double base;
	int exp;
	double ans;
	Solution sol;

	for(int i = 0; i < 1000000; i++)
	{
		base = 0.00001;
		exp = 2147483647;
		ans = sol.myPow(base, exp);
	}

	cout << "pow(" << base << ", " << exp <<") = " << ans << endl;
	return 0;
}
