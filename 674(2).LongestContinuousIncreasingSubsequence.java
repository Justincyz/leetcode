/*674. Longest Continuous Increasing Subsequence
Easy: 
Given an unsorted array of integers, find the length of longest continuous 
increasing subsequence (subarray).

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
*/

/*解题思路
这道题目要求我们找最长的连续子数组。其他的都没什么难点，注意如果整个数组都是连续升序的，最后的
数值就储存在temp里面，所有最后返回的是Math.max(res, temp);

*/

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length ==0) return 0;
        int end = 1, res =0, temp=1;
        while(end < nums.length){
            if(nums[end]> nums[end-1]){
                temp++;
            }else{
                res = Math.max(res, temp);
                temp = 1;
            }
            end++;
        }
        
        return Math.max(res, temp);
    }
}