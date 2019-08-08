/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
*/

/*
这道题的平均时间是 O(nlogn)但是 worst case 的时间是O(n). 因为这道题最大的不一样是数组中可能
存在重复的元素。比如说在array [0,0,0,0,0]中要找1的话，无法判断左右的走向。
*/
class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length-1;
        while(low <= high){
            int mid = low + (high-low)/2;
            if(nums[mid] == target) return true;
            if(nums[low] < nums[mid]){
                if(target>= nums[low] && target <= nums[mid]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }
            else if(nums[mid] < nums[low]){
                if(target >= nums[mid] && target <= nums[high]){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }else low++;
            /*最后这一步就是为了解决duplication的时候应该怎么处理*/

        }
        return false;
    }
}
