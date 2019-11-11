/*166. Fraction to Recurring Decimal
链接：https://leetcode.com/problems/fraction-to-recurring-decimal/
Medium: 

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"
*/

/*解题思路
这道题还是比较有意思的，开始还担心万一结果是无限不循环小数怎么办，百度之后才发现原来可以写成
分数的都是有理数，而有理数要么是有限的，要么是无限循环小数，无限不循环的叫无理数，例如圆周率p
i或自然数e等。由于还存在正负情况，处理方式是按正数处理，符号最后在判断，那么我们需要把除数和被除数取绝对值，那么问题就来了：由于整型数int的取值范围是-2147483648～2147483647，而对-2147483648取绝对值就会超出范围，所以我们需要先转为long long型再取绝对值。那么怎么样找循环呢，肯定是再得到一个数字后要看看之前有没有出现这个数,只要出现过这个数就一定代表了此时进入了循环，不存在比如说0.1231212312这样的(12312)循环。为了节省搜索时间，我们采用哈希表来存数每个小数位上的数字。还有一个小技巧，由于我们要算出小数每一位，采取的方法是每次把余数乘10，再除以除数，得到的商即为小数的下一位数字。等到新算出来的数字在之前出现过，则在循环开始出加左括号，结束处加右括号。
为什么可以这样，其实就是模仿了平时我们用除法来计算小数的过程。如果某一位不够除了，那么先借一位，也就是乘10再除。
*/


class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        //用first代表小数点前面的数值，second=代表了小数点后面的数值
        StringBuffer first = new StringBuffer();
        first.append(((numerator >0) ^ (denominator >0)) ? "-" :"");        
        long num = Math.abs((long)numerator), den = Math.abs((long)denominator);
  
        first.append(String.valueOf(Math.abs(num)/Math.abs(den)));

        
        if(num%den == 0) return first.toString();
        long res = num % den;
        first.append(".");
        
        Map<Long, Integer> map = new HashMap<>();
        StringBuffer second = new StringBuffer();
        
        int index =0;//index代表的是每一个数字在second中的第几位
        while(res !=0){//当余数一直不为0时
            if(map.containsKey(res)){
                int idx = map.get(res);
                second.insert(idx, '(');
                second.append(')');
                return first.toString()+second.toString();
            }

            map.put(res, index);
            res*=10;
            second.append(res/den);
            res = res%den;
            index++;
        }
        
        return first.toString()+second.toString();
    }
}