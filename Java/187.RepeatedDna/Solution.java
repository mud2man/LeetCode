/* 
 * 1. Encode the Dna sequence via the denifination, A=0, C=1, G=2, T=3
 * 2. Caculate all the encoded number of every sub-sequence from right
 * 3. Store all the encoded number in hashtable
 * 4. If the number of the associate element is 2, duplicated sub-sequence found
 */

import java.util.*;

public class Solution 
{
	public static final int BITMAP_SIZE = 1024*1024;

	/* Bit map to record the traversal */
	private short bitmap[];
	
	/* Repeated sequences */
	private List<String> repeatedSeqs;

	Solution()
	{
		bitmap = new short [BITMAP_SIZE];
		repeatedSeqs = new ArrayList<String>();
	}

	public List<String> findRepeatedDnaSequences(String s)
	{
		int strLen;
		int idx;
		int seqNum;
		String subSeq;
		
		strLen = s.length();
		
		if(strLen <= 10)
		{
			return repeatedSeqs;
		}
    
		/* Store all the encoded number in multimap seqs */
		for(idx = 0; (idx + 9) < strLen; idx++)
		{
			seqNum = seq2num(idx, s);
			bitmap[seqNum]++;
			
			if(bitmap[seqNum] == 2)
			{
				subSeq = num2seq(seqNum);
				repeatedSeqs.add(subSeq);
			}
		}

		return repeatedSeqs;
	}
	
	int seq2num(int idx, String s)
	{
		int offset;
		int num;
	
		num = 0;
		
		/*A=0, C=1, G=2, T=3 */
		for(offset = 0; offset < 10; offset++)
		{
			switch(s.charAt(idx + offset))
			{
				case 'A':
					num = num*4;
					break;
				
				case 'C':
					num = num*4 + 1;
					break;
				
				case 'G':
					num = num*4 + 2;
					break;
				
				case 'T':
					num = num*4 + 3;
					break;
			}
		}
	
		return num;
	}

	String num2seq(int num)
	{
		int lsb;	
		int idx;
		int offset;
		String s = "AAAAAAAAAA" ;
	    
		for(idx = 0; idx < 10; idx++)
		{
			lsb = num % 4;
			offset = 9 -idx;
	
			switch((int)lsb)
			{
				case 0:
					s = s.substring(0, offset)+ 'A'+ s.substring(offset + 1);
					break;
				
				case 1:
					s = s.substring(0, offset)+ 'C'+ s.substring(offset + 1);
					break;
				
				case 2:
					s = s.substring(0, offset)+ 'G'+ s.substring(offset + 1);
					break;
				
				case 3:
					s = s.substring(0, offset)+ 'T'+ s.substring(offset + 1);
					break;
			}
			num = num / 4;
		}
	
		return s;
	}

	public void dump() 
	{
		repeatedSeqs.remove(0);
		System.out.println(repeatedSeqs);
	}
	
	public static void main(String[] args)
	{
		String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		Solution sol;
		List<String> repeatedSeqs;
		
		System.out.print("string: ");
		System.out.println(s);

		sol = new Solution();
		repeatedSeqs = sol.findRepeatedDnaSequences(s);

		System.out.println("Repeated sub-sequences: ");
		System.out.println(repeatedSeqs);
		
	}
}
