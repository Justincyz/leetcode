/*150. Evaluate Reverse Polish Notation
Medium

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression 
would always evaluate to a result and there won't be any divide by zero operation.

Example 1:
Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:
Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
*/

/*解题思路
Polish Notation指的就是不用括号的情况下解出来式子。一个式子可以用树状来表示。
   +
  / \
 1   2
 这个意思就可以写成 (1,2,+), 意思就是 (1+2) =3的意思
 理解了这个做题就很简单了，就是一个stack来解决的问题。唯一要注意的是加法和乘法不用管
 顺序，但是减法和除法要管顺序。还有一点就是这个题目假设所有的输入都是valid的。
 但是没有考虑比如说(1,+)这种无法计算的问题。面试时需要问面试官是否所有的case都是有效的

*/

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();

        for(int i=0; i< tokens.length; i++){
            switch(tokens[i]){
                case "+":
                    stack.push(stack.pop()+stack.pop());
                    break;
                case "-":
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b-a);
                    break;
                case "*":
                    stack.push(stack.pop()*stack.pop());
                    break;
                case "/":
                    int c = stack.pop();
                    int d = stack.pop();
                    stack.push(d/c);
                    break;
                default:
                    stack.push(Integer.valueOf(tokens[i]));
            }        
        }
        return stack.pop();
    }
}