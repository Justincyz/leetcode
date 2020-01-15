/*38. Count and Say
Easy
链接: https://leetcode.com/problems/count-and-say/

The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so recursively, in other words from the previous member read off the digits, counting the number of digits in groups of the same digit.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:
Input: 1
Output: "1"
Explanation: This is the base case.

Example 2:
Input: 4
Output: "1211"
Explanation: For n = 3 the term was "21" in which we have two groups "2" and "1", "2" can be read as "12" which means frequency = 1 and value = 2, the same way "1" is read as "11", so the answer is the concatenation of "12" and "11" which is "1211".

*/

/*解题思路
这道题比较无聊，主要是要看懂题目。从base case开始，也就是1，念一个一，就是11，就变成
第二个数字。然后就是2个1，就变成21。然后对于第一个2来说，是一个2，就是12，第二个1来说
就是一个1，所以连在一起变成1211就是第三位数。以此类推，最后输出第n位的结果。

*/

class Solution {
    public String countAndSay(int n) {
        StringBuffer move = new StringBuffer();
        move.append("1");
        while(--n > 0){
            StringBuffer temp = new StringBuffer();
            int count = 1, i=1;
            for(; i< move.length(); i++){
                if(move.charAt(i) == move.charAt(i-1)){
                    count++;
                }else{
                    temp.append(count);
                    temp.append(move.charAt(i-1));
                    count = 1;
                }
            }
            temp.append(count).append(move.charAt(i-1));;
            move = temp;
        }
        
        return move.toString();
    }
}