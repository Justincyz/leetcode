/*34. Find First and Last Position of Element in Sorted Array
链接：https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
Medium: 
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/

/*解题思路


*/


class Solution {
	//用两个global的值来当做最后的范围
    int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        
        
        binarySearch(nums, target, 0, nums.length-1);
        //如果并没有找到数值的话
        if(left == Integer.MAX_VALUE) left = -1;
        if(right == Integer.MIN_VALUE) right = -1;
        res[0] = left; res[1] = right;
        
        return res;
    }
    
    public void binarySearch(int[] nums, int target, int s, int e){
        if(s > e) return;
        if(s == e){
            if(nums[s] == target){
                left = Math.min(left, s);
                right = Math.max(right, s);
            }
            return;
        }
        int mid = (e+s)/2;

        if(nums[mid] == target){
            left = Math.min(left, mid);
            right = Math.max(right, mid);
            /*这里就是判断，如果hit中其中一个数字，接下来的走向了。
            解释一下s <left 和 e > right是什么意思。最极端的情况是
            整个数组都是同一个元素。那么我们为了让二分不会搜索整个
            数组，我们每一次只往比left小和比right大的地方搜索，
            中间部分就不用搜索了
            */
            if(mid-1>=0 && nums[mid-1] == target && s<left){
                binarySearch(nums, target, s, mid);
            }
            if(mid+1 < nums.length && nums[mid+1]==target && e > right){
                binarySearch(nums, target, mid+1, e);
            }
        }else if(nums[mid] > target){
            binarySearch(nums, target, s, mid);
        }else{
            binarySearch(nums, target, mid+1, e);
        }
    }
}