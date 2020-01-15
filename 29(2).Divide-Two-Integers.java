/*29. Divide Two Integers
链接：https://leetcode.com/problems/divide-two-integers/
Medium: 
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
*/

/*解题思路
dividend = 26: 11010 取负数 -26 =  11100101
divisor = 3 : 101           -3 =  11111101

Round 1
divid : 11100101 = -26   
divis : 11111101 = -3
curSum: 11111010 = -6   ,11110100 = -12, 11101000 =-24   , 11010000 =-48
preSum: 11111101 = -3   ,11111010 = -6 , 11110100 = -12  , 11101000 =-24
q     : 1               ,2             , 4               , 8

Round 2
divid : 11111101 = -2
divis : 11111101 = -3
curSum: 11111010 = -6  
preSum: 11111101 = -3   
q     : 0     because div() will return 0 when divid > divis   


*/
class Solution {
    public int divide(int dividend, int divisor) {       
        if(dividend ==  Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }
      
        boolean isNeg = (dividend < 0) ^ (divisor < 0);
        if(dividend > 0) dividend = -dividend;
        if(divisor > 0) divisor = -divisor;
        int res = div(dividend, divisor);
        return isNeg? -res : res;
    }
    public int div(int divid, int divis){
        if(divid > divis) return 0;
        int curSum = divis << 1, prevSum = divis, q = 1;
        
        while(divid <= curSum && curSum < prevSum){
            prevSum = curSum;
            //curSum <<= 1; q <<= 1;
            curSum = curSum <<1; q =q <<1;
        }
        return q + div(divid - prevSum, divis);
    }
}
