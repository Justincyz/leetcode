/*283. Move Zeroes
链接：https://leetcode.com/problems/move-zeroes/
Easy: 
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

/*解题思路
slow指针之前的都是排序好的非0数字，fast指针用来遍历数组。

这种需要ordered, 就一定是按照顺序排列好

*/


class Solution {
    public void moveZeroes(int[] nums) {
        int fast = 0, slow = 0;
        while(fast < nums.length){
            if(nums[fast] != 0){
                swap(nums, fast, slow);
                slow++;
            }
            fast++;
        }
    }
    
    public void swap(int[] nums, int fast, int slow){
        int t = nums[fast];
        nums[fast] = nums[slow];
        nums[slow] = t;
    }
}