/* 
 * 1. Encode the Dna sequence via the denifination, A=0, C=1, G=2, T=3
 * 2. Caculate all the encoded number of every sub-sequence from right
 * 3. Store all the encoded number in "multiset", because it's based on rb-tree
 * 4. Any of the element in rb-tree is a pair = (encoded number, starting position)
 * 5. Iterate all the pairs, if the position difference > 10, repeated sub-sequence is found
 */

#include <iostream>
#include <map>
#include <vector>
#include <string>

using namespace std;

class Solution
{

public:
	Solution(void);
	~Solution(void);
	vector<string> findRepeatedDnaSequences(string s);
	void dump();

private:
	/* Utilities function */
	unsigned long seq2num(string);
	string num2seq(unsigned long);

	/* The sub-sequence number with position */
	multimap<unsigned long,int> seqs;

};/*End of class Solution */

Solution::Solution(void)
{
}

Solution::~Solution(void)
{
	seqs.clear();
}

vector<string> Solution::findRepeatedDnaSequences(string s)
{	
	unsigned int idx;
	unsigned int headidx;
	unsigned int tailidx;
	string subSeq;
	unsigned long seqNum;
	pair<unsigned long, int> seqPair;
	multimap<unsigned long,int>::iterator mapit;
	multimap<unsigned long,int>::iterator preMapit;
	
	/* Repeated sequences */
	vector<string> repeatedSeqs;
	
	/* Store all the encoded number in multimap seqs */
	for(idx = 0; (idx + 9) < s.length(); idx++)
	{
		subSeq = s.substr(idx, 10);
		seqNum = seq2num(subSeq);
		seqPair.first = seqNum;
		seqPair.second = idx;
		seqs.insert(seqPair);
	}

	/* Iterate all the pairs, check if the position difference > 0 */
	mapit = seqs.begin();
	while(mapit != seqs.end())
	{
		seqNum = (*mapit).first;
		headidx = (*mapit).second;

		do
		{	preMapit = mapit;
			mapit++;
		}while((*mapit).first == seqNum);

		tailidx = (*preMapit).second;
		
		/* Makse sure no overlap */
		if((tailidx - headidx) > 0)
		{
			subSeq = num2seq(seqNum);
			repeatedSeqs.push_back(subSeq);
		}
	}

	return repeatedSeqs;
}

unsigned long Solution::seq2num(string s)
{
	unsigned int idx;
	unsigned long num;

	num = 0;
	
	/*A=0, C=1, G=2, T=3 */
	for(idx = 0; idx < 10; idx++)
	{
		switch(s[idx])
		{
			case 'A':
				num = num*10;
				break;
			
			case 'C':
				num = num*10 + 1;
				break;
			
			case 'G':
				num = num*10 + 2;
				break;
			
			case 'T':
				num = num*10 + 3;
				break;
		}
	}

	return num;
}

string Solution::num2seq(unsigned long num)
{
	unsigned long lsb;	
	unsigned int idx;
	string s = "AAAAAAAAAA" ;
    
	for(idx = 0; idx < 10; idx++)
	{
		lsb = num % 10;

		switch(lsb)
		{
			case 0:
				s[9 - idx] = 'A';
				break;
			
			case 1:
				s[9 - idx] = 'C';
				break;
			
			case 2:
				s[9 - idx] = 'G';
				break;
			
			case 3:
				s[9 - idx] = 'T';
				break;
		}
		num = num / 10;
	}

	return s;
}

void Solution::dump(void)
{
	multimap<unsigned long,int>::iterator mapit;

	cout << "Sub-sequences: " << endl;
	for(mapit = seqs.begin(); mapit != seqs.end(); mapit++)
	{
		cout << "<"<<(*mapit).first << ", " << (*mapit).second << ">" << endl;
	}
}

int main()
{
	string s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
	//string s = "AAAAAAAAAAA";
	//string s = "GAGAGAGAGAGA";
	Solution sol;
	vector<string> repeatedSeqs;
	vector<string>::iterator vecit;
	
	cout << "string: " << s << endl;

	repeatedSeqs = sol.findRepeatedDnaSequences(s);
	sol.dump();
	
	cout << "Repeated sub-sequences: " << endl;
	for(vecit = repeatedSeqs.begin(); vecit != repeatedSeqs.end(); vecit++)
	{
		cout <<(*vecit) << endl;
	}
}
