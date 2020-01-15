/*77. Combinations
链接：https://leetcode.com/problems/combinations/
Medium: 
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

/*解题思路 Backtrack
这道题目是一道很经典的backtrack的题目。
题目要求找到1到n之间，组合长度为k个元素的所有组合。那么循环结束的条件
就是当k为0的时候就将本次循环中的元素加入到结果中。

*/
class Solution {
    List<List<Integer>> res;
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        
        List<Integer> list = new ArrayList<>();
        helper(list, n, k, 1);
        return res;
    }
    
    public void helper(List<Integer> list, int n, int k, int idx){
        if(k == 0){
            List<Integer> t = new ArrayList<>(list);
            res.add(t);
            return;
        }
        /*
		这里有一个可以大大加速的做法，原来for循环结束的条件是i<=n。但是
		事实上因为受到k个字符长度的限制，根本后面的k个元素是无法取到的。
		所以将循环结束条件设置为 i<=n-k+1的话可以节约很多的时间。
        */
        for(int i = idx; i<=n; i++){
            list.add(i);
            helper(list, n, k-1, i+1);
            list.remove(list.size()-1);
        }
    }
}