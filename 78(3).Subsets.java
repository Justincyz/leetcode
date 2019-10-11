/*78. Subsets
链接：https://leetcode.com/problems/subsets/
Medium: 
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

/*解题思路


*/


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums.length==0) return list;
        helper(nums, list, 0);
        return list;
    }
    
    public void helper(int[] nums, List<List<Integer>> list, int index){
        if(index == nums.length){
            list.add(new ArrayList<>());
            return;
        }
        helper(nums, list, index+1);
        int size = list.size();
        for(int i=0; i< size; i++){
            ArrayList<Integer> cur = new ArrayList<Integer>(list.get(i));
            cur.add(nums[index]);
            list.add(cur);
        }
    }
}