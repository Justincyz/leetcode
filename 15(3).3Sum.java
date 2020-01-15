/*15. 3Sum
链接：https://leetcode.com/problems/3sum/
Medium: 
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

/*解题思路
非常经典的题目，让我们在无序数组中找到三个数相加为0(可以扩展到相加为x)。
这三个数的组合不能出现重复。
我们先把数组排序，固定住一个值，使用另外两个指针在固定值的右边来
查找组合的和是否为0。主要是注意细节问题，比如说去重。

*/


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        //加快速度
        if(nums.length ==0 || nums[0] > 0 || nums[nums.length -1] <0){
            return res;
        }
        for(int i=0; i< nums.length; i++){
        	//保证左边不出现重复
            if(i>0  && nums[i] == nums[i-1]){      
                continue;
            } 
            if(nums[i] > 0) break; //加快判断速度
            int l = i+1, r = nums.length-1;
            while(l < r){    
            	//保证右边不出现重复，注意是r < nums.length-1, 因为我们要保证相同组合下有一个被加入到结果中
                if(r < nums.length-1 && nums[r] == nums[r+1]){
                    r--;
                    continue;
                } 
                int total = nums[i]+nums[l]+nums[r];
                if(total == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]); list.add(nums[l]); list.add(nums[r]);
                    res.add(list);
                    l++;
                    r--;
                }else if(total > 0){
                    r--;
                }else{
                    l++;
                }
            }
            
            
        }
        return res;
    }
}