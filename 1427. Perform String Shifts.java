/*1427: Perform String Shifts
链接：https://leetcode.com/problems/perform-string-shifts/
Easy:
You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [direction, amount]:

direction can be 0 (for left shift) or 1 (for right shift). 
amount is the amount by which string s is to be shifted.
A left shift by 1 means remove the first character of s and append it to the end.
Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
Return the final string after all operations.

 

Example 1:
Input: s = "abc", shift = [[0,1],[1,2]]
Output: "cab"
Explanation: 
[0,1] means shift to left by 1. "abc" -> "bca"
[1,2] means shift to right by 2. "bca" -> "cab"

Example 2:
Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
Output: "efgabcd"
Explanation:  
[1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
[1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
[0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
[1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
 

Constraints:

1 <= s.length <= 100
s only contains lower case English letters.
1 <= shift.length <= 100
shift[i].length == 2
0 <= shift[i][0] <= 1
0 <= shift[i][1] <= 100
*/

/*解题思路
题目的意思是，给我们一个二维数组shift和一个字符串。字符串可以左移或者右移，详见例子。shift[i]中有两整数，shift[i][0]如果是0的话代表左移，1的话代表右移。shift[i][1]代表左移或者右移多少位。其实这道题目蛮简单的，我们不需要每一次都按照shift[i]指示的将字符串往左移或者右移字符串。而是一次性算出来一个值，然后将字符串一次性移到对应的位置。代码还是蛮简单的。

*/


class Solution {
    public String stringShift(String s, int[][] shift) {
        int totalShift = 0;
        for(int[] str: shift){
            totalShift += str[0] == 0? -str[1]:str[1];   
        }
        
        //因为字符串的移动是circular move，所以我们先对移动的位置取余数
        totalShift %= s.length();
        
        String result = "";
     
        if(totalShift < 0){
            totalShift = -totalShift;
            //左移，是将前边部分衔接到字符串尾部
            result = s.substring(totalShift, s.length())+s.substring(0, totalShift);
        }else{
        	//右移，是将后边部分放到字符串前部
            result = s.substring(s.length() - totalShift, s.length())+s.substring(0,s.length() - totalShift);
        }
        return result;
    }
}