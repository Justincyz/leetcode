/*32. Longest Valid Parentheses
Hard: 
Given a string containing just the characters '(' and ')', find the 
length of the longest valid (well-formed) parentheses substring.

Example 1:
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"

Example 2:
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
*/

/*解题思路
这道题目问我们，最长有效组合的括号对有多少。第一种方法是常规方法，用的是stack来辅助我们做。
注意每一次存放的是坐标。

*/

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack();
        int start = 0, res = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '(') stack.push(i);
            else{
                //如果为空的话，说明我们在包括i位以前不可能再有新的匹配了，所以起始位置是start = i+1
                if(stack.isEmpty()) start = i+1;
                else{
                    //如果有对应的左括号匹配的话，那么首先Pop出来
                    stack.pop();
                    //对应的是刚才Pop()出来的是不是最后一个，如果是的话总长度为i-start+1
                    res = stack.isEmpty() ? Math.max(res, i-start+1): Math.max(res, i-stack.peek());
                }
            }
        }
        
        return res;
    }
}

/*
这是用dp的方法做出来的，很值得看一下，dp[i]位储存的是到第i位的最长子字符串，
什么叫最长子字符串，比如说 "()(())"
截止到第2个符号，我们可以组成最长为2的子字符串。dp[2] = 2. 
dp[3] = 0因为单独的一个左括号并不能构成括号对，
dp[4] = 0，同理。
dp[5] = 2，此时一个左括号可以和前一个左括号构成一对，所以长度为2. 
dp[6] = 4，这个时候就是计算的精髓了。因为我们要找到能否和当前的右括号
匹配的左括号，那么如果有可以匹配的左括号会出现在哪里呢?答案是j = i - 2-dp[i-1]。  
dp[i-1]相当于前一位可以匹配的最长括号长度，因为我们必须跳过已经匹配好的，去找有可能
还没有匹配的左括号，因为是长度，所以要再减去1。需要再向前查找一个，所以要再减去一。
此时如果第j为左括号，那么说明当前的右括号可以找到一个左括号与之匹配。那么再计算一次长度
并且更新dp[i]就好了
*/

class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length(), res =0;
        int[] dp = new int[n+1];
        
        for(int i=1; i<=n; i++){
            /*这里的j相当于计算的就是上一种方法中的start。先计算当前子序列最长的
            起始点在哪里*/
            int j = i-2-dp[i-1];

            if(s.charAt(i-1) =='(' || j<0 || s.charAt(j) == ')') continue;
            else{
                dp[i] = dp[i-1]+2+dp[j];
                res = Math.max(res, dp[i]);
                    
            }
        }
        return res;
        
    }
}