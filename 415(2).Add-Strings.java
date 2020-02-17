/*415. Add Strings
链接：https://leetcode.com/problems/add-strings/
Easy: 
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

/*解题思路
最稳妥的办法还是一位一位的元素往前数，这样不管输入是多大，都不会受到Int或者
long之类的数值限制。记得最后还要判断一下carry是否为0

*/


class Solution {
    public String addStrings(String num1, String num2) {
        int i=num1.length()-1, j = num2.length()-1, carry=0;
        StringBuffer sb = new StringBuffer();
        while(i>=0 || j>=0){
            if(i>=0 && j>=0){
                int cur = num1.charAt(i)-'0'+num2.charAt(j)-'0'+carry;
                sb.append(cur%10);
                carry = cur/10;
                i--; j--;
            }else if(i >=0){
                int cur = num1.charAt(i)-'0'+carry;
                sb.append(cur%10);
                carry = cur/10;
                i--;
            }else{
                int cur = num2.charAt(j)-'0'+carry;
                sb.append(cur%10);
                carry = cur/10;
                j--;
            }
        }
        if(carry !=0) sb.append(1);
        return sb.reverse().toString();
    }
}