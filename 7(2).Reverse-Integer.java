/*7. Reverse Integer
Easy
链接: https://leetcode.com/problems/reverse-integer/
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers 
within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose 
of this problem, assume that your function returns 0 when the reversed integer
 overflows.


*/

/*解题思路
这个题目有很多的corner case。
第一个方法是最直接的，直接用long来储存数值。因为用Long的话可以直接判断是否当前的值大于或者
小于整形的边界。
*/

class Solution {
   public int reverse(int x) {
        long result = 0;     
        while(Math.abs(x) > 0){
            result = x%10 + result*10;
            x = x/10;
            if(result < Integer.MIN_VALUE || result > Integer.MAX_VALUE -1) return 0;
        }
        return ((int)result);   
    }
}


//第二次做的
class Solution {
    public int reverse(int x) {
        int sign = 1;
        if(x < 0){
            sign = -1;
            x*=-1;
        } 
        String num = String.valueOf(x);
        int p = 0;
        while(p < num.length()){
            if(num.charAt(p) == 0) p++;
            else break;
        }
        num = num.substring(p);
        long res =0;
        for(int i=num.length()-1; i>=0; i--){
            res+= (num.charAt(i)-'0');
            if(res > Integer.MAX_VALUE) return 0;
            res*=10;
        }
        res/=10;
      
        return sign*(int)res;
    }
}