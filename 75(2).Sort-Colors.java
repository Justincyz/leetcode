/*75. Sort Colors

链接：https://leetcode.com/problems/sort-colors/
Medium: 

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
*/

/*解题思路
这道题目让我们将0,1,2三个数字按照顺序排列好，代表三种不同颜色的气球。其实这道题目我们可以把它
当做将两个颜色排序的问题来看，只不过每一次移动的指针有两个不同的位置与其交换。 我们定义start指针
永远指向0, end指针永远指向2, 那么这两个数字排列好的话其实剩下的1肯定也是排列好的。
我们从头开始遍历原数组，如果遇到0，则交换pt指向的该值和 red 指针指向的值，并将 start 指针后移一位。若遇到2，则交换该值和 end 指针指向的值，并将 blue 指针前移一位。若遇到1，则继续遍历。
*/
class Solution {
    public void sortColors(int[] nums) {
        int start = 0, end = nums.length-1, pt=0;
        while(pt <= end){//注意这里的结束条件
            if(nums[pt] == 0){
                swap(nums, start, pt);
                start++;
                pt = start;
            }else if(nums[pt] == 2){
                swap(nums, end, pt);
                end--;
            }else{
                pt++;
            }
        }
    }
    
    public void swap(int[] nums, int a, int b){
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}