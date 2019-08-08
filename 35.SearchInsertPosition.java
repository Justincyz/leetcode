/* 35. Search Insert Position
Easy
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 4:

Input: [1,3,5,6], 0
Output: 0
*/
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums.length ==0 ) return 0;
        int max = nums.length-1;
        int min = 0;
        while(min < max){
            int mid = min + (max-min)/2;
            if(nums[mid] < target) min = mid+1;
            else max = mid;
        }
        if(target > nums[min]) min++;//注意，如果是target< nums[min]的话那么直接插入，否则要插入到当前位的后面一位
       
        return min;
    }
}