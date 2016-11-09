/* 
 * 1. If any number appear more than once, which means it will be a cycle 
 * 2. Save every caculated number into a set, and check if it conflicts with any element in the set
 */

#include <iostream>
#include <set>
#include <new>

using namespace std;

class Solution {
public:
    bool isHappy(int n) {
        int squareSum;
        int tmp;
        set<int> record;
        set<int>::iterator it;
        
        squareSum = n;
        
        do{
            tmp = 0;
			record.insert(squareSum);
            while(squareSum > 0){
                tmp = tmp + (squareSum % 10)*(squareSum % 10);
                squareSum = squareSum / 10;
            }
            
            squareSum = tmp;

            if(squareSum == 1){
                return true;
            }
            
        }while(record.find(squareSum) == record.end());
        
        return false;
    }
};


int main()
{
	Solution sol;
	bool isTrue;
	
	isTrue = sol.isHappy(7); 
	cout << "isTrue: " << isTrue << endl;
	
	return 0;
}
