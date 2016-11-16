/* Binary search: Complexity: O(logn)
 * 1. Return condition: (citations[m] >= (size - m)) && (citations[m - 1] <= (size - m))
 *    ex: citations[0, 3, 3, 5, 6], where m = 2, citations[2] = 3 >= (5 - 2), 
 *		  citations[2 - 1] = 3 <= 5, so return
 * 2. Go left condition: (citations[m] >= (size - m)) && (citations[m - 1] > (size - m))
 * 3. Go right condition: (citations[m] < (size - m))
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
    
public:
	Solution();
	~Solution();
    int hIndex(vector<int>& citations);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::hIndex(vector<int>& citations) {
    int lb;
    int ub;
    int m;
    int size;
    
    if(citations.size() == 0){
        return 0;
    }
    
    lb = 0;
    ub = citations.size() - 1;
    size = citations.size();
    
    while(lb <= ub){
        m = (lb + ub) / 2;
        
        if(citations[m] >= (size - m)){
            if(m > 0){
                if(citations[m - 1] <= (size - m)){
                    return (size - m);
                }
                else{
                    ub = m - 1;
                }
            }
            else{
                return (size - m);
            }
        }
        else{
            if(m < (size - 1)){
                lb = m + 1;
            }
            else{
                return 0;
            }
        }
    }
    
    return -1;
}

int main()
{
    Solution sol;
    vector<int> citations;
	int hIndex;

	citations.push_back(0);
	citations.push_back(3);
	citations.push_back(3);
	citations.push_back(6);
	citations.push_back(5);

	hIndex = sol.hIndex(citations);
	
	cout << "hIndex: " << hIndex << endl; 

	return 0;
}
