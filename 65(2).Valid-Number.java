/*65. Valid Number
Hard
链接: https://leetcode.com/problems/valid-number/

Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

*/

/*解题思路
这道题真的很无聊

*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
   public boolean isNumber(String s) {
        s = s.trim();
        if(s.equals("")) return false;
        Pattern p = Pattern.compile("^[+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))([eE][+-]?\\d+)?");
        Matcher m = p.matcher(s);
        return m.matches();
    }
}


/*
^                     Start of regular expression
[+,-]?                Optional sign 
(\d+(\.\d*)?)         Atleast one digit followed by optional (. followed by 0 or more characters )
(\.[0-9]+)            This is to handle cases like .3 which is not handled by above expression 
(e[+,-]?\d+)?         Optional (e followed by an optional sign , followed by one or more numbers)


*/