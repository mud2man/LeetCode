/* Complexity: O(n)
 * 1. Use stack
 */

#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

class Solution 
{
public:
	Solution();
	~Solution();
	void dump();
	int reverse(int );

private:
    bool isNegative;
	vector<int> stack;

};/*End of class Solution */

Solution::Solution(){
};

Solution::~Solution(){
};


void Solution::dump(void){
    int i;
    
    for(i = 0; i < this->stack.size(); i++){
        cout << stack[i] << ",";
    }
    
    cout << endl;
}

int Solution::reverse(int x) {
    int digit;
    int ans;
    int max = 0x7fffffff;
    
    if(x < 0){
        x = abs(x);
        this->isNegative = true;
    }
    else{
        this->isNegative = false;
    }
    
    /* Store every digit into "stack" */
    while(x > 0){
        digit = x % 10;
        stack.push_back(digit);
        x = x / 10;
    }
    dump();
    ans = 0;
    /* Pop every digit from "stack" */
    while(!stack.empty()){
        digit = stack.front();
        stack.erase(stack.begin());
        if((10 * (unsigned long)ans + (unsigned long)digit) > (unsigned long)max){
            return 0;
        }
        else{
            ans = 10 * ans + digit;
        }
    }
    
    if(this->isNegative == true){
        ans = 0 - ans;
    }
    
    return ans;
}

int main(){
   	int num;
    Solution sol;
    
    num = 1356;

	cout << "num: " << num << endl;;
	cout << "After reverse: " << sol.reverse(num) << endl;

	return 0;
}
