/*162. Find Peak Element
链接：https://leetcode.com/problems/find-peak-element/
Medium: 
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
Note:

Your solution should be in logarithmic complexity.
*/

/*解题思路
这道题目给出了一个整形数组，元素都是正整数。定义了一个peak element的概念，就是说
某个数字左右两个数字都比他小。让我们输出这个元素，因为数组中可能存在多个这样的元素，
只要输出一个就可以。有一个很重要的corner case我们要注意，就是当原数组中只有一个数字，
就直接输出这个数字，因为题目假设两端都是无限小的数字。

第一种解法就是最简单的O(N)的解法，遍历一遍数组就可以了，无需多言

*/
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1) return 0;
        for(int i=0; i<nums.length; i++){
            if(i>0 && i< nums.length-1){
                if(nums[i]>nums[i-1] && nums[i]> nums[i+1]){
                    return i;
                }
            }else if(i > 0){
                if(nums[i] > nums[i-1]) return i;
            }else{
                if(nums[i] > nums[i+1]) return i;
            }
        }
        return nums[0];
    }
}

/*
这道题目的follow-up是让我们在Log的时间复杂度解出这个题目。
我一开始很费解，这样没有规律的数组是怎么可能做到在log的时间复杂度内求解。
网上的解释是这样的:
"那么我们就要考虑使用类似于二分查找法来缩短时间，由于只是需要找到任意一个峰值，
那么我们在确定二分查找折半后中间那个元素后，和紧跟的那个元素比较下大小，如果大于，
则说明峰值在前面，如果小于则在后面。这样就可以找到一个峰值了"

我首先想到的反例就是:[1,4,2,3,4]，这样看起来peak是在左边，是nums[1] = 4.但是
查找的方向会去右边。后来仔细看了题目，发现两个细节。第一，我们假设了每个数字和相邻
的数字都会不一样。第二，我们nums[-1]和nums[n]的位置是负无穷。所以对于我举的这个
例子来说，nums[4]=4也是一个peak element.
换句话来说，我们其实要找到一个递增的趋势。然后这个递增的趋势最后一定会递减(因为
我们的判断和头尾都是负无穷的关系)
所以每次我们都比较中间的两个元素是否是递增的关系。
*/

class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return right;
    }
}