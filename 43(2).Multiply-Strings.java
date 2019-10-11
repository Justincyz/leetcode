/*43. Multiply Strings
链接：https://leetcode.com/problems/multiply-strings/
Medium: 
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
*/

/*解题思路
主要就是模拟普通的乘法，因为有可能有integer overflow的情况出现，所以用
数组存下来。数组的长度就是 num1.length+num2.length 就够了。最大情况就是
num1 和num2全是9，比如说99*999, 结果是98901也不过是两个长度相加罢了

*/
class Solution {
    public String multiply(String num1, String num2) {
        if(num1.length() == 1 && num1.charAt(0) =='0') return "0";
        if(num2.length() == 1 && num2.charAt(0) =='0') return "0";
        
        int n = num1.length(), m = num2.length();
        int[] list = new int[n+m];
        
        for(int i=n-1; i>=0 ; i--){
            for(int j=m-1; j>=0; j--){
                int temp = (num1.charAt(i)-'0')*(num2.charAt(j)-'0')+list[i+j+1];
                list[i+j+1] = temp%10;
                //先不管下一位是不是需要进位，到了下一位再计算就好。不用一次把当前位的进位全部完成
                list[i+j] +=temp/10;   
            }
        }
        StringBuffer res = new StringBuffer();
        for(int i=0; i< list.length; i++){
            res.append(String.valueOf(list[i]));
        }

        // remove leading zero
        if(res.charAt(0) == '0') res.deleteCharAt(0);
   
        
        return res.toString();
    }
}