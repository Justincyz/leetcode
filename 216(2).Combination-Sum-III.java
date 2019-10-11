/*216. Combination Sum III
链接：https://leetcode.com/problems/combination-sum-iii/
Medium: 
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/

/*解题思路
dfs+ backtrack

*/
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, k, n, 1, new ArrayList());
        return res;
    }
    
    public void helper(List<List<Integer>> res, int k, int n, int idx, List<Integer> list){
        if(k < 0 || n < 0) return;
        if(k==0 && n == 0){
            List<Integer> t = new ArrayList<>(list);
            res.add(t);
            return;
        }
        
        for(int i=idx; i<=9; i++){
            list.add(i);
            helper(res, k-1, n-i, i+1, list);
            list.remove(list.size()-1);
        }
    }
}