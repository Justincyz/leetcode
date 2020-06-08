/*476. Number Complement
Easy
链接: https://leetcode.com/problems/number-complement/
Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

 

Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 

Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
*/



/*解题思路
这道题目让我们找到一个数字的二进制，然后将原来所有的1变换成0，0变换成1。然后输出这种交替后的数字是什么。
除了上面的两个例子之外，比如说8这个数字，二进制是1000。我们对每一位做一个交换，就变成了0111 = 7。这就是我们要的结果。

我想到的最简单的办法就是使用一个stringbuffer记录每个被翻转的Bit。获取Bit的方法就是我们不停地将原来的数字右移，最右边的每一位都和0进行&的比较。如果原来是1的话，则会变成0，如果原来是0的话，则会变成1。这样我们就可以很简单的获取正确的每一位了。最后我们需要把这个字符串转换成整数，我们利用integer.parseInt()这一个方法可以把二进制的字符串转换成数组。
*/
class Solution {
    public int findComplement(int num) {
        int res = 0;
        StringBuffer sb = new StringBuffer(); 
        while(num > 0){
            if((num & 1) == 0) sb.append("1");
            else sb.append("0");
            num = num >> 1;
        }
        
        return Integer.parseInt(sb.reverse().toString(), 2);
    }
}

/*
上面的做法用到了stringbuffer, 但是实际上还有更简单的做法。我们要获得某一个数的相反位，实际上我们就创建一个长度和原来数字一样的二进制数字，然后对原来的数字做一个减法就可以了。比如说 101 = 5，那么这个数字的补位实际上就是 111 - 101 = 010。如果是原来数字是111的话，那么还是111-111 = 0.
有一个要注意的地方，因为输入的数字的数值范围可以是整形最大数。但是我们二进制使用了补位的原因，所以会导致对于Integer.MAX_VALUE会产生错误。我们就用Long来做这个二进制的mask就好了。
*/

class Solution {
    public int findComplement(int num) {
        Long res = 0L;
        int copy = num;
        
        while(num > 0){
            res |= 1;
            res <<= 1;
            num >>= 1;
        }
  
        return (int)((res>>1) - copy);
    }
}

//这个是网上的另外一种做法，思路和上面的差不多。

class Solution {
    public int findComplement(int num) {
        int cp = num;
        int sum = 0;
        while(num > 0){
            sum = (sum << 1) + 1;
            num >>= 1;
        }
        return sum - cp;
    }
}
