/*227. Basic Calculator II
Medium: 

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / 
operators and empty spaces . The integer division should truncate toward zero.

Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5
*/

/*解题思路
这道题内的所有运算只有加减乘除，暂时还没有涉及到括号和优先级的算法。
这道题有两种思路来解，第一就是先算一次优先级高的算术。将优先级低的先放到stack中。
比如说， 3+2*2-4。放入stack中的数字是[3,4,-4].然后再用一个循环累加stack中的值。
这种方法虽然可以beats 80%，但是我想的是可以用一个loop就计算完这个式子。那么我们可以用第二种方法来做。

*/
class Solution {
    public int calculate(String s) {
        char[] list = s.toCharArray();
        Stack<Integer> stack = new Stack<>();

        int num = 0;
        char op = '+';
        for(int i=0; i< list.length; i++){
             char c = list[i];
             if(Character.isDigit(c)){
                num= num*10+ (c-'0');
            }
            if(c == '*' || c== '/'|| c=='+' || c=='-' || i== list.length-1){
                if(op == '+') stack.push(num);
                if(op == '-') stack.push(-num);
                if(op == '*' || op == '/'){
                    int val = op =='*'? stack.pop()*num : stack.pop()/num;
                    stack.push(val);
                }
                num = 0;
                op = c;
            }
           
        }
        int total = 0;
        for(int v: stack) total+=v;

        return total;
    }
}

/*
因为我们没有办法确定 3+2*2里面到底第二个2是应该和前面的3累加还是说和后面的2累乘。所以我们先用一个tail
来记住前一个出现的数字。而不是直接加到结果里。这样当出现乘法或者除法的时候，我们就可以直接获取上一位的数字。
如果是加法减法的话，那么我们先把上一位的数字加上。再把当前数字赋值给tail。
*/

class Solution {
    public int calculate(String s) {
    char operator = '+';
    char[] charArray = s.toCharArray();
    int num = 0，tail = 0， res = 0;
    int n = charArray.length;


    for(int i = 0 ; i < n; i++){
      char c = charArray[i];
      if(c == ' '){continue;}
      if (Character.isDigit(c)){
        num = (int) (c-'0');
        while(i + 1 < n && Character.isDigit(charArray[i+1])){
          num = num * 10 + (int)(charArray[i+1] - '0');
          i++;
        }
        switch (operator){
          case '+':
            res +=tail;//注意这里加的是tail，也就是加号前面的，安全的那位数
            tail = num;
            break;
          case '-':
            res += tail;
            tail = -num;
            break;
          case '*':
            tail*=num;
            break;
          case '/':
            tail/=num;
            break;
        }
      } 
      else {
        operator = c;
      }
    }
    return tail + res;
    }
}