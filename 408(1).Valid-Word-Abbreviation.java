/*408. Valid Word Abbreviation
链接：https://leetcode.com/problems/valid-word-abbreviation/
Easy: 
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
*/

/*解题思路
注意细节，这里我们将数字展开变成星号，比如说 w2d -> w**d这样。只要碰到星号
代表可以匹配任意字符。

*/


class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        StringBuffer sb = new StringBuffer();
        
        for(int i=0; i< abbr.length(); i++){
            char c = abbr.charAt(i);
            if(c >='0' && c <= '9'){
                if( c== '0') return false;
                StringBuffer temp = new StringBuffer();
                while(i < abbr.length() && abbr.charAt(i) >='0' && abbr.charAt(i) <= '9'){
                    temp.append(abbr.charAt(i++));
                }
                i--;
                int num = Integer.valueOf(temp.toString());
                if(num > word.length()) return false;
                while(num-- >0) sb.append("*");
            }else{
                sb.append(c);
            }
        }
       
        if(sb.length() != word.length()) return false;
        
        for(int i=0; i< word.length(); i++){
            if(word.charAt(i) != sb.charAt(i) && sb.charAt(i) != '*'){
                return false;
            }
        }
        
        return true;
    }
}