/*439. Ternary Expression Parser
链接：https://leetcode.com/problems/ternary-expression-parser/
Medium: 
Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).

Note:

The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.
Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"
*/

/*解题思路
这道题让我们解析一个三元表达式，我们通过分析题目中的例子可以知道，如果有多
个三元表达式嵌套的情况出现，那么我们的做法是从右边开始找到第一个问号，然后
先处理这个三元表达式，然后再一步一步向左推，这也符合程序是从右向左执行的特
点。那么我最先想到的方法是用用一个stack来记录所有问号的位置，(答案2)同时
将每个部分单纯存在一个list中，然后根据此问号的位置再回到list中找到对应的
位置，注意remove list中元素的顺序很重要，一定要从最右边往左边remove.

注意了，这道题目说明了数字也只会单个单个的出现，不会出现大于一位的数字，
但是第二个解法我写的一开始没注意，所以也包含了会出现大于一位的数字。

第二种解法就是用一个stack, 从右边往左边来遍历，存所有的字符，将原数组从后
往前遍历，将遍历到的字符都压入栈中，我们检测如果栈首元素是问号，说明我们当
前遍历到的字符是T或F，然后我们移除问号，再取出第一部分，再移除冒号，再取出
第二部分，我们根据当前字符来判断是放哪一部分进栈，这样遍历完成后，所有问号
都处理完了，剩下的栈顶元素即为所求:
*/


class Solution {
    public String parseTernary(String expression) {
        Stack<Character> s = new Stack();
        
        for(int i=expression.length() -1; i >=0 ; i--){
            char c = expression.charAt(i);
            if(c != '?'){
                s.push(c);
            }else{
                char n1 = s.pop();
                s.pop();
                char n2 = s.pop();
                if(expression.charAt(i-1) ==  'T'){
                    s.push(n1);
                }else{
                    s.push(n2);
                }
                i--;
            }
        }

        return s.pop()+"";
    }
}

//解法二，比较慢，因为每一次在list 中remove都需要移动元素。
class Solution {
    public String parseTernary(String expression) {
        Stack<Integer> s = new Stack();
        List<String> list = new ArrayList<>();
        for(int i=0; i< expression.length(); i++){
            char c = expression.charAt(i);
            if(c == '?'){
                s.push(i);
            }
            if(c >='0' && c <='9'){
                String sb = "";
                while(i < expression.length() && expression.charAt(i) >='0' && expression.charAt(i) <='9'){
                    sb+=(""+expression.charAt(i++));
                }
                i--;
                list.add(sb);
            }
            else{
                list.add(""+c);
            }
        }
       
        
        while(!s.isEmpty()){
            int p1 = s.pop(); // pop the position of ?
            if(list.get(p1-1).equals("T")){
                list.remove(p1+3);
                list.remove(p1+2);
                list.remove(p1);
                list.remove(p1-1);
            }else{
                list.remove(p1+2);
                list.remove(p1+1);
                list.remove(p1);
                list.remove(p1-1);
            }
        }
        
        
        return list.get(0);
    }
}