/* Use Combination Theorey: complexity = O(n)
 * 1. Caculate the number with fixed number of digits from 0 to n. 
 * 2. Sum all the specific count with the specific number of digits
 * 3. If number of digits > 10, there is no unique digit number
 */

#include <iostream>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
	int countbyDigtNum(int n);
	int countNumbersWithUniqueDigits(int n);
	void dump();

private:
	
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::countbyDigtNum(int n){
    int count;
    
    if(n == 0){
        count = 1;
    }
    else if (n == 1){
        count = 9;
    }
    else if (n <= 10){
        count = 9;
        for(int i = 1; (i < n); i++){
            count = count * (10 - i);
        }
    }
    else{
        count = 0;
    }
    
    return count;
}

int Solution::countNumbersWithUniqueDigits(int n){
    int digitnum;
    int digitmax;
    int count;
    
    count = 0;
    
    if(n > 10){
        digitmax = 10;
    }
    else{
        digitmax = n;
    }
    
    for(digitnum = 0; digitnum <= digitmax; ++digitnum){
        count = count + countbyDigtNum(digitnum);
    }
    
    return count;
}


void Solution::dump(){
}

int main(){
	int count;
	int digitnum;
	Solution sol;
   	
	digitnum = 2;
	count = sol.countNumbersWithUniqueDigits(digitnum);

	cout << "count: " << count << " with digit num = " << digitnum << endl; 
	
	return 0;
}
