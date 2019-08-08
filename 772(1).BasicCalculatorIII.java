/*772. Basic Calculator III
Hard: 
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / 
operators , open ( and closing parentheses ) and empty spaces . The 
integer division should truncate toward zero.

You may assume that the given expression is always valid. All 
intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
*/

/*解题思路
这道题目值得多看几次，相对于basic calculate I/II来说是一个融合。由于此题既有括号，又有乘除法，
我们知道括号是优先级最高的，但是好就好在我们可以将括号里的内容当作一个整体调用递归函数来处理。
而其他部分，就跟第二道一模一样了。我们还是分情况来处理遍历，我们需要几个变量，num 表示当前的数字，
sum 为最终的结果，sign 为操作符号，初始化为 '+'。当遇到数字的时候，我们将 num 自乘以 10 并加
上这个数字，这是由于可能遇到多位数，所以每次要乘以 10。那么我们就以括号为循环标志，遇到括号就循环。
最后再用一个while loop输出总和。

*/

class Solution {
    public int calculate(String s) {
        Queue<Character> q = new LinkedList<>();
        for(int i=0; i< s.length(); i++){
            q.add(s.charAt(i));
        }
        q.add('+');
        int amount = helper(q);
        return amount;
    }
    
    public int helper(Queue<Character> q){
        int  num=0;
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        while(!q.isEmpty()){
            char c = q.remove();
            if(Character.isDigit(c)){
                num = num*10 + (c-'0');
            }else if(c == '('){
                num = helper(q);
            }else if(c == ' '){
                continue;
            }else{
                if(sign == '+'){
                    stack.push(num);
                }else if(sign == '-'){
                    stack.push(-num);
                }else if(sign == '*'){
                    stack.push(stack.pop() * (num));
                }else if(sign == '/'){
                    stack.push(stack.pop() / (num));
                }
                sign = c;
                num = 0;                
                
                if(c == ')') break;
            }
        }
        int sum =0 ;
        while(!stack.isEmpty()) sum+=stack.pop();
        return sum;
    }
}