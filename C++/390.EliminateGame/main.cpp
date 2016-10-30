/* 
 * Complexity = O(logn)
 * 1. If from left, head = head + step
 * 2. If from right, need to fullfill the number of remaining is odd. then head = head + step
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
	int lastRemaining(int n);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::lastRemaining(int n) {
    int remaining;
    int head;
    int step;
    bool left;
    
    remaining = n;
    
    head = 1;
    left = true;
    step = 1;
    while(remaining > 1){
        if(left || (remaining % 2) == 1){
            head = head + step;
        }
        remaining = remaining / 2;
        step = step*2;
        left = !left;
    }
    
    return head;
}

int main(){
    Solution sol;
    int num;

	num = 9;

	cout << "num: " << num << endl;
	cout << "remaining: " << sol.lastRemaining(num) << endl;

	return 0;
}
