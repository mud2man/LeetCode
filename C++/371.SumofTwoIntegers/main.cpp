/* Use ALU method O(log(a) + log(b))
 * 1. Use cast (unsigned int) to transfer negative integer to 2's complement 
 * 2. Simulate ALU to implement the add operation without "+" and "-"
 */

#include <iostream>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
	int getSum(int, int);
	void dump();

private:
	
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::getSum(int a, int b) {
    unsigned int c;
    unsigned int sum;
    unsigned int subsum;
    unsigned int digit;
    unsigned int aLsb;
    unsigned int bLsb;
    unsigned int tmpa;
    unsigned int tmpb;
    
    tmpa = (unsigned int)(a);
    tmpb = (unsigned int)(b);
    
    c = 0;
    sum = 0;
    digit = 0;
    
    while(((tmpa > 0) || (tmpb > 0) || (c > 0)) && (digit < sizeof(int)*8)){
        aLsb = tmpa % 2;
        bLsb = tmpb % 2;
        subsum = aLsb ^ bLsb ^ c;
        sum = sum | (subsum << digit);
        
        /* Determine c */
        if(aLsb==1 && bLsb==1 && c==1){
            c = 1;
        }
        else if(aLsb==0 && bLsb==1 && c==1){
            c = 1;
        }
        else if(aLsb==1 && bLsb==0 && c==1){
            c = 1;
        }
        else if(aLsb==1 && bLsb==1 && c==0){
            c = 1;
        }
        else{
            c = 0;
        }
        
        tmpa = tmpa >> 1;
        tmpb = tmpb >> 1;
        
        ++digit;
    }
    
    return (int)sum;
}

void Solution::dump(){
}
    
int main(){
    int a;
	int b;
	Solution sol;

	a = -5;
	b = 8;
   	
	cout << a <<" + " << b << " = " << sol.getSum(a, b) << endl;
	
	return 0;
}
