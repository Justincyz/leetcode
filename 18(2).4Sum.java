/*18. 4Sum
链接：https://leetcode.com/problems/4sum/
Medium: 
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/

/*解题思路
每次固定两个指针，注意边界条件。
注意结果不能有重复

*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 4) return res;
        Arrays.sort(nums);
        
        for(int i=0; i< nums.length-3; i++){
            if(i> 0 && nums[i] == nums[i-1]) continue;//不能与后一个比较，而是和前一个比较
             for (int e = nums.length - 1; e >= i + 3; e--) {
                    if (e < nums.length - 1 && nums[e] == nums[e + 1]) continue;
                    int local = target - nums[i] - nums[e];
                    int j = i + 1;
                    int k = e - 1;
                    while (j < k) {
                        if (j > i + 1 && nums[j] == nums[j - 1]) { // j>i+1保证了在[0,0,0,0,0]，target=0这种情况下不会全部忽略 
                            j++; 
                            continue;
                        }
                        if (k < e - 1 && nums[k] == nums[k + 1]) {
                            k--;
                            continue;
                        }
                        if ((nums[j] + nums[k]) > local) k--;
                        else if ((nums[j] + nums[k]) < local)j++;
                        else
                            res.add(new ArrayList<Integer>(Arrays.asList(
                                    nums[i], nums[j++], nums[k--], nums[e])));
                    }
                }
            }
        
        return res;
    }
}