/*344. Reverse String
链接：https://leetcode.com/problems/reverse-string/
Easy: 
Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.

 

Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
*/

/*解题思路
two pointers, 很简单的题目

*/

class Solution {
    public void reverseString(char[] s) {
        int start = 0, end = s.length-1;
        while(start <= end){
            char t = s[start];
            s[start] = s[end];
            s[end] = t;
            start++;
            end--;
        }
    }
}

//Veeve这个公司需要用递归来做
class Solution {
    public void reverseString(char[] s) {
        int start = 0, end = s.length-1;
        helper(start, end, s);
    }
    
    public void helper(int start, int end, char[] s){
        if(start >= end) return;
        char t = s[start];
        s[start] = s[end];
        s[end] = t;
        helper(start+1, end-1, s);
    }
}