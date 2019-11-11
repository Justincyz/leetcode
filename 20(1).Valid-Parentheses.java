/*20. Valid Parentheses
链接：https://leetcode.com/problems/valid-parentheses/
Easy: 
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
*/

/*解题思路
这道题目眼睁睁的看着从Medium下降到easy

*/


class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i< s.length();i++){
            if(s.charAt(i) ==')'){
                if(stack.isEmpty() ||stack.pop() != '(') return false;
            }
            else if(s.charAt(i) ==']'){
                if(stack.isEmpty() || stack.pop() != '[') return false;
            }
            else if(s.charAt(i) =='}'){
                if(stack.isEmpty() ||stack.pop() != '{') return false;
            }
            else stack.push(s.charAt(i));
            
        }
        if(stack.isEmpty()) return true;
        else return false;
    }
}





class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(c == ']'){
            	//如果是判断不存在的话记得要把新的加进去
                if(!stack.isEmpty() && stack.peek() == '['){
                    stack.pop();
                }
                else{
                     stack.push(c);
                }
            }else if(c == ')'){
                if(!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }else{
                     stack.push(c);
                }
            }else if(c == '}'){
                if(!stack.isEmpty() && stack.peek() == '{'){
                    stack.pop();
                }else{
                     stack.push(c);
                }
            }else stack.push(c);
        }
        
        return stack.size() == 0? true: false;
    }
}