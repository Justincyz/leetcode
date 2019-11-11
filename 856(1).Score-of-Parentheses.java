/*856. Score of Parentheses
链接：https://leetcode.com/problems/score-of-parentheses/
Medium: 
Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: "()"
Output: 1
Example 2:

Input: "(())"
Output: 2
Example 3:

Input: "()()"
Output: 2
Example 4:

Input: "(()(()))"
Output: 6
*/

/*解题思路
两种解法

*/
//第一次自己写的，1ms
class Solution {
    public int scoreOfParentheses(String S) {
        Stack<String> st = new Stack<>();
        for(char c: S.toCharArray()){
            String s = String.valueOf(c);
            if(s.equals(")")){
                if(st.peek().equals("(")){
                    int v = 1; 
                    st.pop(); // pop掉当前相对应的左括号
                    st.push(String.valueOf(v));
                }else{
                    //先遇到了数字的情况
                    int val = 0;
                    //加入多个数字 比如 "(1(2"这样的排序
                    while(!st.isEmpty() && !st.peek().equals("(") && !st.peek().equals(")")){
                        val += Integer.valueOf(st.pop());
                    }
                    st.pop();
                    st.push(String.valueOf(2*val));//两倍的情况
                }
            }
            else{
                st.push(s); //其他情况
            }
        }
        int res =0;
        //解决"()()"这样的输入，1+1
        while(!st.isEmpty()) res+= Integer.valueOf(st.pop());
        
        return res;
    }
}

//0ms， 更快
class Solution {
    private int i;
    
    public int scoreOfParentheses(String S) {
        i = 0;
        return calc(S);
    }
    
    private int calc(String s) {

        int sum = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                i ++;
                sum += calc(s);
            } else {
                i ++;
                if (sum == 0) return 1;
                else return 2 * sum;
            }
        }
        return sum;
    }
}