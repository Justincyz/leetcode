/*1249. Minimum Remove to Make Valid Parentheses
链接：https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
Medium: 
Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Example 4:
Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"
 

Constraints:
1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.
*/

/*解题思路
题目给出了一个字符串，里面有小写字母和一些小括号'('和')'。让我们移除最少的括号
使得字符串当中的括号都可以被match. 其实这就是 20. Valid Parentheses 的小小
升级版。我们还是使用stack来帮.stack当中存的是括号的位置。通过遍历字符串，我们
stack最后留下来的都是不可以被别的括号match的剩余括号位置。我们将这些括号从字符串
当中抹掉就可以了。

TIME & SPACE : O(n)

*/


class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] str = s.toCharArray();
        
        for(int i=0; i< str.length; i++){
            char c = str[i];
            if(c == ')' || c =='('){
                if(c == ')' && !stack.isEmpty() && str[stack.peek()] == '('){
                    stack.pop();
                }else{
                    stack.push(i);
                }
            }
        }
        while(!stack.isEmpty()) str[stack.pop()] = '';
        StringBuffer sb = new StringBuffer(str);
        for(char c: str){
            if(c!=' ') sb.append(c);
        }
        return sb.toString();
    }
}