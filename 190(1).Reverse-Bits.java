/*190. Reverse Bits
Easy
链接: https://leetcode.com/problems/reverse-bits/

Reverse bits of a given 32 bits unsigned integer.

 

Example 1:

Input: 00000010100101000001111010011100
Output: 00111001011110000010100101000000
Explanation: The input binary string 00000010100101000001111010011100 represents 
the unsigned integer 43261596, so return 964176192 which its binary representation 
is 00111001011110000010100101000000.
Example 2:

Input: 11111111111111111111111111111101
Output: 10111111111111111111111111111111
Explanation: The input binary string 11111111111111111111111111111101 represents 
the unsigned integer 4294967293, so return 3221225471 which its binary representation 
is 10111111111111111111111111111111.

Note:
Note that in some languages such as Java, there is no unsigned integer type. 
In this case, both input and output will be given as signed integer type and 
should not affect your implementation, as the internal binary representation of 
the integer is the same whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement
 notation. Therefore, in Example 2 above the input represents the signed 
 integer -3 and the output represents the signed integer -1073741825.
 
*/

/*解题思路
Java中的位运算符：
>>表示右移，如果该数为正，则高位补0，若为负数，则高位补1；
>>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0。
表达式为：
result = exp1 >> exp2;
result = exp2 >>> exp2;
表示把数exp1向右移动exp2位。
例如：
res = 20 >> 2; 
20的二进制为 0001 0100，右移2位后为 0000 0101，则结果就为 res = 5;
res = -20 >> 2;
-20的二进制为其正数的补码加1，即 1110 1011，右移2位后为 1111 1100，结果为 res = -6;
而对于>>>符号而言：
res = 20 >>> 2; 的结果与 >> 相同；
res = -20 >> 2;
-20的二进制为 1110 1011，右移2位，此时高位补0，即 0011 1010，结果为 res = 58;
补充：
<< 是与>>对应的左移运算符，表示将exp1向左移动exp2位，在低位补0。其实，向左移动n位，就相当于乘以2^n。
左移没有<<<运算符！

*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for(int i=0; i<32; i++){
            res += n&1; //每次取最右边的那一位，是结果中的最小位
            n >>>= 1; //这里要用无符号右移
            if( i < 31){ // CATCH: for last digit, don't shift!
                res <<= 1; //将结果左移一位，相当于乘2
            }
        }

        return res;
    }
}