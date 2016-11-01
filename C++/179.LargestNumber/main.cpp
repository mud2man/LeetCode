/* Complexity = O(nlogn) 
 * Compare if a.b is larger than b.a
 */

#include <iostream>
#include <vector>
#include <math.h> 
#include <algorithm> 

using namespace std;

bool comparator(int a, int b){
    int widtha;
    int widthb;
    long aBefore;
    long bBefore;
    
    if((a == 0) && (b != 0)){
        return true;
    }
    
    if((b == 0) && (a != 0)){
        return false;
    }
    
    if((a == 0) && (b == 0)){
        return false;
    }
    
    widtha = log10(a) + 1;
    widthb = log10(b) + 1;
    
    aBefore = (long)(a*pow(10, widthb) + b);
    bBefore = (long)(b*pow(10, widtha) + a);
    
    return (aBefore < bBefore) ;
}

class Solution {
    
public:
	Solution();
	~Solution();
	string largestNumber(vector<int>& nums);
	int getIndex(int num, int pos, int digitNum);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

string Solution::largestNumber(vector<int>& nums) {
	vector<int> cloneNums;
	string ans;
	int size;
	int i;
	
	cloneNums = nums;
	size = cloneNums.size();
	
	if(size == 0){
	    return ans;
	}
	
	sort(cloneNums.begin(), cloneNums.end(), comparator);
	
	for(i = size - 1; i >= 0; --i){
	    if(!ans.empty() || cloneNums[i] != 0){
	        ans.append(to_string(cloneNums[i]));
	    }
	}
	
	if(ans.empty()){
	    return "0";
	}
	else{
	    return ans;
	}
}

int main(){
    Solution sol;
    vector<int> nums;
    
    nums.push_back(3);
    nums.push_back(30);
    nums.push_back(34);
    nums.push_back(5);
    nums.push_back(9);
    
    cout << "max: " << sol.largestNumber(nums) << endl;

	return 0;
}
