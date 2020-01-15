/*33. Search in Rotated Sorted Array
链接：https://leetcode.com/problems/search-in-rotated-sorted-array/
Medium: 
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/

/*解题思路
题目说，现在有一个排序好的数组，但是可能往左或者往右移动过，让我们在O(logn)的
时间复杂度中找到target元素在那一位，如果target不存在在数组当中的话，那么就返回-1.
这个里面其实还是用寻常的二分法来做，不管在什么情况下，最起码可以保证有一半的数组
是排序正常的。我们最方便的办法就是先判断一下那一半数据有问题，假设左半边数据是错乱
的，说明右半边数据是正常排序的，那我们就看target是不是在右半边的范围中，如果在的话
我们就往右半边去寻找，如果不在右半边的范围内的话，那说明数据一定在左半边。那就在左半边
寻找。反正记住一定有一半的数据是排列正常的。

*/
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        //最后是左边小于等于右边，因为有可能整个数组只有一个元素。
        while(l <= r){
            int mid = l+(r-l)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < nums[l]){//如果左半边元素错乱的话
                if(target > nums[mid] && target <= nums[r]){//如果target在右半边的话
                    l = mid+1;
                }else{
                    r = mid;
                }
            }else{
                if(target >= nums[l] && target  < nums[mid]){
                    r = mid;
                } else{
                    l = mid+1;
                }
            }
        }
        
        return -1;
    }
}