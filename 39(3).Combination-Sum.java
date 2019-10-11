/*39. Combination Sum
链接：https://leetcode.com/problems/combination-sum/
Medium: 
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

/*解题思路
很简单的 dfs + backtracking的做法

*/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);//这里先sort一次，然后接下来如果某一位的值大于target, 那么之后所有的数都可以不用管了
        helper(res, candidates, target, new ArrayList(), 0);
        return res;
    }
    
    public void helper(List<List<Integer>> res, int[] candidates, int target, List<Integer> list, int idx){
        if(target == 0){
            List<Integer> temp = new ArrayList<>();
            copy(temp, list);
            res.add(temp);
            return;
        }
        
        for(int i=idx; i< candidates.length; i++){
            if(candidates[i] > target) break;
            list.add(candidates[i]);
            helper(res, candidates, target - candidates[i], list, i);
            list.remove(list.size()-1);
        }
        
    }
    
    public void copy(List<Integer> temp, List<Integer> list){
        for(int i: list){
            temp.add(i);
        }
        return;   
    }
}