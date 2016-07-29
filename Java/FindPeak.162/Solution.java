/* Use binary search 
 * Case1: num[i-1] < num[i] > num[i + 1], num[i] is a  peak exist 
 * Case2: num[i-1] > num[i] > num[i + 1], a peak exist between num[0] and num[i - 1] 
 * Case3: num[i-1] < num[i] < num[i + 1], a peak exist between num[i+1] and num[n] 
 * Case4: num[i-1] > num[i] < num[i + 1], a peak exist in both sides
 */
public class Solution 
{
	
	public int binarySearch(int[] nums, int head, int tail)
	{
		int idx;
		long preVal;
		long nextVal;

		idx = (head + tail) / 2;

		/* Corner case I: idx == 0, idx == (nums.length - 1) */
		if((idx == 0) && (idx == (nums.length - 1)))
		{
			return idx;
		}
		/* Corner case II: idx == 0 */
		else if(idx == 0)
		{
			if(nums[idx] > nums[idx + 1])
			{
				return idx;
			}
			else
			{
				return binarySearch(nums, 1, tail);
			}
		}
		/* Corner case III: idx == (nums.length - 1) */
		else if (idx == (nums.length - 1))
		{
			if(nums[idx] > nums[idx - 1]) 
			{
				return idx;
			}
			else
			{
				return binarySearch(nums, head, (nums.length - 2));
			}
		}
		else
		{
			preVal = nums[idx - 1];
			nextVal = nums[idx + 1];
            
			if((nums[idx] > preVal) && (nums[idx] > nextVal))
			{
				return idx;
			}
			else if((nums[idx] > preVal) && (nums[idx] < nextVal))
			{
				head = idx + 1;
				return binarySearch(nums, head, tail);
			}
			else if((nums[idx] < preVal) && (nums[idx] > nextVal))
			{
				tail = idx - 1;
				return binarySearch(nums, head, tail);
			}
			else
			{
				tail = idx;
				return binarySearch(nums, head, tail);
			}
		}
	}

	public int findPeakElement(int[] nums) 
	{
		return binarySearch(nums, 0, nums.length - 1);
	}
	
	public void dump(int[] nums) 
	{
		System.out.print("Input array: ");
		for(int num: nums)
		{
			System.out.print(num);
			System.out.print(",");
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		int[] nums = {3, 2, 1};
		int peak;
		Solution sol;
		
		sol = new Solution();

		sol.dump(nums);
		peak = sol.findPeakElement(nums);

		System.out.print("Peak ID: ");
		System.out.println(peak);
	}

}
