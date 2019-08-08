/*448. Find All Numbers Disappeared in an Array
Easy: 
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), 
some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this 
array.

Could you do it without extra space and in O(n) runtime? You may 
assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/

/*解题思路
题目告诉我们，有一个长度为n的数组，数组中每个数的范围是[1, n]。数组中有些出现了两次，
有些一次，有一些一次都没出现。要求出那些一次都没有出现的数字。这道题目没办法用 1-pass
来解。高票答案也都没有用1-pass解出来的。但是我们可以做到不用extra space。具体做法就是
把每一个数字放到它应该在的位置上，如果那个位置上的数字已经是正确的，那么说明当前这个是
多余的，那就移动到下一个位置。最后遍历一遍数组，把所有位置不对的位置都输出，就是答案了。

*/

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new LinkedList<>();
        for(int i=0; i<nums.length; i++){
            while(nums[i] != (i+1)){
                if(nums[nums[i]-1] == nums[i]){
                    break;
                } 
                int temp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[temp-1] = temp;
            }
        }
        
        for(int i=0; i<nums.length; i++){
            if(nums[i] !=i+1) res.add(i+1);
        }
        return res;
    }
}