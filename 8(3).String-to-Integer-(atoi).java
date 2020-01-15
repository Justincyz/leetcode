/*8. String to Integer (atoi)
链接：https://leetcode.com/problems/string-to-integer-atoi/
Medium: 
Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
Example 1:

Input: "42"
Output: 42
Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.
*/

/*解题思路
这道题目其实不难，就是corner case太多了导致lc 上面踩得人很多。
有一些这样的corner case
1. "+"/"-"
2. "asdas"
3. "asd 897"
4. "990909871320" //超过integer.max_value
5. ""

只要注意一下这一些就好了。那就是一道简单的提取字符串中的数字


*/
class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        if(str.length() == 0) return 0;
        int res = 0, sign = 1, idx =0;
        
        if(str.charAt(0) !='+' && str.charAt(0) !='-' && (str.charAt(0) >'9' || str.charAt(0)<'0' )) return 0;
        
        if(str.charAt(0) =='-'){
            sign = -1;
            idx++;
        }else if(str.charAt(0) =='+'){
            idx++;
        } 
       
        while(idx < str.length() && str.charAt(idx) <='9' && str.charAt(idx)>='0' ){
            if(res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && str.charAt(idx) >'7')){
                return sign==1 ? Integer.MAX_VALUE:  Integer.MIN_VALUE;
            }
            res = res*10+ (str.charAt(idx)-'0');
            idx++;
        }
            
        return res*sign;
    }
}