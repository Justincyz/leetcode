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

//几乎一样的做法
class Solution {
    public void sortColors(int[] nums) {
        if(nums.length ==0) return;
        int end = nums.length-1, start = 0, move = 0;
        while(move <= end){
            if(nums[move] == 0){
                swap(nums, start, move);
                start++;
            }else if(nums[move] ==2){
                swap(nums, end, move);
                end--;
                move--; //这里非常关键，需要往后退一步，再检查一次新被交换的那位数
            }                     
            move++;
        }
        return;
    }
    
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}


/*
第三次做这道题目的一点新的，我们每一次将正确的数字交换到left指针或者right
指针之后，并不知道此时left指针或者right指针指向的数字是什么。所以当这个数字
被转换到move这个指针所指的位置时，我们不能急着把move往后移动，而是需要再
检查一次这个move是什么元素。
*/
class Solution {
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length-1, move = 0;
        
        while(move <= right){
            if(nums[move] == 0){
                swap(nums, move, left);
                left++;
                /*如果此时move和left指向同一个元素且都为0的话，那么我们
                如果将left往右移动一位的时候，不能让move小于left, 所以
                此时我们要再移动Move一位。
                */
                if(move < left) move++;
            }else if(nums[move] == 1){
                move++;
            }else{
                swap(nums, move, right);
                right--;
               
            }
        }
       
    }
    
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}