/*40. Combination Sum II
链接：https://leetcode.com/problems/combination-sum-ii/
Medium: 
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
*/

/*解题思路
这道题目是39: Combination Sum的拓展。这道题目里面同一个数字不允许重复使用。有一个重点
标注在了代码里面。

*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
     List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
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
            /*一开始我写的是 i>0，但是实际上这样子不行。我们只是不可以重复使用
            同一个数字，但是不同数字如果值相同的话是可以重复使用的。同时在结果中
            不可以出现重复的结果。这两点看起来有点冲突，但是实际上可以这样考虑。
            对于例子1来说，我们可以使用两个1，用[1,1,6]来组成结果，但是不同的1却可以和同样的一个7组合成为8，所以这种结果是我们需要去重的。之所以用i> idx
            而不是i >0, 原因是这样。因为i是递归到下一层之前添加的最后一个元素，
            所以对于下一层来说，如果有重复却相同的数字被考虑了两次，就会造成
            duplication。但是对于本层来说，是可以考虑相同值的不同元素的。听起来好像
            很复杂，实际上很简单，仔细思考下就好。

            */
            if(i>idx && candidates[i-1] == candidates[i]) continue;
            
            list.add(candidates[i]);
            helper(res, candidates, target - candidates[i], list, i+1);
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