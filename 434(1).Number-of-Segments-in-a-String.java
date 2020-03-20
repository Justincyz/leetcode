/*434. Number of Segments in a String
链接：https://leetcode.com/problems/number-of-segments-in-a-string/
Easy: 
Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5
*/

/*解题思路
这道题目让我们计算segment的个数，空字符串不算segment。
这里我一开始直接return s.split(" ").length，结果错误。
这里有两个问题没有注意到，第一个是空的字符串要返回0，而用上面的办法
会返回1。
第二个问题是如果中间有多个空格，比如说"a    b  c"，
我门用split的时候会将以上字符串划分为"a"," "," ","b"," ","c"。
因为连续三个空格的话会分为两个空字符串。所以我们还是要遍历一次split
之后的string[]

*/


class Solution {
    public int countSegments(String s) {
     
        String[] list = s.split(" ");
        int cnt =0;
        for(String str: list){
            if(str.length()!=0) cnt++;
        }
        return cnt;
    }
}