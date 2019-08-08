/*
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3 
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.


*/

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
    	HashMap<Long, Long> map = new HashMap<>();
    	map.put((long)0, (long)1);
    	long pre = 0;
        int total=0;
    	for(int i=0; i< nums.length; i++){
    		pre +=nums[i];
    		int l = lower;
    		while(l <=upper){
    			if(map.containsKey(pre-l)){
    				total+= map.get(pre-l);
    			}
    			l++;
    		}
    		map.put((long)pre, map.getOrDefault(pre, (long)0)+(long)1);
    	}
    	return total;
    }
}
/*
用的就是pre-sum的办法，只需要知道每一次计算到当前的presum时，在这之前可以有多少和当前组合之后在给到的范围之内
*/