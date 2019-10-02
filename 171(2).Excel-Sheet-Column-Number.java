/*171. Excel Sheet Column Number
链接：https://leetcode.com/problems/excel-sheet-column-number/
Easy: 
Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
Example 1:

Input: "A"
Output: 1
Example 2:

Input: "AB"
Output: 28
Example 3:

Input: "ZY"
Output: 701
*/

/*解题思路
简单的累加

*/


class Solution {
    public int titleToNumber(String s) {
        
        int res = 0, len = s.length()-1;
        for(int i=s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            //c-'A'+1记得要加一，假如c是'A'的话，A-A是0，
            res+= (Math.pow(26, len-i)*(c-'A'+1));
        }
        
        return res;
    }
}