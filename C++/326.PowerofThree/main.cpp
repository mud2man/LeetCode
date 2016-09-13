/* Complexity: O(1)
 * 1. Check if upperbond = 3^m can be devided by n
 */

#include <iostream>
#include <math.h>

using namespace std;

class Solution 
{
public:
	Solution();
	~Solution();
	bool isPowerOfThree(int n);
	void dump();

private:

};/*End of class Solution */

Solution::Solution(){
};

Solution::~Solution(){
};

bool Solution::isPowerOfThree(int n){
	int upperbond;
	
	if(n == 0){
		return false;
	}
	
	if(n == 1){
		return true;
	}
	
	upperbond = (int)log(((pow(2, sizeof(int) * 8) / 2) - 1)) / log(3);
	upperbond = (int)pow(3, upperbond);
	
	if((upperbond % n) == 0){
		return true;
	}
	else{
		return false;
	}
};

void Solution::dump(){
};

int main(){
   	int num; 
    Solution *sol;

	num = -3;
	sol = new Solution();
	cout << num << "can be devided by 3 ? => " << sol->isPowerOfThree(num) << endl;

	return 0;
}
