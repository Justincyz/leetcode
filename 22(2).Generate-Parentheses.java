/*22. Generate Parentheses
链接：https://leetcode.com/problems/generate-parentheses/
Medium: 
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/

/*解题思路
题目让我们完美匹配括号，就是每一个右括号的左边都有一个与之匹配的左括号。
这道题一开始我觉得dfs肯定很慢，结果一写发现beats 98%。那就是一道很简单的bfs的题目。循环中的balance指的是如果匹配左括号则
加一，如果匹配右括号则减一。如果某一时刻变为负数，那么说明在这之前我们
循环的括号中肯定有一个右括号无法被匹配上，直接return。如果我们的字符串
长度达到了两倍的n，那么我们判断balance是否为0，也就是可以被正确匹配上，
如果同时满足这两个条件，则将字符串加入到我们的列表中

*/


class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        
        helper("(", list, 1, n);
        return list;
    }
    
    public void helper(String s, List<String> list, int balance, int n){
        if(s.length() == 2*n){
            if(balance == 0){
                list.add(s);
            }
            return;
        }
        if(balance < 0) return;
        
        helper(s+'(', list, balance+1, n);
        helper(s+')', list, balance-1, n);
    }
}