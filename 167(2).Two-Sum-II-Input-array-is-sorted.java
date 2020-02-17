/*167. Two Sum II - Input array is sorted
链接：https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
Medium: 
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Note:

Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.
Example:

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
*/

/*解题思路
这道题目就是two sum的简化版，注意结果是下标，且因为下标不是基于0的，所以
要加上1

*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int l=0, r = numbers.length-1;
        while(l < r){
            int sum = numbers[l]+numbers[r];
            if(sum == target){
                res[0] = l+1;
                res[1] = r+1;
                break;
            }else if(sum > target) r--;
            else l ++;
        }
        return res;
    }
}