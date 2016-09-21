/* Use the number of alphabat is only 26
 * 1. Caculate how many alphbat does ransom note have w.r.t. every alphabet
 * 2. Caculate how many alphbat does magazine have w.r.t. every alphabet
 * 3. Compare the number of alphabet from a to z
 */

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
    unsigned int i;
    vector<int> ransonNums(26, 0);
    vector<int> magNums(26, 0);

    if(ransomNote.size() == 0){
        return true;
    }
    
    if(magazine.size() == 0){
        return false;
    }
    
    /* Traslate string into vector */
    for(i = 0; i < ransomNote.size(); ++i){
        ransonNums[ransomNote[i] - 'a']++;
    }
    
    for(i = 0; i < magazine.size(); ++i){
        magNums[magazine[i] - 'a']++;
    }
    
    for(i = 0; i < 26; ++i){
		if(magNums[i] < ransonNums[i]){
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
