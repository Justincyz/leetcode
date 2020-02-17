/*224. Basic Calculator
链接：https://leetcode.com/problems/basic-calculator/
Hard: 
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
*/

/*解题思路
这道题让我们实现一个基本的计算器来计算简单的算数表达式，而且题目限制了
表达式中只有加减号，数字，括号和空格，没有乘除，那么就没啥计算的优先级
之分了。于是这道题就变的没有那么复杂了。我们需要一个栈来辅助计算，用个变
量sign来表示当前的符号，我们遍历给定的字符串s，如果遇到了数字，由于可能
是个多位数，所以我们要用while循环把之后的数字都读进来，然后用sign*num来
更新结果res；如果遇到了加号，则sign赋为1，如果遇到了符号，则赋为-1；如
果遇到了左括号，则把当前结果res和符号sign压入栈，res重置为0，sign重置为
1；如果遇到了右括号，结果res乘以栈顶的符号，栈顶元素出栈，结果res加上栈
顶的数字，栈顶元素出栈。

这道题的坑也比较多，比如说"1  + 1"这种中间有空位的，比如说 1+(((2)))
这种中间有多个括号的，再比如说(1)-(2)这种两边都有括号的，所以一定要注意。
*/
class Solution {
    public int calculate(String s) {      
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        char[] list = s.toCharArray();
        int res =0, sign =1;
        while(idx <list.length){
            if(list[idx] == '('){
                //如果遇到了连续的左括号，比如说(((3)))，这样最后结果也就是多个0*1相加，所以也就是0，不会有影响
                stack.push(res);
                stack.push(sign);
                res=0;
                sign=1;              
            }else if(list[idx]==')'){
                res = res*stack.pop()+stack.pop(); 
            }else if(Character.isDigit(list[idx])){
                int num =list[idx]-'0';
                while(idx+1 < list.length && Character.isDigit(list[idx+1])){
                    num*=10;
                    num+=(list[idx+1]-'0');
                    idx++;
                }
                res += (num)*sign;
            }else if(list[idx] == '+'){
                sign = 1;
            }else if(list[idx] == '-'){
                sign = -1;
            }
            idx++;
        }
        
        
        return res;
    
    }
}