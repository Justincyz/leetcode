/*189. Rotate Array
Easy
Given an array, rotate the array to the right by k steps,
 where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
*/

/*解题思路
这道题有个小要求，就是需要in-place replace。那么方法比较巧妙。都是需要考虑三次翻转
比如说我写的第一个。 如果按照example 1作为例子来看。 
先翻转前 n-k个和后k个
[4,3,2,1,7,6,5]
再整体进行一次翻转
[5,6,7,1,2,3,4]
就可以了。第二个和第一个差不多，只是反转的顺序不太一样而已。
有一个特别要注意的就是如果k大于数组的长度的话，要先取个余数。

*/
class Solution {
    public void rotate(int[] nums, int k) { 
        k = k%(nums.length);
        int n = nums.length;
        int s =0, e =n-k-1;
        while(s < e){
            swap(nums, s++, e--);
        }
        s = n-k; e = nums.length-1;
        while(s < e){
            swap(nums, s++, e--);
        }
        s = 0; e = nums.length-1;
        while(s < e){
            swap(nums, s++, e--);
        }
            
    }
    
    public void swap(int[]  nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}