/* Sort and compare: O(nlogn)
 */

#include <algorithm>
#include <vector>
#include <iostream>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
	bool canConstruct(string ransomNote, string magazine);
	void dump();

private:
	
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

bool Solution::canConstruct(string ransomNote, string magazine) {
    int i;
    int j;
    int target;
    vector<int> ransonNums;
    vector<int> magNums;
    
    if(ransomNote.size() == 0){
        return true;
    }
    
    if(magazine.size() == 0){
        return false;
    }
    
    /* Traslate string into vector */
    for(i = 0; i < ransomNote.size(); ++i){
        ransonNums.push_back(ransomNote[i] - 'a');
    }
    
    for(i = 0; i < magazine.size(); ++i){
        magNums.push_back(magazine[i] - 'a');
    }
    
    /* Sort rasomNums and maggize */
    sort(ransonNums.begin(), ransonNums.end());
    sort(magNums.begin(), magNums.end());
    
    j = 0;
    for(i = 0; i < ransonNums.size(); ++i){
        target = ransonNums[i];
        while(magNums[j] < target){
            ++j;
        }
        
        if(magNums[j] == target){
            ++j;
            
            if(j == magNums.size() && i < (ransonNums.size() - 1)){
                return false;
            }
        }
        else{
            return false;
        }
    }
    return true;
}

void Solution::dump(){
}

int main(){
	Solution sol;
	string ransomNote = "aba";
	string magazine = "cbdbbaa";
	
	cout << "ransomNote: " << ransomNote << endl;
	cout << "magazine: " << magazine << endl;
	cout << "can construct? " << sol.canConstruct(ransomNote, magazine) << endl;
	
	return 0;
}
