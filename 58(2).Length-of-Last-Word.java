/*58. Length of Last Word
链接：https://leetcode.com/problems/length-of-last-word/
Easy: 
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5
*/

/*解题思路
找到最后一个单词的长度

*/


class Solution {
    public int lengthOfLastWord(String s) {
        String[] list = s.split(" ");
        if(list.length == 0) return 0;
        return list[list.length -1].length();
    }
}