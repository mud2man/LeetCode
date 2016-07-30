/* Use recursive method
 * 1. Create a hash table to store the mapping table from bottom 2 to bottom 9
 * 2. Enter the recursive function digit2letter with 2 input parameters "digits" and previous translated string 
 * 3. Call the digit2letter three times respectively, with three different characters mapped to the digit  
 */

#include <iostream>
#include <vector>
#include <new>
#include <string>
#include <stdlib.h> 

using namespace std;

class Solution 
{
public:

vector<string> letterCombinations(string digits) 
{
	string::iterator it;
	vector<string> ::iterator itx;
	int digit;
	int idx;

    if(digits.empty())
	{
		return strings;
	}
					    
	/* Convert string to array of numbers */
	for(idx = 0; idx < (int)digits.length(); idx++)
	{
		digit = digits[idx] - '0';
		diglist.push_back(digit);
	}

	/* Create digit table */
	digtbl.push_back("");
	digtbl.push_back("");
	digtbl.push_back("abc");
	digtbl.push_back("def");
	digtbl.push_back("ghi");
	digtbl.push_back("jkl");
	digtbl.push_back("mno");
	digtbl.push_back("pqrs");
	digtbl.push_back("tuv");
	digtbl.push_back("wxyz");

	digit2letter(0, "");

	return strings;
}

void digit2letter(int idx, string letters)
{
	string bottom;
	string::iterator it;
	int key;

	if(idx == (int)diglist.size())
	{
		strings.push_back(letters);
		return;
	}
	else
	{
		key = diglist[idx];
		bottom = digtbl[key];
		
		/* Iterate all character on the bottom */
		for(it = bottom.begin(); it != bottom.end(); it++)
		{
			letters.push_back(*it);
			digit2letter(idx + 1, letters);
			letters.erase(letters.length() - 1);
		}
	}
}

private:
    /* Input digit list */
    vector<int> diglist;
    
	/* Output string list */
    vector<string> strings;
    
	/* Digit translation table */
	vector<string> digtbl;

};/*End of class Solution */

int main()
{
	string digits ("239");
	vector<string> strings;
	vector<string>::iterator it;
	Solution sol;

    /* Tree generation */
	strings = sol.letterCombinations(digits);
	/* Output the strings */
	for(it = strings.begin(); it != strings.end(); it++)
	{
		cout << *it << ",";
	}
	cout << endl;

	return 0;
}
